package com.wm.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.wm.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
	
	private int productId;
	private String productTitle;
	private double weightInKg;
	private int quantity;
	private List<MaterialTypes> materialTypes;
	
	private LocalDate restockedAt;
	
	private int sellerId;

}
