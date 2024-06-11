package com.wm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.Service.AdminService;
import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.utility.ResponseStructure;



@RestController
@RequestMapping("/api/version1")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody AdminRequest adminRequest)
	{
		return adminService.createSuperAdmin(adminRequest);
		
	}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody AdminRequest adminRequest, @PathVariable int warehouseId)
	{
		
		return adminService.createAdmin(adminRequest, warehouseId);
	}

}
