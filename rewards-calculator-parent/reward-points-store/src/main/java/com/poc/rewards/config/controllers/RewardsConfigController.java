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
import com.poc.rewards.config.business.service.RefreshCacheService;
import com.poc.rewards.config.model.request.RewardsLimitsRequest;
import com.poc.rewards.config.model.response.ConfigResponse;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class RewardsConfigController {
	
	@Autowired
	private ConfigurationService rewardsLimitConfigService;
	
	@Autowired
	private RefreshCacheService refreshCacheService;
	
	/**
	 * This will accept the request from user and create the configuration record.
	 * @param request
	 * @return
	 */
	@PostMapping
	public RewardsLimitsRequest createConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		log.info("Reward configuration {} is created. Now triggering refresh cache ", request);
		this.refreshCacheService.refreshRewardsConfigCache();
		log.info("Triggered cache");
		return request;
	}
	
	/**
	 * This will return all the configuration records
	 * @return
	 */
	@GetMapping("/all")
	public List<RewardsLimitsRequest> fetchAllConfigDetails(){
		return this.rewardsLimitConfigService.getAllLimitConfigDetails();
	}
	
	/**
	 * This api will be used to update the existing configuration record
	 * @param request
	 * @return
	 */
	
	@PutMapping
	public RewardsLimitsRequest updateConfig(@Valid @RequestBody RewardsLimitsRequest request) {
		this.rewardsLimitConfigService.createConfig(request);
		return request;
	}
	
	/**
	 * This will delete the configuration record for given configuration id.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ConfigResponse deleteConfig(@PathVariable @Valid Integer id) {
		this.rewardsLimitConfigService.deleteConfig(id);
		ConfigResponse response=new ConfigResponse();
		response.setMessage("The configuration "+id+" is deleted successfully");
		return response;
	}
	
	

}
