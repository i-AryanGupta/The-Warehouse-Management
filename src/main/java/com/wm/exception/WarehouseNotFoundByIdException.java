package com.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WarehouseNotFoundByIdException extends RuntimeException {

	private String message;
}
