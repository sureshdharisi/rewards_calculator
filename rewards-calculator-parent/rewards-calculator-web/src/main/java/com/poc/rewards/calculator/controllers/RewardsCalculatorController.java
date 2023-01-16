package com.poc.rewards.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.reward.calculator.exception.InvalidDataException;
import com.coding.reward.calculator.response.RewardsResponse;
import com.coding.reward.calculator.service.RewardsCalculatorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/calculate/")
@Slf4j
public class RewardsCalculatorController {

	@Autowired
	private RewardsCalculatorService service;

	/**
	 * This will calculate the rewards points based on purchase amount
	 * 
	 * <p>
	 * Example:
	 * 
	 * <br/>
	 * http://localhost:8585/rewards/calculate/120.0 -> {"points": 90} <br/>
	 * http://localhost:8080/rewards/calculate/100.0 -> {"points": 50} <br/>
	 * </p>
	 * 
	 * 
	 * Made request params are optional to verify the error scenarios.
	 * 
	 * @param purchaseAmount
	 * @return
	 */
	@GetMapping(path = "{purchaseAmount}")
	public RewardsResponse calculateRewards(@PathVariable(required = true) Double purchaseAmount) {
		log.debug("Purchase amount = {}", purchaseAmount);
		if (purchaseAmount == null) {
			throw new InvalidDataException("RC001", "Purchase amount is required");
		}
		Integer points = service.calculatePoints(purchaseAmount);

		RewardsResponse response = new RewardsResponse(points);

		return response;
	}
}
