package com.wm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	

}
