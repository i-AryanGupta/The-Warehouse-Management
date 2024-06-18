package com.wm.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {


	public Optional<Client> findByEmail(String username);

	

}
