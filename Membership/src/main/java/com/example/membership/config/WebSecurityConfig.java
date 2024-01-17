package com.example.membership.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.membership.service.AdminDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests ->
					requests
						.requestMatchers("/", "/join", "/signin", "/signout").permitAll()
						.anyRequest().authenticated())
				.formLogin(login ->
					login
						.loginPage("/signin") // default url : /login
						.defaultSuccessUrl("/members")
						.failureForwardUrl("/"))
				.logout(logout ->
					logout
						.logoutUrl("/signout") // default url : /logout
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(
			AdminDetailsServiceImpl adminDetailService,
			BCryptPasswordEncoder bCrptBCryptPasswordEncoder) {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(adminDetailService);
		authenticationProvider.setPasswordEncoder(bCrptBCryptPasswordEncoder);
		
		return new ProviderManager(authenticationProvider);
		
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
