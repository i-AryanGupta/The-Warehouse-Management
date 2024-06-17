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
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		
		return storage;
		
	}
	
	public StorageResponse mapStorageResponseToStorage(Storage storage)
	{
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}


}
