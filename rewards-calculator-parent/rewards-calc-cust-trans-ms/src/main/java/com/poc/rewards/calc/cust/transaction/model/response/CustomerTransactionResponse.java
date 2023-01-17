package com.poc.rewards.calc.cust.transaction.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTransactionResponse {

	private Integer transactionId;
	
	private String message;

}
