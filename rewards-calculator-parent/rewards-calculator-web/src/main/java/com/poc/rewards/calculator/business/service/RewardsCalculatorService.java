package com.poc.rewards.calculator.business.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.rewards.calculator.model.request.CustomerTransactionRequest;
import com.poc.rewards.calculator.model.request.RewardsLimitsRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardsCalculatorService {

	@Autowired
	private RewardsLimitConfigClient rewardsLimitConfigClient;
	
	@Autowired
	private CustomerTransactionsClient customerTransactionsClient;

	public Integer calculatePoints(String customerId) {

		List<CustomerTransactionRequest> customerTransactionsList=this.customerTransactionsClient.getAllCustomerTransactions(customerId);
		List<RewardsLimitsRequest> rewardsLimitsList = this.rewardsLimitConfigClient.getAllLimitConfigDetails();
		
		AtomicInteger purchaseAmountValue=new AtomicInteger(0);
		
		customerTransactionsList.stream().forEach(request->{
			purchaseAmountValue.addAndGet(request.getTransactionAmt());
		});

		Collections.sort(rewardsLimitsList,
				(reward1, reward2) -> -1 * reward1.getLowerLimit().compareTo(reward2.getLowerLimit()));

		AtomicInteger points = new AtomicInteger(0);

		rewardsLimitsList.forEach(limit -> {
			if (purchaseAmountValue.get() > limit.getLowerLimit()) {
				if (limit.getUpperLimit() != null) {
					if (limit.getUpperLimit() > purchaseAmountValue.get()) {
						points.addAndGet(limit.getPoints() * (purchaseAmountValue.get() - limit.getLowerLimit()));
					} else {
						points.addAndGet(limit.getPoints() * (limit.getUpperLimit() - limit.getLowerLimit()));
					}

				} else {
					points.addAndGet(limit.getPoints() * (purchaseAmountValue.get() - limit.getLowerLimit()));
				}
			}

		});

		return points.get();

	}

}
