package com.example.memos.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.memos.service.JWTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final JWTokenProvider tokenProvider;
	private static final String AUTH_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String token = getToken(request);
		
		if (token != null) {
			if (tokenProvider.isValidToken(token)) {
				Authentication authentication = tokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				request.setAttribute("TokenException", "Invalid Token");
			}
		} else {
			request.setAttribute("TokenException", "Token Required");
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(AUTH_HEADER);
		
		if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX))
			return authorizationHeader.substring(TOKEN_PREFIX.length());
		
		return null;
	}
}
