package com.poc.rewards.config.model.request;

import com.poc.rewards.config.common.constants.ConfigErrorCodes;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardsLimitsRequest {
	
	private Integer id;
	
	@NotNull(message = ConfigErrorCodes.CFG001)
	private Integer lowerLimit;
	
	private Integer upperLimit;
	
	@NotNull(message = ConfigErrorCodes.CFG003)
	private Integer points;

}
