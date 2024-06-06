package com.wm.ServiceImpl;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.wm.Repository.WareHouseRepository;
import com.wm.Service.WareHouseService;
import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

@org.springframework.stereotype.Service
public class WareHouseServiceImpl implements WareHouseService{

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		
		return null;
	}

}
