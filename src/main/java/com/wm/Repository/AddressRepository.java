package com.wm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
