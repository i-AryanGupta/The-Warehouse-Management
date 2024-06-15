package com.wm.Service;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.ClientRequest;
import com.wm.responsedto.ApiKeyResponse;
import com.wm.utility.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(ClientRequest clientRequest, int clientId);



}
