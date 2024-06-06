package com.wm.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wm.Repository.AdminRepository;
import com.wm.entity.Admin;
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		return adminRepository.findByEmail(username).map(UserDetailImpl::new).orElseThrow();
		
//	return adminRepository.findByEmail(username).map(admin ->
//		
//		new UserDetailImpl(admin)).orElseThrow(() -> new UsernameNotFoundException(" "));
		
		
	}

}
