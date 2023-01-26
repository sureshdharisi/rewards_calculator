package com.poc.rewards.calc.cust.transaction.model.response;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionResponse {
	private List<ErrorDetails> errors;
}
