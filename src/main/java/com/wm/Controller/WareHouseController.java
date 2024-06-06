package com.wm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Service.WareHouseService;
import com.wm.requestdto.WareHouseRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.responsedto.WareHouseResponse;
import com.wm.utility.ResponseStructure;

@RestController
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@GetMapping("/warehouses")
	public String createWareHouse()
	{
		return "warehouse found";

		
	}
	

}
