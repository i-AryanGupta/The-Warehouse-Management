package com.wm.entity;

import java.util.List;

import com.wm.enums.AdminType;
import com.wm.enums.Privilege;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)   // enum values will be in 0 & 1 to convert that into string we use @Enumerated with type
	private AdminType adminType;
	

	
//	void setType(AdminType type){
//		this.type = type;
//		}

}
