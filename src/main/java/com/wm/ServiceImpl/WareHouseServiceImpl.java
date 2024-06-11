package com.wm.ServiceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;


import com.wm.Repository.WareHouseRepository;
import com.wm.Service.WareHouseService;
import com.wm.entity.WareHouse;

import com.wm.exception.WarehouseNotFoundByIdException;
import com.wm.mapper.WarehouseMapper;
import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

@org.springframework.stereotype.Service
public class WareHouseServiceImpl implements WareHouseService{

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		
		WareHouse warehouse = warehouseMapper.mapRequestToWarehouse(wareHouseRequest, new WareHouse());
		warehouse = wareHouseRepository.save(warehouse);
		
		WareHouseResponse warehouseResponse = warehouseMapper.mapResponseToWarehouse(warehouse);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WareHouseResponse>()
						.setData(warehouseResponse)
						.setMessage("Created")
						.setStatus(HttpStatus.CREATED.value()));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,
			int warehouseId) {
		
		 return wareHouseRepository.findById(warehouseId).map(warehouse ->{
		 
				 warehouse = warehouseMapper.mapRequestToWarehouse(wareHouseRequest, warehouse);
		 
		 			warehouse = wareHouseRepository.save(warehouse);
		 			
		 			WareHouseResponse response = warehouseMapper.mapResponseToWarehouse(warehouse);
		 			return ResponseEntity.status(HttpStatus.OK)
		 					.body( new ResponseStructure<WareHouseResponse>()
		 							.setData(response)
		 							.setMessage("Updated warehouse")
		 							.setStatus(HttpStatus.OK.value()));
		 }).orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse not found so not able to update"));
		 			
	
	}

	
//	 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
//	 Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//	 if(authorities.contains(new SimpleGrantedAuthority(Privilege.CREATE_WAREHOUSE.name())))
//	 	{WareHouse warehouse = warehouseMapper.mapRequestToWarehouse(wareHouseRequest, new WareHouse());
//		warehouse = wareHouseRepository.save(warehouse);}

}
