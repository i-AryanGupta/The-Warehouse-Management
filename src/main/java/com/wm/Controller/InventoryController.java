package com.wm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Service.InventoryService;
import com.wm.requestdto.InventoryRequest;
import com.wm.responsedto.InventoryResponse;
import com.wm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/version1")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/storages/{storageId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>>addInventory(@RequestBody InventoryRequest inventoryRequest,@PathVariable int storageId, @PathVariable int clientId)
	{
		return inventoryService.addInventory(inventoryRequest, storageId, clientId);
		
	}
	
	@PutMapping("/inventories/{productId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>>updateInventory(@RequestBody InventoryRequest inventoryRequest,@PathVariable int productId)
	{
		return inventoryService.updateInventory(inventoryRequest, productId);
	}
	
	@GetMapping("/inventories/{productId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(@PathVariable int productId)
	{
		return inventoryService.findInventory(productId);
	}
	
	@GetMapping("/inventories")
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory()
	{
		return inventoryService.findAllInventory();
	}
	
}

