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

	
	@NotEmpty(message = "MaterialTypes is required")
	private List<MaterialTypes> materialTypes;

}
