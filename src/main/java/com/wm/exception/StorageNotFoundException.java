package com.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StorageNotFoundException extends RuntimeException {
	
	private String message;

}