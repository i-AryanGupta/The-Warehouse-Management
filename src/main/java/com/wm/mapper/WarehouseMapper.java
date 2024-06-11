package com.wm.mapper;

import org.springframework.stereotype.Component;

import com.wm.entity.WareHouse;
import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.WareHouseResponse;

@Component
public class WarehouseMapper {
	
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
				.totalCapacity(0)
				.build();
	}
}
