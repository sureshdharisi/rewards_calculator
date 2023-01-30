package com.poc.rewards.config.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.poc.rewards.config.common.constants.RewardsStoreConstants;

@Configuration
@EnableCaching
public class RewardsConfiguration {
	
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
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(RewardsStoreConstants.REWARDS_STORE_CACHE_NAME);
    }

}
