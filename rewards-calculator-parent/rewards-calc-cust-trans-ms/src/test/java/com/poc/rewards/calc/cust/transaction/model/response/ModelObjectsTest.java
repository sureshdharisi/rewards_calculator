package com.poc.rewards.calc.cust.transaction.model.response;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.rewards.calc.cust.transaction.model.request.CustomerTransactionRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelObjectsTest {
	
	@Test
	public void testRequestObject() throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		CustomerTransactionRequest request=new CustomerTransactionRequest();
		request.setCustomerId("ABC");
		request.setTransactionAmt(100);
		//request.setCreatedDate(LocalDate.now());
		log.debug(mapper.writeValueAsString(request));
	}

}
