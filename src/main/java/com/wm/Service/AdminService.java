package com.wm.Service;

import org.springframework.http.ResponseEntity;

import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.utility.ResponseStructure;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

}
