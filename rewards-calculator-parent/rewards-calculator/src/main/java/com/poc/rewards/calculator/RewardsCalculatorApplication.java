package com.poc.rewards.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RewardsCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsCalculatorApplication.class, args);
	}

}
