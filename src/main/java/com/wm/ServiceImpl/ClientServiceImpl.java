package com.wm.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.ClientRepository;
import com.wm.Service.ClientService;
import com.wm.entity.Client;
import com.wm.exception.ClientNotFoundException;
import com.wm.mapper.ClientMapper;
import com.wm.requestdto.ClientRequest;
import com.wm.responsedto.ApiKeyResponse;
import com.wm.utility.ResponseStructure;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest) {
		String apiKey = UUID.randomUUID().toString();
		
		Client client =clientMapper.mapToClientRequest(clientRequest, new Client());
		client.setApiKey(apiKey);
		
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<ApiKeyResponse>()
						.setData(clientMapper.mapToClientResponse(client))
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Client Data created")
						);

	}

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(ClientRequest clientRequest, int clientId) {
		
		return clientRepository.findById(clientId).map(client -> {
			client = clientMapper.mapToClientRequest(clientRequest, client);
			
			clientRepository.save(client);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<ApiKeyResponse>()
							.setData(clientMapper.mapToClientResponse(client))
							.setMessage("Client Updated")
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(()-> new ClientNotFoundException("Client not Found"));
	}

}
