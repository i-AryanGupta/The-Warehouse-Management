package com.wm.requestdto;

import java.util.List;

import com.wm.enums.MaterialTypes;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageRequest {

	@NotEmpty(message = "blockName is required")
	private String blockName;
	@NotEmpty(message = "section is required")
	private String section;
	@NotEmpty(message = "lengthInMt is required")
	private double lengthInMeters;
	@NotEmpty(message = "breadthInMt is required")
	private double breadthInMeters;
	@NotEmpty(message = "heightInMt is required")
	private double heightInMeters;
	@NotEmpty(message = "capacityInKg is required")
	private double capacityInKg;
	

	private List<MaterialTypes> materialTypes;
}
