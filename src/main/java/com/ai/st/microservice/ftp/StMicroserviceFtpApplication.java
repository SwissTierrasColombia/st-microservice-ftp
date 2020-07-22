package com.ai.st.microservice.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StMicroserviceFtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(StMicroserviceFtpApplication.class, args);
	}

}
