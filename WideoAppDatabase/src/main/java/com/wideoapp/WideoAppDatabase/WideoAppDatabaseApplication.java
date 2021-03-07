package com.wideoapp.WideoAppDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WideoAppDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WideoAppDatabaseApplication.class, args);
	}
}
