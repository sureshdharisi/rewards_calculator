package com.poc.rewards.calculator.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RewardsCalculatorConfiguration {
	
	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

}
