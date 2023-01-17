package com.poc.rewards.calc.cust.transaction.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.rewards.calc.cust.transaction.dataaccess.entity.CustomerTransactionEntity;

@Repository
public interface CustomerTransactionsRepository extends ListCrudRepository<CustomerTransactionEntity, Integer>,JpaRepository<CustomerTransactionEntity, Integer>{

}
