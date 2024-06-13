package com.wm.mapper;

import org.springframework.stereotype.Component;

import com.wm.entity.Storage;
import com.wm.requestdto.StorageRequest;
import com.wm.responsedto.StorageResponse;

@Component
public class StorageMapper {
	
	public Storage mapStorageRequestToStorage(StorageRequest storageRequest, Storage storage)
	{
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setCapacityInKg(storageRequest.getCapacityInKg());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		
		return storage;
		
	}
	
	public StorageResponse mapStorageResponseToStorage(Storage storage)
	{
		return StorageResponse.builder()
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.capacityInKg(storage.getCapacityInKg())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}
	
//	.maxAdditionalInKg(storage.getCapacityInKg())
//	.availableArea(storage.getLengthInMeters()*storage.getBreadthInMeters()*storage.getHeightInMeters())
	

}
