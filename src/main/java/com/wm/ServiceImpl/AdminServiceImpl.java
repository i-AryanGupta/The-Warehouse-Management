package com.wm.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.wm.Repository.AdminRepository;
import com.wm.Repository.WareHouseRepository;
import com.wm.Service.AdminService;
import com.wm.entity.Admin;
import com.wm.enums.AdminType;
import com.wm.enums.Privilege;
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


	

}
