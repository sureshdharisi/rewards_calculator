package com.poc.rewards.config.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RewardsConfiguration {
	
	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

}
