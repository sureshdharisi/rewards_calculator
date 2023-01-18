package com.poc.rewards.config.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.poc.rewards.config.BaseWebTestCase;
import com.poc.rewards.config.model.request.RewardsLimitsRequest;
import com.poc.rewards.config.model.response.ConfigResponse;
import com.poc.rewards.config.model.response.ErrorDetails;
import com.poc.rewards.config.model.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RewardsConfigControllerWebTest extends BaseWebTestCase{

	@Test
	public void testCreateConfig() {
		RewardsLimitsRequest request =createRewardsLimitsRequest();
		RewardsLimitsRequest response = this.restTemplate.postForObject(
				"http://localhost:" + port + "/rewards/config/",request, RewardsLimitsRequest.class);
		log.debug("Response = {}", response);
		assertThat(response.getId()).isNotNull();
	}
	
	@Test
	public void testDeletePaymentTransactionForError() {
		ResponseEntity<ExceptionResponse> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/rewards/config/220",HttpMethod.DELETE,null, ExceptionResponse.class);
		log.debug("Response = {}", response);
		ExceptionResponse errorResponse=response.getBody();
		assertThat(errorResponse.getErrors().get(0).getErrorCode()).isEqualTo("CFG100");
		assertThat(errorResponse.getErrors().get(0).getErrorMessage()).isEqualTo("Config record is not found");
	}
	
	@ParameterizedTest
	@MethodSource("inputToCreateConfigurations")
	public void testCreatePaymentTransactionForErrors(RewardsLimitsRequest request,String expectedErrorCode,String expectedErrorMessage) {
		ExceptionResponse response = this.restTemplate.postForObject(
				"http://localhost:" + port + "/rewards/config/",request, ExceptionResponse.class);
		ErrorDetails errorDetails=response.getErrors().get(0);
		assertThat(errorDetails.getErrorCode()).isEqualTo(expectedErrorCode);
		assertThat(errorDetails.getErrorMessage()).isEqualTo(expectedErrorMessage);
	}
	
	private static Stream<Arguments> inputToCreateConfigurations() {
		return Stream.of(Arguments.of(createRewardsLimitsRequest(null,100,10),"CFG001","lowerLimit is required"), 
				Arguments.of(createRewardsLimitsRequest(50,null,null),"CFG003","points is required"));
	}
	
	@Test
	@Sql("classpath:test-data.sql")
	public void testGetAllConfigurations() {
		List<RewardsLimitsRequest> response=this.restTemplate.getForObject(
				"http://localhost:" + port + "/rewards/config/all", List.class);
		log.debug("Response = {}", response);
		assertThat(response.size()).isEqualTo(3);
		
	}
	
	@Test
	@Sql("classpath:test-data-for-delete.sql")
	public void testDeletePaymentTransaction() {
		ResponseEntity<ConfigResponse> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/rewards/config/210",HttpMethod.DELETE,null, ConfigResponse.class);
		log.debug("Response = {}", response);
		assertThat(response.getBody().getMessage()).isEqualTo("The configuration 210 is deleted successfully");
	}
	
	private static RewardsLimitsRequest createRewardsLimitsRequest() {
		RewardsLimitsRequest request=new RewardsLimitsRequest();
		request.setLowerLimit(50);
		request.setPoints(1);
		request.setUpperLimit(100);
		return request;
	}
	
	private static RewardsLimitsRequest createRewardsLimitsRequest(Integer lowerLimit,Integer upperLimit,Integer points) {
		RewardsLimitsRequest request=new RewardsLimitsRequest();
		request.setLowerLimit(lowerLimit);
		request.setPoints(points);
		request.setUpperLimit(upperLimit);
		return request;
	}
}
