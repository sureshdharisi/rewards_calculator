package com.poc.rewards.config.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.poc.rewards.config.common.constants.RewardsStoreConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RefreshCacheService {
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	ConfigurationService configurationService;

	@Async
	public void refreshRewardsConfigCache() {
		log.info("Started refreshing cache");
		cacheManager.getCache(RewardsStoreConstants.REWARDS_STORE_CACHE_NAME).clear();
		log.info("Cache cleared from region {}",RewardsStoreConstants.REWARDS_STORE_CACHE_NAME);
		configurationService.getAllLimitConfigDetails();
		log.info("Reloaded the cache");
	}
}
