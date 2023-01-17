package com.poc.rewards.calculator.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.rewards.calculator.dataaccess.entity.RewardsLimitsEntity;

@Repository
public interface RewardsLimitsRepository extends ListCrudRepository<RewardsLimitsEntity, Integer>,JpaRepository<RewardsLimitsEntity, Integer>{

}
