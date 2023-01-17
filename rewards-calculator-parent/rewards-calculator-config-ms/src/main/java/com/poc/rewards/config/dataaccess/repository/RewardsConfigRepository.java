package com.poc.rewards.config.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.rewards.config.dataaccess.entity.RewardsLimitsEntity;

@Repository
public interface RewardsConfigRepository extends ListCrudRepository<RewardsLimitsEntity, Integer>,JpaRepository<RewardsLimitsEntity, Integer>{

}
