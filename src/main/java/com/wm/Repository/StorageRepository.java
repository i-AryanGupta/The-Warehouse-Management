package com.wm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer>{

	public Storage findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(Double capacityInKg,
			Double lengthInMeters, Double breadthInMeters, Double heightInMeters);
	
	public List<Storage> findAllFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(Double capacityInKg,
			Double lengthInMeters, Double breadthInMeters, Double heightInMeters);
	
}
