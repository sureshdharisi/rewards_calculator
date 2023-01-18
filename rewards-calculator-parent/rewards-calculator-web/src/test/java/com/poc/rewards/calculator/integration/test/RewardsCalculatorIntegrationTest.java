package com.poc.rewards.calculator.integration.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.poc.rewards.calculator.business.service.CustomerTransactionsClient;
import com.poc.rewards.calculator.business.service.RewardsLimitConfigClient;
import com.poc.rewards.calculator.model.request.CustomerTransactionRequest;
import com.poc.rewards.calculator.model.request.RewardsLimitsRequest;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
public class RewardsCalculatorIntegrationTest {

	@MockBean
	private RewardsLimitConfigClient rewardsLimitConfigClient;

	@MockBean
	private CustomerTransactionsClient customerTransactionsClient;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void calculateRewardsPoints() throws Exception {
		
		List<RewardsLimitsRequest> configList=new ArrayList<>();
		
		configList.add(createRewardsLimitsRequest(50,100,1));
		configList.add(createRewardsLimitsRequest(100,null,2));
		
		Mockito.when(rewardsLimitConfigClient.getAllLimitConfigDetails()).thenReturn(configList);
		
		List<CustomerTransactionRequest> transactionList=new ArrayList<>();
		
		transactionList.add(createCustomerTransactionRequest("XYZ",100));
		transactionList.add(createCustomerTransactionRequest("XYZ",150));
		
		Mockito.when(customerTransactionsClient.getAllCustomerTransactions("XYZ")).thenReturn(transactionList);
		
		this.mvc.perform(get("/calculate/XYZ").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("{\"points\":350}"));

	}
	
	private static RewardsLimitsRequest createRewardsLimitsRequest(Integer lowerLimit,Integer upperLimit,Integer points) {
		RewardsLimitsRequest request=new RewardsLimitsRequest();
		request.setLowerLimit(lowerLimit);
		request.setPoints(points);
		request.setUpperLimit(upperLimit);
		return request;
	}
	
	private static CustomerTransactionRequest createCustomerTransactionRequest(String customerID,Integer transactionAmount) {
		CustomerTransactionRequest request=new CustomerTransactionRequest();
		request.setCustomerId(customerID);
		request.setTransactionAmt(transactionAmount);
		request.setTransactionDate(LocalDate.now());
		return request;
	}
}
