package com.wm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Service.WareHouseService;
import com.wm.requestdto.WareHouseRequest;

import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/version1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouses")
	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@RequestBody WareHouseRequest wareHouseRequest)
	{
		return wareHouseService.createWareHouse(wareHouseRequest);

	}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{warehouseId}")
	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(@RequestBody WareHouseRequest wareHouseRequest, @PathVariable int warehouseId)
	{
		return wareHouseService.updateWareHouse(wareHouseRequest, warehouseId);
	}
	
	@GetMapping("/warehouses/{warehouseId}")
	ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(@PathVariable int warehouseId)
	{
		return wareHouseService.findWarehouse(warehouseId);
	}
	
	@GetMapping("/warehouses")
	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWarehouse()
	{
		return wareHouseService.findAllWarehouse();
	}
	
	
	@GetMapping("/cities/{city}/warehouses")
	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCityAdmin(@PathVariable String city)
	{
		return wareHouseService.findWarehousesByCity(city);
	}
	
	@GetMapping("client/cities/{city}/warehouses")
	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCityClient(@PathVariable String city)
	{
		return wareHouseService.findWarehousesByCity(city);
	}
	

}
