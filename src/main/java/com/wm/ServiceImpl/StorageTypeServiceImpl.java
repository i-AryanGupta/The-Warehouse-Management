package com.wm.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.StorageTypeRepository;

import com.wm.Service.StorageTypeService;
import com.wm.entity.StorageType;
import com.wm.exception.StorageNotFoundException;
import com.wm.exception.StorageTypeNotFound;
import com.wm.mapper.StorageTypeMapper;
import com.wm.requestdto.StorageTypeRequest;
import com.wm.responsedto.StorageTypeResponse;
import com.wm.utility.ResponseStructure;


@Service
public class StorageTypeServiceImpl implements StorageTypeService {
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		
		StorageType storageType = storageTypeMapper.mapToStorageTypeRequest(storageTypeRequest, new StorageType());
		storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>()
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType))
						.setMessage("StorageType Created")
						.setStatus(HttpStatus.CREATED.value()));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
			StorageTypeRequest storageTypeRequest, int storageTypeId) {
		
		return storageTypeRepository.findById(storageTypeId).map(storageType -> {
			storageType = storageTypeMapper.mapToStorageTypeRequest(storageTypeRequest, storageType);
			
			storageType= storageTypeRepository.save(storageType);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<StorageTypeResponse>()
							.setData(storageTypeMapper.mapToStorageTypeResponse(storageType))
							.setMessage("StorageType updated")
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(()-> new StorageTypeNotFound("Storage Type is not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageType() {
		
		List<StorageTypeResponse> storages = storageTypeRepository.findAll().stream().map(storageType -> 
		storageTypeMapper.mapToStorageTypeResponse(storageType)).toList();
		
		if(storages.isEmpty())
		{
			throw new StorageNotFoundException("Storages Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<StorageTypeResponse>>()
						.setData(storages)
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("All storagetypes are found"));
	}
	
	
	
	
	

}
