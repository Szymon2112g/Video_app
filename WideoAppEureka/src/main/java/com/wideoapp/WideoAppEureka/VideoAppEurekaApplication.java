package com.wideoapp.WideoAppEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VideoAppEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoAppEurekaApplication.class, args);
	}

}
