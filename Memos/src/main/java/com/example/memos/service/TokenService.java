package com.example.memos.service;

import java.time.Duration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.memos.config.JwtProperties;
import com.example.memos.domain.RefreshToken;
import com.example.memos.domain.User;
import com.example.memos.dto.CreateAccessTokenRequest;
import com.example.memos.dto.CreateAccessTokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService { // Controller에서 사용할 Service
	
	private final JWTokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtProperties jwtProperties;
	
	public CreateAccessTokenResponse createAccessToken(CreateAccessTokenRequest request) {
		
		// 처음 token 발급시 id&pw 확인 필요
		if (request.getUsername() != null && request.getPassword() != null) {
			
			User user = userService.findById(request.getUsername());
			
			// 비밀번호는 암호화 걸어서 확인
			if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
				return new CreateAccessTokenResponse(
						tokenProvider.createToken(user, Duration.ofMinutes(jwtProperties.getDuration())),
						createRefreshToken(user));
			}
		}
		
		// refesh token을 통한 access token 발급
		else if (request.getRefreshToken() != null) {
			if (tokenProvider.isValidToken(request.getRefreshToken())) {
				String username = refreshTokenService.findByRefreshToken(request.getRefreshToken()).getUsername();
				User user = userService.findById(username);
				
				return new CreateAccessTokenResponse(
						tokenProvider.createToken(user, Duration.ofMinutes(jwtProperties.getDuration())),
						null);
			}
		}
		
		throw new IllegalArgumentException("Invalid password");
	}
	
	// refresh token 생성
	public String createRefreshToken(User user) throws IllegalArgumentException {
		
		String token = tokenProvider.createToken(user, Duration.ofMinutes(jwtProperties.getRefreshDuration()));
		RefreshToken refreshToken = refreshTokenService.findByUsername(user.getUsername());
		refreshToken.setRefreshToken(token);
		refreshTokenService.save(refreshToken);
		
		return token;
	}

}
