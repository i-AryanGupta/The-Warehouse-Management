package com.wm.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wm.Repository.AdminRepository;
import com.wm.Service.AdminService;
import com.wm.entity.Admin;
import com.wm.enums.AdminType;
import com.wm.enums.Privilege;
import com.wm.exception.MultipleSuperAdminException;
import com.wm.mapper.AdminMapper;
import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.utility.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		
        
        if (adminRepository.existsByAdminType(AdminType.SUPER_ADMIN)) {
            throw new MultipleSuperAdminException("A Super Admin already exists.");
        }
		
		
		Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		

		
		
		admin = adminRepository.save(admin);
		
//		admin.setPrivileges(List.of(Privilege.CREATE_ADMIN,Privilege.CREATE_WAREHOUSE));
//		List<Privilege> ar = new ArrayList<>();
//		ar.add(Privilege.CREATE_ADMIN);
//		ar.add(Privilege.CREATE_WAREHOUSE);
//		admin.setPrivileges(ar);
		
		
		
		AdminResponse response = adminMapper.mapAdminResponseToAdmin(admin);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setData(response)
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Super Admin Created"));
	}

}
