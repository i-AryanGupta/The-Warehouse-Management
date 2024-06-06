package com.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MultipleSuperAdminException extends RuntimeException {
	
	private String message;

}
