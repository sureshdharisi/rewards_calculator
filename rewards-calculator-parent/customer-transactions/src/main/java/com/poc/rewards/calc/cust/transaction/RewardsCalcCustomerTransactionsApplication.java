package com.poc.rewards.calc.cust.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RewardsCalcCustomerTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsCalcCustomerTransactionsApplication.class, args);
	}

}
