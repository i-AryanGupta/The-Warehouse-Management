package com.wm.mapper;

import org.springframework.stereotype.Component;

import com.wm.entity.Client;
import com.wm.requestdto.ClientRequest;
import com.wm.responsedto.ApiKeyResponse;

@Component
public class ClientMapper {
	
	public Client mapToClientRequest( ClientRequest clientRequest, Client client)
	{
		client.setBussinessName(clientRequest.getBussinessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());
		
		return client;
		
	}
	
	public ApiKeyResponse mapToClientResponse(Client client)
	{
		return ApiKeyResponse.builder()
				.apiKey(client.getApiKey())
				.build();
	}

}
