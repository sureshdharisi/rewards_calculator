package com.poc.rewards.calc.cust.transaction.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransactionRequest {

	private Integer id;

	@NotNull
	private String customerId;

	@NotNull
	private Integer transactionAmt;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate transactionDate;

}
