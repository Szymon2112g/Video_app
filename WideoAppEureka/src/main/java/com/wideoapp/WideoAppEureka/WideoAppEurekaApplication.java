package com.wideoapp.WideoAppEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WideoAppEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WideoAppEurekaApplication.class, args);
	}

}
