package com.wm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wm.entity.Admin;
import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;


@Component
public class AdminMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Admin mapAdminRequestToAdmin(AdminRequest adminRequest, Admin admin) {
		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
		
		return admin;
	}
	
	public AdminResponse mapAdminResponseToAdmin(Admin admin)
	{
		return AdminResponse.builder()
				.name(admin.getName())
				.email(admin.getEmail())
				.adminId(admin.getAdminId())
				.adminType(admin.getAdminType())
				.build();
	}
	
	

}
