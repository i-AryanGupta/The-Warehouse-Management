package com.wm.ServiceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import com.wm.Repository.AddressRepository;
import com.wm.Repository.WareHouseRepository;
import com.wm.Service.WareHouseService;
import com.wm.entity.WareHouse;
import com.wm.exception.WarehouseInCityNotFoundException;
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
	
	@Autowired
	private AddressRepository addressRepository;
	

	
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		
		WareHouse warehouse = warehouseMapper.mapRequestToWarehouse(wareHouseRequest, new WareHouse());
		warehouse.setTotalCapacityInKg(0);
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

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int warehouseId) {
		
		return wareHouseRepository.findById(warehouseId).map(warehouse ->{
			WareHouseResponse response = warehouseMapper.mapResponseToWarehouse(warehouse);
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<WareHouseResponse>()
							.setData(response)
							.setMessage("Warehouse Found")
							.setStatus(HttpStatus.FOUND.value()));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWarehouse() {
		List<WareHouseResponse> warehouses = wareHouseRepository.findAll().stream().map(warehouse ->
		warehouseMapper.mapResponseToWarehouse(warehouse)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<WareHouseResponse>>()
						.setData(warehouses)
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Warehouses Details Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCity(String city) {
	
		List<WareHouseResponse> cities = addressRepository.findAllByCity(city).stream().map(address -> 
		warehouseMapper.mapResponseToWarehouse(address.getWarehouse(), address)).toList();
		
		if(cities.isEmpty())
		{
			throw new WarehouseInCityNotFoundException("City is not Found");
		}
			
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<WareHouseResponse>>()
						.setData(cities)
						.setMessage("Found All Similar Cities")
						.setStatus(HttpStatus.FOUND.value()));
	}

	
//	 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
//	 Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//	 if(authorities.contains(new SimpleGrantedAuthority(Privilege.CREATE_WAREHOUSE.name())))
//	 	{WareHouse warehouse = warehouseMapper.mapRequestToWarehouse(wareHouseRequest, new WareHouse());
//		warehouse = wareHouseRepository.save(warehouse);}

}
