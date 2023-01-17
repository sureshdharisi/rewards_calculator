package com.poc.rewards.calculator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.rewards.calculator.business.service.RewardsLimitConfigService;
import com.poc.rewards.calculator.model.request.RewardsLimitsRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class RewardsConfigController {
	
	@Autowired
	private RewardsLimitConfigService rewardsLimitConfigService;
	
	@PostMapping
	public RewardsLimitsRequest createConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	@GetMapping("/all")
	public List<RewardsLimitsRequest> fetchAllConfigDetails(){
		return this.rewardsLimitConfigService.getAllLimitConfigDetails();
	}
	
	@GetMapping("/{id}")
	public RewardsLimitsRequest fetchAllConfigDetailsById(@PathVariable @Valid Integer id){
		return this.rewardsLimitConfigService.getLimitConfigDetails(id);
	}
	
	@PutMapping
	public RewardsLimitsRequest updateConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	@DeleteMapping
	public RewardsLimitsRequest deleteConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	

}
