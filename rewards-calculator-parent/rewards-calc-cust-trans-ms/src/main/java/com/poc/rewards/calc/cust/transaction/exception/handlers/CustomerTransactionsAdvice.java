package com.poc.rewards.calc.cust.transaction.exception.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.poc.rewards.calc.cust.transaction.common.exception.InvalidDataException;
import com.poc.rewards.calc.cust.transaction.model.response.ExceptionResponse;

@ControllerAdvice
public class CustomerTransactionsAdvice {

	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleInvalidDataException(final InvalidDataException exception) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(exception.getErrorCode());
		return error;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleGeneralException(final Exception exception) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode("RC000");
		return error;
	}
}
