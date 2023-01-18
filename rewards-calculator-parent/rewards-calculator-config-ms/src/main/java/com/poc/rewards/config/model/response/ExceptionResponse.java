package com.poc.rewards.config.model.response;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionResponse {
	private List<ErrorDetails> errors;
}
