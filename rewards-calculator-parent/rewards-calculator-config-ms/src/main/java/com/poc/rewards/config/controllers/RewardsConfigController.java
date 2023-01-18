package com.poc.rewards.config.controllers;

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

import com.poc.rewards.config.business.service.ConfigurationService;
import com.poc.rewards.config.model.request.RewardsLimitsRequest;
import com.poc.rewards.config.model.response.ConfigResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class RewardsConfigController {
	
	@Autowired
	private ConfigurationService rewardsLimitConfigService;
	
	@PostMapping
	public RewardsLimitsRequest createConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	@GetMapping("/all")
	public List<RewardsLimitsRequest> fetchAllConfigDetails(){
		return this.rewardsLimitConfigService.getAllLimitConfigDetails();
	}
	
	
	@PutMapping
	public RewardsLimitsRequest updateConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	@DeleteMapping("/{id}")
	public ConfigResponse deleteConfig(@PathVariable @Valid Integer id) {
		this.rewardsLimitConfigService.deleteConfig(id);
		ConfigResponse response=new ConfigResponse();
		response.setMessage("The configuration "+id+" is deleted successfully");
		return response;
	}
	
	

}
