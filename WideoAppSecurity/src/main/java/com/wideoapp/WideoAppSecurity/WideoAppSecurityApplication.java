package com.wideoapp.WideoAppSecurity;

//import brave.sampler.Sampler;
//import com.wideoapp.WideoAppSecurity.Dao.UserDao;
//import com.wideoapp.WideoAppSecurity.Jwt.JwtInMemoryUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients("com.wideoapp.WideoAppSecurity")
@EnableDiscoveryClient
public class WideoAppSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(WideoAppSecurityApplication.class, args);
	}

//	@Bean
//	public Sampler defaultSampler(){
//		return Sampler.ALWAYS_SAMPLE;
//	}
}
