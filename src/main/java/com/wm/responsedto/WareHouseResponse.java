package com.wm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseResponse {
	
	private int wareHouseId;
	private String name;
	private int totalCapacity;
	

}
