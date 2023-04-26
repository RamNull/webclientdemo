package com.example.webclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WebclientdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientdemoApplication.class, args);
	}

}
