package com.example.memos.service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.memos.config.JwtProperties;
import com.example.memos.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTokenProvider { // JWT Service
	
	private final JwtProperties jwtProperties;
	private SecretKey key;
	private JwtParser parser;
	
	// Token 생성
	public String createToken(User user, Duration expiredAt) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + expiredAt.toMillis());
		
		return Jwts.builder()
				.header().add(getHeader()).and()
				.claims()
					.issuedAt(now)
					.issuer(jwtProperties.getIssuer())
					.subject(user.getUsername())
					.expiration(exp)
					.add("role", user.getRole()).and()
				.signWith(getKey(), Jwts.SIG.HS256)
				.compact();
	}

	// 정상 Token인지 판단
	public boolean isValidToken(String token) {
		if (parser == null)
			parser = Jwts.parser().verifyWith(getKey()).build();
		
		try { // 예외 없으면 정상 token
			parser.parseSignedClaims(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	// for Controller
	public Claims getClaims(String token) {
		if (parser == null)
			parser = Jwts.parser().verifyWith(getKey()).build();
		
		try {
			Jws<Claims> jws = parser.parseSignedClaims(token);
			return jws.getPayload(); // token 정보 가져오기
		} catch(Exception e) {
			return null; // 반환된 값이 없으면 다음 단계에서 예외 발생
		}
	}
	
	public Authentication getAuthentication(String token) {
		
		Claims claims = getClaims(token);
		String role = claims.get("role", String.class);
		Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
		
		// 불러온 Token 정보로 UserDeatils 작성
		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername(claims.getSubject())
				.password(claims.getSubject())
				.roles(role)
				.build();
		
		return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
	}
	
	// secret key 생성
	private SecretKey getKey() {
		if (key == null)
			key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getSecretKey()));
		
		return key;
	}
	
	// header 설정
	private Map<String, Object> getHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		
		return header;
	}
}
