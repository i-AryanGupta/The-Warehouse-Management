package com.wm.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wm.entity.Admin;
import com.wm.enums.AdminType;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	boolean existsByAdminType(AdminType superAdmin);

	Optional<Admin> findByEmail(String username);


}
