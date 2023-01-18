package com.poc.rewards.calc.cust.transaction.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class CustomerTransactionsConfiguration {
	
	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	

}
