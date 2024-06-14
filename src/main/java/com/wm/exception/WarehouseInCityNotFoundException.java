package com.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarehouseInCityNotFoundException extends RuntimeException {
	
	private String message;

}
