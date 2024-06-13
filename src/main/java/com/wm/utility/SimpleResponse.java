package com.wm.utility;


public class SimpleResponse<T> {
	
	private String message;
	private int status;
	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return status;
		
		
	}
	public SimpleResponse<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public SimpleResponse<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	
	
	
	

}
