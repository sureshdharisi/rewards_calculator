package com.poc.rewards.config.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardsLimitsRequest {
	
	private Integer id;
	
	private Integer lowerLimit;
	
	private Integer upperLimit;
	
	private Integer points;

}
