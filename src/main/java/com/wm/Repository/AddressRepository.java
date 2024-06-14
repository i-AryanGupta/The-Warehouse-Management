package com.wm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findAllByCity(String city);



}
