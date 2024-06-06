package com.wm.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wm.exception.MultipleSuperAdminException;

@RestControllerAdvice
public class ApplicationHandler {
	
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		return ResponseEntity.status(status)
				.body(new ErrorStructure<String>()
						.setStatus(status.value())
						.setRootCause(rootCause)
						.setMessage(message));
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleMultipleSuperAdminException(MultipleSuperAdminException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Super Admin already exist");
	}

}
