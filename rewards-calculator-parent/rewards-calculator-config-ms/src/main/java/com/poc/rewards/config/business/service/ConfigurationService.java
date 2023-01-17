package com.poc.rewards.config.business.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.rewards.config.dataaccess.entity.RewardsLimitsEntity;
import com.poc.rewards.config.dataaccess.repository.RewardsConfigRepository;
import com.poc.rewards.config.model.request.RewardsLimitsRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

	@Autowired
	private RewardsConfigRepository rewardsLimitsRepository;

	@Autowired
	private Mapper mapper;

	public RewardsLimitsRequest createConfig(RewardsLimitsRequest request) {
		RewardsLimitsEntity entity = this.mapper.map(request, RewardsLimitsEntity.class);
		entity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
		this.rewardsLimitsRepository.save(entity);
		request.setId(entity.getId());
		return request;

	}

	public RewardsLimitsRequest updateConfig(RewardsLimitsRequest request) {
		RewardsLimitsEntity entity = this.rewardsLimitsRepository.findById(request.getId()).get();
		entity.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
		this.rewardsLimitsRepository.save(entity);
		request.setId(entity.getId());
		return request;

	}

	public RewardsLimitsRequest deleteConfig(RewardsLimitsRequest request) {
		RewardsLimitsEntity entity = this.rewardsLimitsRepository.findById(request.getId()).get();
		this.rewardsLimitsRepository.delete(entity);
		return request;

	}

	public List<RewardsLimitsRequest> getAllLimitConfigDetails() {
		List<RewardsLimitsEntity> entityList = this.rewardsLimitsRepository.findAll();
		return entityList.stream().map(entity -> this.mapper.map(entity, RewardsLimitsRequest.class))
				.collect(Collectors.toList());
	}

	public RewardsLimitsRequest getLimitConfigDetails(Integer id) {
		RewardsLimitsEntity entity = this.rewardsLimitsRepository.findById(id).get();
		return this.mapper.map(entity, RewardsLimitsRequest.class);

	}
}
