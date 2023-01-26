package com.poc.rewards.calc.cust.transaction.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.poc.rewards.calc.BaseWebTestCase;
import com.poc.rewards.calc.cust.transaction.model.request.CustomerTransactionRequest;
import com.poc.rewards.calc.cust.transaction.model.response.CustomerTransactionResponse;
import com.poc.rewards.calc.cust.transaction.model.response.ErrorDetails;
import com.poc.rewards.calc.cust.transaction.model.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerTransactionControllerWebTest extends BaseWebTestCase{
	
	@Test
	public void testCreatePaymentTransaction() {
		CustomerTransactionRequest request =createCustomerTransactionRequest("ABCD",100);
		CustomerTransactionResponse response = this.restTemplate.postForObject(
				"http://localhost:" + port + "/customer/transaction/",request, CustomerTransactionResponse.class);
		log.debug("Response = {}", response);
		assertThat(response.getTransactionId()).isNotNull();
	}
	
	@Test
	public void testDeletePaymentTransactionForError() {
		ResponseEntity<ExceptionResponse> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/customer/transaction/220",HttpMethod.DELETE,null, ExceptionResponse.class);
		log.debug("Response = {}", response);
		ExceptionResponse errorResponse=response.getBody();
		assertThat(errorResponse.getErrors().get(0).getErrorCode()).isEqualTo("CT100");
		assertThat(errorResponse.getErrors().get(0).getErrorMessage()).isEqualTo("Transaction record is not found");
	}
	
	@ParameterizedTest
	@MethodSource("inputToCreateTransactions")
	public void testCreatePaymentTransactionForErrors(CustomerTransactionRequest request,String expectedErrorCode,String expectedErrorMessage) {
		ExceptionResponse response = this.restTemplate.postForObject(
				"http://localhost:" + port + "/customer/transaction/",request, ExceptionResponse.class);
		ErrorDetails errorDetails=response.getErrors().get(0);
		assertThat(errorDetails.getErrorCode()).isEqualTo(expectedErrorCode);
		assertThat(errorDetails.getErrorMessage()).isEqualTo(expectedErrorMessage);
	}
	
	private static Stream<Arguments> inputToCreateTransactions() {
		return Stream.of(Arguments.of(createCustomerTransactionRequest(null,100),"CT001","Customer ID is required"), 
				Arguments.of(createCustomerTransactionRequest("ABC",null),"CT002","Transaction amount is required"),
				Arguments.of(createCustomerTransactionRequest("ABC",100,null),"CT003","Transaction date is required"));
	}
	
	@Test
	@Sql("classpath:test-data.sql")
	public void testGetAllTransactions() {
		List<CustomerTransactionRequest> response=this.restTemplate.getForObject(
				"http://localhost:" + port + "/customer/transaction/custid/XYZ", List.class);
		log.debug("Response = {}", response);
		assertThat(response.size()).isEqualTo(1);
		
	}
	
	@Test
	@Sql("classpath:test-data-for-delete.sql")
	public void testDeletePaymentTransaction() {
		ResponseEntity<CustomerTransactionResponse> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/customer/transaction/210",HttpMethod.DELETE,null, CustomerTransactionResponse.class);
		log.debug("Response = {}", response);
		assertThat(response.getBody().getMessage()).isEqualTo("The transaction 210 is deleted successfully");
	}
	
	private static CustomerTransactionRequest createCustomerTransactionRequest(String customerID,Integer transactionAmount) {
		CustomerTransactionRequest request=new CustomerTransactionRequest();
		request.setCustomerId(customerID);
		request.setTransactionAmt(transactionAmount);
		request.setTransactionDate(LocalDate.now());
		return request;
	}
	
	private static CustomerTransactionRequest createCustomerTransactionRequest(String customerID,Integer transactionAmount,LocalDate localeDate) {
		CustomerTransactionRequest request=new CustomerTransactionRequest();
		request.setCustomerId(customerID);
		request.setTransactionAmt(transactionAmount);
		request.setTransactionDate(localeDate);
		return request;
	}
	
}
