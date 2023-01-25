package com.poc.rewards.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.rewards.calculator.business.service.RewardsCalculatorService;
import com.poc.rewards.calculator.common.exception.InvalidDataException;
import com.poc.rewards.calculator.model.response.RewardsResponse;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/calculate/")
@Slf4j
public class RewardsCalculatorController {

	@Autowired
	private RewardsCalculatorService service;

	/**
	 * This will calculate the rewards points based on purchase amount by the customer
	 * 
	 * <p>
	 * Example:
	 * 
	 * <br/>
	 * http://localhost:8686/rewards/calculate/ABC -> {"points": 250} <br/>
	 * </p>
	 * 
	 * 
	 * Made request params are optional to verify the error scenarios.
	 * 
	 * @param customer id
	 * @return
	 */
	@GetMapping(path = "{customerId}")
	public RewardsResponse calculateRewards(@PathVariable String customerId) {
		log.debug("Customer ID = {}", customerId);
		if (StringUtils.isBlank(customerId)) {
			throw new InvalidDataException("RC001", "Customer ID is required");
		}
		Integer points = service.calculatePoints(customerId);

		RewardsResponse response = new RewardsResponse(points);

		return response;
	}
}
