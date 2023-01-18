package com.poc.rewards.calc.cust.transaction.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.poc.rewards.calc.cust.transaction.common.constants.CustomerTransactionsErrorCodes;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransactionRequest {

	private Integer id;

	@NotNull(message = CustomerTransactionsErrorCodes.CT001)
	private String customerId;

	@NotNull(message = CustomerTransactionsErrorCodes.CT002)
	private Integer transactionAmt;

	@NotNull(message = CustomerTransactionsErrorCodes.CT003)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate transactionDate;

}
