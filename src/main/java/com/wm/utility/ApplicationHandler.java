package com.wm.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wm.exception.AddressNotFoundException;
import com.wm.exception.AdminNotMatchException;
import com.wm.exception.WarehouseInCityNotFoundException;
import com.wm.exception.MultipleSuperAdminException;
import com.wm.exception.StorageNotFoundException;
import com.wm.exception.WarehouseNotFoundByIdException;

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
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleWarehouseNotFoundByIdException(WarehouseNotFoundByIdException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Warehouse not present");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAdminNotMatchException(AdminNotMatchException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Admin not Found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAddressNotFoundException(AddressNotFoundException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Address not Found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleStorageNotFoundException( StorageNotFoundException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Storage not found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleWarehouseInCityNotFoundException(WarehouseInCityNotFoundException ex)
	{
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Warehouse not Found in city");
	}
	

}
