package com.wm.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.StorageTypeRequest;
import com.wm.responsedto.StorageTypeResponse;
import com.wm.utility.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest,
			int storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageType();

}
