package com.poc.rewards.calc.cust.transaction.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerTransactionsConfiguration {
	
	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

}
