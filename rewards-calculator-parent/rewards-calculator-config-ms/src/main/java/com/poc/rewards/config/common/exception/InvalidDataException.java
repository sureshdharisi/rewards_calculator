package com.poc.rewards.config.common.exception;

public class InvalidDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1878247561169420904L;

	private String message;
	
	private String errorCode;

	public InvalidDataException(String errorCode,String message) {
		super(message);
		this.message = message;
		this.errorCode=errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
	
}
