package com.wm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wm.entity.Address;
import com.wm.entity.WareHouse;
import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.WareHouseResponse;

@Component
public class WarehouseMapper {
	
	@Autowired
	private AddressMapper addressMapper;
	
	public WareHouse mapRequestToWarehouse(WareHouseRequest warehouseRequest, WareHouse warehouse)
	{
		warehouse.setName(warehouseRequest.getName());
		return warehouse;
			
	}

	public WareHouseResponse mapResponseToWarehouse(WareHouse warehouse)
	{
		return WareHouseResponse.builder()
				.wareHouseId(warehouse.getWarehouseId())
				.name(warehouse.getName())
				.totalCapacityInKg(warehouse.getTotalCapacityInKg())
				.build();
	}
	
	public WareHouseResponse mapResponseToWarehouse(WareHouse warehouse, Address address)
	{
		WareHouseResponse response = mapResponseToWarehouse(warehouse);
		response.setAddressResponse(addressMapper.mapAddressResponseToAddress(address));
		
		return response;
	}
	

}
