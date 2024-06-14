package com.wm.requestdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
	
	@NotEmpty(message = "AddressLine is required")
	private String addressLine;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@NotEmpty(message = "State is required")
	private String state;
	
	@NotEmpty(message = "Country is required")
	private String country;
	
	@NotEmpty(message = "Pincode is required")
	private int pincode;
	
	@NotEmpty(message = "Longitude is required")
	private String longitude;
	
	@NotEmpty(message = "Latitude is required")
	private String latitude;

}
