package com.wm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
	
	
	private int addressId;
	private String addressLine;
	private String state;
	private String country;
	private int pincode;
	private String longitude;
	private String latitude;

}