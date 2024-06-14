package com.wm.responsedto;

import java.util.List;

import com.wm.enums.MaterialTypes;

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
public class StorageResponse {

	private int storageId;
	private String blockName;
	private String section;
	private double capacityInKg;
	
	private List<MaterialTypes> materialTypes;
}
