package com.wm.requestdto;



import com.wm.enums.AdminType;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {
	
	@NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username should contain only alphabets")
	private String name;
	
	@NotEmpty(message = "Email is required")
    @Email(regexp = ".+@gmail\\.com$", message = "Email should be a valid Gmail address")
	private String email;
	
	@NotEmpty(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
	         message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character")
	private String password;

	

}
