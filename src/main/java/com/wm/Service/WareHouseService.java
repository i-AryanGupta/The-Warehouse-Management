package com.wm.Service;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

}
