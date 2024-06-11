package com.wm.responsedto;

import java.util.List;

import com.wm.enums.AdminType;
import com.wm.enums.Privilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
	
	private int adminId;
	private String name;
	private String email;
	private AdminType adminType;

}
