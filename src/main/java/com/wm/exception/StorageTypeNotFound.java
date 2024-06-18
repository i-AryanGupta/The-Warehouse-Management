package com.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StorageTypeNotFound extends RuntimeException{
	private String Message;

}
