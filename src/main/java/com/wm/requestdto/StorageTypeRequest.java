package com.wm.requestdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageTypeRequest {
	
	@NotEmpty(message = "Length is required")
	private double lengthInMeters;
	
	@NotEmpty(message = "breadth is required")
	private double breadthInMeters;
	
	@NotEmpty(message = "Heigth is required")
	private double heightInMeters;
	
	@NotEmpty(message = "Capacity is required")
	private double capacityInKg;

}
