package com.wm.Controller;

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

import com.wm.Service.AddressService;
import com.wm.requestdto.AddressRequest;
import com.wm.responsedto.AddressResponse;
import com.wm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/version1")
public class AddressController {
	
	
	@Autowired
	private AddressService addressService;
	
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	@PostMapping("/warehouses/{warehouseId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>>addAddress(@RequestBody AddressRequest addressRequest, @PathVariable int warehouseId )
	{
		return addressService.addAddress(addressRequest, warehouseId);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
	@PutMapping("/addresses/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>>updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable int addressId)
	{
		return addressService.updateAddress(addressRequest, addressId);
	}
	
	@GetMapping("/addresses/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>>findAddress(@PathVariable int addressId)
	{
		return addressService.findAddress(addressId);
	}

	
	

}
