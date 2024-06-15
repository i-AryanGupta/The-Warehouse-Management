package com.wm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	

}
