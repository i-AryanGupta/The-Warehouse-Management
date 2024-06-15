package com.wm.requestdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	
	@NotEmpty(message = "Bussiness Name is required")
	private String bussinessName;
	@NotEmpty(message = "Email is required")
	private String email;
	@NotEmpty(message = "Contact Number is required")
	private long contactNumber;

}
