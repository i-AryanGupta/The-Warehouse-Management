package com.wm.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.utility.ResponseStructure;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);


	ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int warehouseId);


	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);


	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest, int adminId);


	ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId);


	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins();
	

}
