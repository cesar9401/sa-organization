package com.cesar31.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SaOrganizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaOrganizationApplication.class, args);
	}

}
