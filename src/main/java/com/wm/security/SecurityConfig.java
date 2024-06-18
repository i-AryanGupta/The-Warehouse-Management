package com.wm.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wm.Repository.ClientRepository;
import com.wm.filter.ClientRequestFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Bean
	 PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(12); //it is uses to encryt password, 12 times iteration of hashing for encrption for security purpose
	}
	
	@Bean
	@Order(2)
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf(csrf-> csrf.disable()).securityMatchers(matcher ->
	matcher.requestMatchers("/api/version1/**",  "/login/**")).authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/version1/register")
						.permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	
	@Bean
	@Order(1) //specifies the order of filter chain
	SecurityFilterChain	clientRequestFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf(csrf -> csrf.disable()).securityMatchers(matcher ->
			
		matcher.requestMatchers("/api/version1/client/**")).authorizeHttpRequests(authorize -> authorize.anyRequest()
				.permitAll()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new ClientRequestFilter(clientRepository), UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
}
