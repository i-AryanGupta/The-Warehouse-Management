package com.wm.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.InventoryRequest;
import com.wm.responsedto.InventoryResponse;
import com.wm.utility.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest, int storageId, int clientId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory();

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			int productId);

}
