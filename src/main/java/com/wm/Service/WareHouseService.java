package com.wm.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.WareHouseRequest;

import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest, int warehouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int warehouseId);

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWarehouse();

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCity(String city);

}
