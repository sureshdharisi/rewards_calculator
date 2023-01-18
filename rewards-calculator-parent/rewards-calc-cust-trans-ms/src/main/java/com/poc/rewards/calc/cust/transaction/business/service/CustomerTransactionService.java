package com.poc.rewards.calc.cust.transaction.business.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.rewards.calc.cust.transaction.common.exception.InvalidDataException;
import com.poc.rewards.calc.cust.transaction.dataaccess.entity.CustomerTransactionEntity;
import com.poc.rewards.calc.cust.transaction.dataaccess.repository.CustomerTransactionsRepository;
import com.poc.rewards.calc.cust.transaction.model.request.CustomerTransactionRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerTransactionService {

	@Autowired
	private CustomerTransactionsRepository customerTransactionsRepository;

	@Autowired
	private Mapper mapper;

	public CustomerTransactionRequest createCustomerTransaction(CustomerTransactionRequest request) {
		CustomerTransactionEntity entity = this.mapper.map(request, CustomerTransactionEntity.class);
		entity.setTransactionDateTime(Timestamp.valueOf(request.getTransactionDate().atStartOfDay()));
		entity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		this.customerTransactionsRepository.save(entity);
		request.setId(entity.getId());
		return request;

	}

	public void deleteCustomerTransaction(Integer id) {
		Optional<CustomerTransactionEntity> entity = this.customerTransactionsRepository.findById(id);
		if(entity.isPresent()) {
			this.customerTransactionsRepository.delete(entity.get());
		}else {
			throw new InvalidDataException("CT100", "Transaction record is not found");
		}
		
	}

	public List<CustomerTransactionRequest> getAllCustomerTransactions(String customerId) {
		List<CustomerTransactionEntity> entityList = this.customerTransactionsRepository.findByCustomerId(customerId);
		return entityList.stream().map(entity -> {
			CustomerTransactionRequest request = this.mapper.map(entity, CustomerTransactionRequest.class);
			request.setTransactionDate(entity.getTransactionDateTime().toLocalDateTime().toLocalDate());
			return request;
		}).collect(Collectors.toList());
	}


}
