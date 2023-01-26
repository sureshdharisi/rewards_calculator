package com.poc.rewards.calculator.business.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poc.rewards.calculator.configurations.ClientConfiguration;
import com.poc.rewards.calculator.model.request.RewardsLimitsRequest;

/**
 * This will interact with limits config micro service and gets the configuration details.
 * @author suresh.dharisi
 *
 */
@Service
@FeignClient(value = "limitconfigclient", url = "localhost:8585/rewards/config/", configuration = ClientConfiguration.class)
public interface RewardsLimitConfigClient {


	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<RewardsLimitsRequest> getAllLimitConfigDetails();

}
