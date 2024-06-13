package com.wm.Service;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.AddressRequest;
import com.wm.responsedto.AddressResponse;
import com.wm.utility.ResponseStructure;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest, int warehouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, int addressId);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId);

}
