package com.poc.rewards.calculator.business.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poc.rewards.calculator.configurations.ClientConfiguration;
import com.poc.rewards.calculator.model.request.CustomerTransactionRequest;

/**
 * This will interact with customer transaction micro service and gets the details
 * @author suresh.dharisi
 *
 */
@Service
@FeignClient(value = "customertransactionsclient", url = "localhost:8686/customer/transaction/", configuration = ClientConfiguration.class)
public interface CustomerTransactionsClient {


	@RequestMapping(method = RequestMethod.GET, value = "/custid/{customerId}")
	public List<CustomerTransactionRequest> getAllCustomerTransactions(@PathVariable("customerId") String customerId);

}
