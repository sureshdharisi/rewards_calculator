package com.poc.rewards.calculator.business.service;

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

	public Integer calculateRewardsPoints(String customerId) {

		// Get all the transactions for the customer using customer-transactions micro
		// service
		List<CustomerTransactionRequest> customerTransactionsList = this.customerTransactionsClient
				.getAllCustomerTransactions(customerId);

		// Get all available configurations
		List<RewardsLimitsRequest> rewardsLimitsList = this.rewardsLimitConfigClient.getAllLimitConfigDetails();

		AtomicInteger points = new AtomicInteger(0);

		// Stream each transaction and calculate the reward points for each transaction.
		// After getting the reward points for each transaction, add the result to
		// "points" variable
		// which is holding total reward points for the given customer.
		customerTransactionsList.forEach(transaction -> {
			points.getAndAdd(calculateRewardPoints(transaction, rewardsLimitsList));
		});

		// return the reward points
		return points.get();

	}

	private Integer calculateRewardPoints(CustomerTransactionRequest transaction,
			List<RewardsLimitsRequest> rewardsLimitsList) {
		AtomicInteger points = new AtomicInteger(0);
		// Reward points calculation logic
		// 1. Filter the configuration objects based on transaction amount. If
		// transaction amount is less than the lower limit,
		// then we don't need to consider that configuration object.
		rewardsLimitsList.stream().filter(limit -> transaction.getTransactionAmt() > limit.getLowerLimit())
				.forEach(limit -> {
					Integer amount = (limit.getUpperLimit() == null
							|| limit.getUpperLimit() > transaction.getTransactionAmt())
									? transaction.getTransactionAmt()
									: limit.getUpperLimit();
					points.addAndGet(limit.getPoints() * (amount - limit.getLowerLimit()));
				});

		return points.get();
	}

}
