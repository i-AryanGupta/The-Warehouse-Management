package com.wm.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.wm.Repository.AdminRepository;
import com.wm.Repository.WareHouseRepository;
import com.wm.Service.AdminService;
import com.wm.entity.Admin;
import com.wm.enums.AdminType;
import com.wm.enums.Privilege;
import com.wm.exception.AdminNotMatchException;
import com.wm.exception.MultipleSuperAdminException;
import com.wm.exception.WarehouseNotFoundByIdException;
import com.wm.mapper.AdminMapper;
import com.wm.requestdto.AdminRequest;
import com.wm.responsedto.AdminResponse;
import com.wm.utility.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
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

		
		
		AdminResponse response = adminMapper.mapAdminResponseToAdmin(admin);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setData(response)
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Super Admin Created"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int warehouseId) {
		return wareHouseRepository.findById(warehouseId).map(warehouse->{
			Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			admin = adminRepository.save(admin);
			
			warehouse.setAdmin(admin);
			wareHouseRepository.save(warehouse);
			
			AdminResponse response = adminMapper.mapAdminResponseToAdmin(admin);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setData(response)
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("Admin Created"));

		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return adminRepository.findByEmail(email).map(admin ->{
        	
        	admin = adminMapper.mapAdminRequestToAdmin(adminRequest, admin);
        	
        	
        	admin = adminRepository.save(admin);
        	
        	
              return ResponseEntity.status(HttpStatus.OK)
      				.body(new ResponseStructure<AdminResponse>()
      						.setData(adminMapper.mapAdminResponseToAdmin(admin))
      						.setStatus(HttpStatus.OK.value())
      						.setMessage("Admin Updated"));
              
        }).orElseThrow(() -> new AdminNotMatchException("Admin not found"));

       
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId) {
		
		return adminRepository.findById(adminId).map(admin-> {
			
			admin = adminMapper.mapAdminRequestToAdmin(adminRequest, admin);
			admin = adminRepository.save(admin);
			
			AdminResponse response = adminMapper.mapAdminResponseToAdmin(admin);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setData(response)
							.setMessage("Admin updated")
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new AdminNotMatchException("Admin not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) {
		return adminRepository.findById(adminId).map(admin-> {
			
			AdminResponse response = adminMapper.mapAdminResponseToAdmin(admin);
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AdminResponse>()
							.setData(response)
							.setMessage("Admin Found")
							.setStatus(HttpStatus.FOUND.value()));
		}).orElseThrow(() -> new AdminNotMatchException("Admin not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>>findAllAdmins() {
		
		List<AdminResponse> admins = adminRepository.findAllByAdminType(AdminType.ADMIN).stream().map(admin ->
		adminMapper.mapAdminResponseToAdmin(admin)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<AdminResponse>>()
						.setMessage("Admins Found")
						.setStatus(HttpStatus.FOUND.value())
						.setData(admins));
	}
	
	


	

}
