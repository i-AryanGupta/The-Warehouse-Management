package com.wm.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.wm.entity.Inventory;
import com.wm.requestdto.InventoryRequest;
import com.wm.responsedto.InventoryResponse;

@Component
public class InventoryMapper {
	
	public Inventory mapToInventoryRequest(InventoryRequest inventoryRequest, Inventory inventory)
	{
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setSellerId(inventoryRequest.getSellerId());
		
		return inventory;
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory)
	{
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.weightInKg(inventory.getWeightInKg())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.build();
	}
	
	

}
