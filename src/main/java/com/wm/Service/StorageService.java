package com.wm.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.wm.requestdto.StorageRequest;
import com.wm.requestdto.StorageTypeRequest;
import com.wm.responsedto.StorageResponse;
import com.wm.utility.ResponseStructure;
import com.wm.utility.SimpleResponse;

public interface StorageService {

	ResponseEntity<SimpleResponse<String>> addStorage(StorageRequest storageRequest, int noOfStorageUnits,
			int warehouseId, int storageTypeId);

	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest, int storageId);

}
