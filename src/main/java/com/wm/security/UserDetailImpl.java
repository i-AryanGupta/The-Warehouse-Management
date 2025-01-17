package com.wm.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wm.entity.Admin;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImpl implements UserDetails{

	private Admin admin;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admin.getAdminType().getPrivileges().stream().map(privilege ->
		new SimpleGrantedAuthority(privilege.name())).toList();
		// stream.collection.simplegrandtedauthority will be return
		
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
		
	}

	@Override
	public String getUsername() {
		return admin.getEmail();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
