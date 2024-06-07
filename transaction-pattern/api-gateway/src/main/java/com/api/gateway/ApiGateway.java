package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateway {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(ApiGateway.class);
    }
}