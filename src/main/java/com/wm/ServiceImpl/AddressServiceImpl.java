package com.wm.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.AddressRepository;
import com.wm.Repository.WareHouseRepository;
import com.wm.Service.AddressService;
import com.wm.entity.Address;
import com.wm.exception.AddressNotFoundException;
import com.wm.exception.WarehouseNotFoundByIdException;
import com.wm.mapper.AddressMapper;
import com.wm.requestdto.AddressRequest;
import com.wm.responsedto.AddressResponse;
import com.wm.utility.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,
			int warehouseId) {
		
		return wareHouseRepository.findById(warehouseId).map(warehouse ->{
			Address address = addressMapper.mapAddressRequestToAddress(addressRequest, new Address());
			
			address.setWarehouse(warehouse);
			address = addressRepository.save(address);
			
			AddressResponse response = addressMapper.mapAddressResponseToAddress(address);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AddressResponse>()
							.setData(response)
							.setMessage("Address Created")
							.setStatus(HttpStatus.CREATED.value()));	
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse Not Found"));	
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,
			int addressId) {
		return addressRepository.findById(addressId).map(address ->{
			address = addressMapper.mapAddressRequestToAddress(addressRequest, address);
			address = addressRepository.save(address);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AddressResponse>()
							.setData(addressMapper.mapAddressResponseToAddress(address))
							.setMessage("Updated the address")
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new AddressNotFoundException("Address not found"));	
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) {
		
		return addressRepository.findById(addressId).map(address -> {
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AddressResponse>()
							.setData(addressMapper.mapAddressResponseToAddress(address))
							.setMessage("Address Found")
							.setStatus(HttpStatus.FOUND.value()));
		}).orElseThrow(()-> new AddressNotFoundException("Address not Found"));

	}

	

}
