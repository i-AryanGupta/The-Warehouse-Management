package com.wm.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wm.Repository.ClientRepository;
import com.wm.entity.Client;
import com.wm.exception.ApiKeyORUsernameNotFoundException;
import com.wm.exception.IllegalOperationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientRequestFilter extends OncePerRequestFilter {
	//ApiKeyFilter
	
//	@Autowired
	private ClientRepository clientRepository;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getSession(false)!= null)
		{
			throw new IllegalOperationException("Session is already created");
		}
		
		String reqUrl =request.getRequestURI();
		
		
		
		
		if(!reqUrl.equals("/api/version1/client/register"))
		{
		
			String apiKey = request.getHeader("API-KEY");
			String username =request.getHeader("USERNAME");
			
		 if (username != null || apiKey != null) {
			 
			 Client client = clientRepository.findByEmail(username)
					 .orElseThrow(() -> new ApiKeyORUsernameNotFoundException("Api-Key is Empty or Username is Empty"));
			 
			 if (!apiKey.equals(client.getApiKey()))throw new BadCredentialsException("Bad Credentials Entered");	

			 }
		 else
		 {
			throw new ApiKeyORUsernameNotFoundException("Api-Key is Empty or Username is Empty");
		 }
		}

		filterChain.doFilter(request, response);

	}
	

}
