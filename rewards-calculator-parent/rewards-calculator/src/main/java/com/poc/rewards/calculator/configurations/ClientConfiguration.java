package com.poc.rewards.calculator.configurations;

import org.springframework.context.annotation.Bean;

import feign.okhttp.OkHttpClient;

public class ClientConfiguration {

	@Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
