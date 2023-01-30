package com.poc.rewards.config.business.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.poc.rewards.config.common.constants.RewardsStoreConstants;
import com.poc.rewards.config.common.exception.InvalidDataException;
import com.poc.rewards.config.dataaccess.entity.RewardsLimitsEntity;
import com.poc.rewards.config.dataaccess.repository.RewardsConfigRepository;
import com.poc.rewards.config.model.request.RewardsLimitsRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfigurationService {

	@Autowired
	private RewardsConfigRepository rewardsLimitsRepository;

	@Autowired
	private Mapper mapper;

	/**
	 * This will create the configuration record
	 * @param request
	 * @return
	 */
	public RewardsLimitsRequest createConfig(RewardsLimitsRequest request) {
		RewardsLimitsEntity entity = this.mapper.map(request, RewardsLimitsEntity.class);
		entity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
		this.rewardsLimitsRepository.save(entity);
		request.setId(entity.getId());
		return request;

	}
	
	/**
	 * This will update the configuration record
	 * @param request
	 * @return
	 */

	public RewardsLimitsRequest updateConfig(RewardsLimitsRequest request) {
		RewardsLimitsEntity entity = this.rewardsLimitsRepository.findById(request.getId()).get();
		entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
		this.rewardsLimitsRepository.save(entity);
		request.setId(entity.getId());
		return request;

	}
	
	/**
	 * This will delete the configuration record. If record is not found, then it will throw the exception
	 * @param id
	 */

	public void deleteConfig(Integer id) {
		Optional<RewardsLimitsEntity> entity = this.rewardsLimitsRepository.findById(id);
		if(entity.isPresent()) {
			this.rewardsLimitsRepository.delete(entity.get());
		}else {
			throw new InvalidDataException("CFG100", "Config record is not found");
		}
		
	}
	
	/**
	 * This will return all the configuration records
	 * @return
	 */

	@Cacheable(RewardsStoreConstants.REWARDS_STORE_CACHE_NAME)
	public List<RewardsLimitsRequest> getAllLimitConfigDetails() {
		log.info("Fetching rewards configuration from database");
		List<RewardsLimitsEntity> entityList = this.rewardsLimitsRepository.findAll();
		return entityList.stream().map(entity -> this.mapper.map(entity, RewardsLimitsRequest.class))
				.collect(Collectors.toList());
	}

}
