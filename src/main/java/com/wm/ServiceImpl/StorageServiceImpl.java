package com.wm.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.StorageRepository;
import com.wm.Repository.WareHouseRepository;
import com.wm.Service.StorageService;
import com.wm.entity.Storage;
import com.wm.exception.StorageNotFoundException;
import com.wm.exception.WarehouseNotFoundByIdException;
import com.wm.mapper.StorageMapper;
import com.wm.requestdto.StorageRequest;
import com.wm.responsedto.StorageResponse;
import com.wm.utility.ResponseStructure;
import com.wm.utility.SimpleResponse;

@Service
public class StorageServiceImpl implements StorageService{
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private WareHouseRepository warehouseRepository;
	
	@Autowired
	private StorageMapper storageMapper;

	@Override
	public ResponseEntity<SimpleResponse<String>> addStorage(StorageRequest storageRequest,
			int noOfStorageUnits, int warehouseId) {
		
		
		return warehouseRepository.findById(warehouseId).map(warehouse ->{
			List<Storage> storages = new ArrayList<Storage>();
			
			int storageUnits=noOfStorageUnits;
			
			while(storageUnits>0)
			{
			Storage storage = storageMapper.mapStorageRequestToStorage(storageRequest, new Storage());
			storage.setAvailableArea(storageRequest.getLengthInMeters()* storageRequest.getBreadthInMeters()*storageRequest.getHeightInMeters());
			storage.setMaxAdditionalInKg(storageRequest.getCapacityInKg());
			storage.setWarehouse(warehouse);
			storages.add(storage);
			
			storageUnits--;
			
			}
			
			storageRepository.saveAll(storages);
			
			return ResponseEntity.status(HttpStatus.CREATED)
			.body( new SimpleResponse<String>()
					.setMessage("Storages Created")
					.setStatus(HttpStatus.CREATED.value()));
			
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse Not Found"));
		

	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,
			int storageId) {
		
		return storageRepository.findById(storageId).map(storage ->{
			storage =storageMapper.mapStorageRequestToStorage(storageRequest, storage);
			
			storageRepository.save(storage);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<StorageResponse>()
							.setMessage("Updated the storage")
							.setData(storageMapper.mapStorageResponseToStorage(storage))
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new StorageNotFoundException("Storage not Found"));
	}

}
