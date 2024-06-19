package com.wm.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.wm.enums.MaterialTypes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
	
	@NotEmpty(message = "Product Title should be not empty")
	private String productTitle;
	@NotEmpty(message = "Length In Meters should be not empty")
	private double lengthInMeters;
	@NotEmpty(message = "Breadth In Meters should be not empty")
	private double breadthInMeters;
	@NotEmpty(message = "Height In Meters should be not empty")
	private double heightInMeters;
	@NotEmpty(message = "Weight In Kg should be not empty")
	private double weightInKg;
	@NotEmpty(message = "Quantity Title should be not empty")
	private int quantity;
	
	@NotEmpty(message = "MaterialTypes should be not empty")
	@Enumerated(EnumType.STRING)
	private List<MaterialTypes> materialTypes;
	
//	private LocalDate restockedAt;
	private int sellerId;
	

}
