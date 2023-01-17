package com.poc.rewards.calc.cust.transaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.rewards.calc.cust.transaction.business.service.CustomerTransactionService;
import com.poc.rewards.calc.cust.transaction.model.request.CustomerTransactionRequest;
import com.poc.rewards.calc.cust.transaction.model.response.CustomerTransactionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CustomerTransactionController {
	
	@Autowired
	private CustomerTransactionService customerTransactionService;
	
	@PostMapping
	public CustomerTransactionResponse createPaymentTransaction(@Valid @RequestBody CustomerTransactionRequest request) {
		this.customerTransactionService.createCustomerTransaction(request);
		CustomerTransactionResponse response=new CustomerTransactionResponse();
		response.setTransactionId(request.getId());
		return response;
	}
	
	@GetMapping("/all")
	public List<CustomerTransactionRequest> fetchAllTransactionDetails(){
		return this.customerTransactionService.getAllCustomerTransactions();
	}
	
	@GetMapping("/{id}")
	public CustomerTransactionRequest fetchTransactionDetailsById(@PathVariable @Valid Integer id){
		return this.customerTransactionService.getCustomerTransaction(id);
	}
	
	@GetMapping("/custid/{customerId}")
	public List<CustomerTransactionRequest> fetchTransactionDetailsByCustomerId(@PathVariable @Valid String customerId){
		return this.customerTransactionService.getAllCustomerTransactions(customerId);
	}
	
	@DeleteMapping("/{id}")
	public CustomerTransactionResponse deleteCustomerTransaction(@PathVariable @Valid Integer id) {
		this.customerTransactionService.deleteCustomerTransaction(id);
		CustomerTransactionResponse response=new CustomerTransactionResponse();
		response.setMessage("The transaction "+id+" is deleted successfully");
		return response;
	}
	
	

}
