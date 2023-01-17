package com.poc.rewards.calculator.business.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.rewards.calculator.model.request.RewardsLimitsRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardsCalculatorService {

	@Autowired
	private RewardsLimitConfigService rewardsLimitConfigService;

	public Integer calculatePoints(Double purchaseAmount) {

		List<RewardsLimitsRequest> rewardsLimitsList = this.rewardsLimitConfigService.getAllLimitConfigDetails();

		Collections.sort(rewardsLimitsList,
				(reward1, reward2) -> -1 * reward1.getLowerLimit().compareTo(reward2.getLowerLimit()));

		AtomicInteger points = new AtomicInteger(0);

		rewardsLimitsList.forEach(limit -> {
			if (purchaseAmount > limit.getLowerLimit()) {
				if (limit.getUpperLimit() != null) {
					if (limit.getUpperLimit() > purchaseAmount.intValue()) {
						points.addAndGet(limit.getPoints() * (purchaseAmount.intValue() - limit.getLowerLimit()));
					} else {
						points.addAndGet(limit.getPoints() * (limit.getUpperLimit() - limit.getLowerLimit()));
					}

				} else {
					points.addAndGet(limit.getPoints() * (purchaseAmount.intValue() - limit.getLowerLimit()));
				}
			}

		});

		return points.get();

	}

}
