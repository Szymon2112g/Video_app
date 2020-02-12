package com.wideoapp.WideoAppFilesStore;

import brave.sampler.Sampler;
import com.wideoapp.WideoAppFilesStore.Storage.StorageProperties;
import com.wideoapp.WideoAppFilesStore.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(StorageProperties.class)
public class WideoAppFilesStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WideoAppFilesStoreApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
}
