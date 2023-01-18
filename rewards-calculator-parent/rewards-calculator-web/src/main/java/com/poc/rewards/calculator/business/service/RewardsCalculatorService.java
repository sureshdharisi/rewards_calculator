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

	/**
	 * This service will fetch the rewards configuration details from rewards config
	 * micro service. This will give the reward point values based on purchase
	 * amount.
	 * 
	 * It will also fetch all the transactions done by customer. Using these
	 * transaction, it will calculate total number of rewards points.
	 * 
	 * @param customerId
	 * @return rewardsPoints
	 */

	public Integer calculatePoints(String customerId) {

		List<CustomerTransactionRequest> customerTransactionsList = this.customerTransactionsClient
				.getAllCustomerTransactions(customerId);
		List<RewardsLimitsRequest> rewardsLimitsList = this.rewardsLimitConfigClient.getAllLimitConfigDetails();

		AtomicInteger purchaseAmountValue = new AtomicInteger(0);

		customerTransactionsList.stream().forEach(request -> {
			purchaseAmountValue.addAndGet(request.getTransactionAmt());
		});

		Collections.sort(rewardsLimitsList,
				(reward1, reward2) -> -1 * reward1.getLowerLimit().compareTo(reward2.getLowerLimit()));

		AtomicInteger points = new AtomicInteger(0);

		rewardsLimitsList.stream().filter(limit -> purchaseAmountValue.get() > limit.getLowerLimit()).forEach(limit -> {
			Integer amount=(limit.getUpperLimit()==null||limit.getUpperLimit() > purchaseAmountValue.get())?purchaseAmountValue.get():limit.getUpperLimit();
			points.addAndGet(limit.getPoints() * (amount - limit.getLowerLimit()));
			
		});

		return points.get();

	}

}
