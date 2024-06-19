package com.wm.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.ClientRepository;
import com.wm.Repository.InventoryRepository;
import com.wm.Repository.StorageRepository;
import com.wm.Service.InventoryService;
import com.wm.entity.Client;
import com.wm.entity.Inventory;
import com.wm.entity.Storage;
import com.wm.exception.ClientNotFoundException;
import com.wm.exception.InventoryNotFoundException;
import com.wm.exception.SpaceOrWeightNotAvailableException;
import com.wm.exception.StorageNotFoundException;
import com.wm.mapper.InventoryMapper;
import com.wm.requestdto.InventoryRequest;
import com.wm.responsedto.InventoryResponse;
import com.wm.utility.ResponseStructure;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest,
			int storageId, int clientId) {
		
		Client client =clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not Found"));
		
		return storageRepository.findById(storageId).map(storage -> {
			
			
			 Inventory inventory = inventoryMapper.mapToInventoryRequest(inventoryRequest, new Inventory());
			 inventory.setClient(client);
			 inventory.setRestockedAt(LocalDate.now());
			 storage.getInventories().add(inventory);
			 
			 double wholeWeight = inventory.getWeightInKg()*inventory.getQuantity();
			 
			 double area = inventory.getBreadthInMeters()*inventory.getHeightInMeters()*inventory.getLengthInMeters();
			 
			 
			 storage.setMaxAdditionalInKg(storage.getMaxAdditionalInKg()-wholeWeight);
			 storage.setAvailableArea(storage.getAvailableArea()-area);
			 storage.setSellerId(inventory.getSellerId());
			 
			 inventory = inventoryRepository.save(inventory);
			 storageRepository.save(storage);
			 
			 return ResponseEntity.status(HttpStatus.CREATED)
					 .body(new ResponseStructure<InventoryResponse>()
							 .setData(inventoryMapper.mapToInventoryResponse(inventory))
							 .setMessage("Inventory added")
							 .setStatus(HttpStatus.CREATED.value()));
			
		}).orElseThrow(() -> new StorageNotFoundException("Storage not found"));
		
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			int productId) {
		
		return inventoryRepository.findById(productId).map(inventory -> {
			
				int oldQuantity = inventory.getQuantity();
			 double originalWeight = inventory.getWeightInKg() * inventory.getQuantity();
			 double originalArea = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
			 
			 inventory = inventoryMapper.mapToInventoryRequest(inventoryRequest, inventory);
			 
			 if(oldQuantity != inventory.getQuantity())
			   inventory.setRestockedAt(LocalDate.now());

			   double newWeight = inventory.getWeightInKg() * inventory.getQuantity();
			   double newArea = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();

			
			inventory.getStorages().forEach(storage -> {
				
				if(storage.getAvailableArea() >0 && storage.getMaxAdditionalInKg() >0)
				{
					storage.setMaxAdditionalInKg(storage.getMaxAdditionalInKg() + originalWeight - newWeight);
			        storage.setAvailableArea(storage.getAvailableArea() + originalArea - newArea);
			        
				}
				
				else
				{
					throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");
				}
		    });
			
			
			
			inventory = inventoryRepository.save(inventory);
		    inventory.getStorages().forEach(storageRepository::save);
		    
		    return ResponseEntity.status(HttpStatus.OK)
		            .body(new ResponseStructure<InventoryResponse>()
		                    .setData(inventoryMapper.mapToInventoryResponse(inventory))
		                    .setMessage("Inventory updated")
		                    .setStatus(HttpStatus.OK.value()));
			
			
		}).orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId) {
		
		return inventoryRepository.findById(productId).map(inventory ->{
			InventoryResponse response = inventoryMapper.mapToInventoryResponse(inventory);
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<InventoryResponse>()
							.setData(response)
							.setMessage("Inventory Found")
							.setStatus(HttpStatus.FOUND.value()));
		}).orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory() {
		
		List<InventoryResponse> responses = inventoryRepository.findAll().stream().map(inventory -> 
		inventoryMapper.mapToInventoryResponse(inventory)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<InventoryResponse>>()
						.setData(responses)
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Inventories Found"));
	}



}
