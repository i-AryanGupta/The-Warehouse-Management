package com.wm.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Repository.StorageRepository;
import com.wm.Service.StorageService;
import com.wm.entity.Storage;
import com.wm.requestdto.StorageRequest;
import com.wm.requestdto.StorageTypeRequest;
import com.wm.responsedto.StorageResponse;
import com.wm.utility.ResponseStructure;
import com.wm.utility.SimpleResponse;

@RestController
@RequestMapping("/api/version1")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	
	
	@PreAuthorize("hasAuthority('CREATE_STORAGE')")
	@PostMapping("/warehouses/{warehouseId}/storageTypes/{storageTypeId}/storages")
	public ResponseEntity<SimpleResponse<String>> addStorage(@RequestBody StorageRequest storageRequest,@RequestParam("no_of_storage_units") int noOfStorageUnits, @PathVariable int warehouseId, @PathVariable int storageTypeId)
	{
		return storageService.addStorage(storageRequest, noOfStorageUnits, warehouseId, storageTypeId);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@RequestBody StorageRequest storageRequest, @PathVariable int storageId)
	{
		return storageService.updateStorage(storageRequest, storageId);
	}
	
	

}
