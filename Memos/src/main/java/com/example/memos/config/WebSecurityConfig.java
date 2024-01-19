package com.example.memos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.memos.dto.TokenExceptionResponse;
import com.example.memos.service.JWTokenProvider;
import com.example.memos.service.UserDetailsServiceImpl;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
	
	private final JWTokenProvider tokenProvider;
	
	// jwt filter
	@Bean
	@Order(1) // /api/** 우선 처리
	SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/api/**")
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize ->
				authorize
				.requestMatchers("/api/join", "/api/signin", "/api/token").permitAll()
				.anyRequest().authenticated()
			)
			.sessionManagement((sessionManagement) -> 
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling((exceptions) -> 
				exceptions
					.authenticationEntryPoint(jwtException()));
		
		return http.build();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests -> // 주소별 필터
					requests // permitAll() -> 바로 controller로 이동
						.requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**", "/uploads/**").permitAll() // static files 
						.requestMatchers("/signin", "/signout", "/join", "/").permitAll() // All method
						.anyRequest().authenticated()) // permitAll()이 없는 나머지 페이지는 권한(로그인)이 있어야 접근 가능, 사용자 정보가 있으면 controller로 이동
				// 로그인 정보가 없으면 formLogin, logout 실행
				.formLogin(login -> // form login에 대한 필터 설정
					login
						.loginPage("/signin") // Request(아이디, 비밀번호) 전달
						.defaultSuccessUrl("/memos")
						.failureForwardUrl("/"))
				.logout(logout -> // logout에 대한 필터 설정
					logout
						.logoutUrl("/signout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				.build();
	}
	
	// ProviderManager
	@Bean
	AuthenticationManager authenticationManager(
			// 사용자가 직접 설장한 값
			UserDetailsServiceImpl userDetailsService,
			BCryptPasswordEncoder bCryptPasswordEncoder
			) {
		// memory 영역에서 사용자가 직접 설정한 값을 가져와 비교
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		
		return new ProviderManager(authenticationProvider);
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 401, 403 Error Handler
	private AuthenticationEntryPoint jwtException() {
		
		AuthenticationEntryPoint ap = (request, response, authException) -> {
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			TokenExceptionResponse res = new TokenExceptionResponse();
			
			String message = (String) request.getAttribute("TokenException");
			if (message != null) { // message 값이 없으면 401
				response.setStatus(401);
				res.setResult(message);
			} else { // 말고는 403
				response.setStatus(403);
				res.setResult(authException.getMessage());
			}
			
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(res));
		};
		
		return ap;
	}

}
