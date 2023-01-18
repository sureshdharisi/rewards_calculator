package com.poc.rewards.calc.cust.transaction.exception.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.poc.rewards.calc.cust.transaction.common.exception.InvalidDataException;
import com.poc.rewards.calc.cust.transaction.model.response.ErrorDetails;
import com.poc.rewards.calc.cust.transaction.model.response.ExceptionResponse;

@ControllerAdvice
public class CustomerTransactionsAdvice {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleInvalidDataException(final InvalidDataException exception) {
		ExceptionResponse errorResponse = new ExceptionResponse();
		ErrorDetails error = createErrorDetails(exception.getErrorCode(), exception.getMessage());
		errorResponse.setErrors(Arrays.asList(error));
		return errorResponse;
	}

	private ErrorDetails createErrorDetails(String code, String message) {
		ErrorDetails error = new ErrorDetails();
		error.setErrorMessage(message);
		error.setErrorCode(code);
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleGeneralException(final Exception exception) {
		ExceptionResponse error = new ExceptionResponse();
		List<ErrorDetails> errorsList = new ArrayList<>();
		if (exception instanceof MethodArgumentNotValidException) {
			List<FieldError> fieldErrors = ((MethodArgumentNotValidException) exception).getBindingResult()
					.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errorsList.add(createErrorDetails(fieldError.getDefaultMessage(),
						messageSource.getMessage(fieldError.getDefaultMessage(), null, Locale.US)));
			}
		}
		error.setErrors(errorsList);
		return error;
	}
}
