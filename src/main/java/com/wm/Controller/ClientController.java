package com.wm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Service.ClientService;
import com.wm.requestdto.ClientRequest;
import com.wm.responsedto.ApiKeyResponse;
import com.wm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/version1")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody ClientRequest clientRequest)
	{
		return clientService.registerClient(clientRequest);
	}
	
	@PutMapping("/clients/{clientId}")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@RequestBody ClientRequest clientRequest, @PathVariable int clientId)
	{
		return clientService.updateClient(clientRequest, clientId);
	}

	

}
