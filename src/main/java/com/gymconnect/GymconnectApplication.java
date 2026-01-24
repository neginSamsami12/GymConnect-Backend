package com.gymconnect;

import com.gymconnect.config.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class GymconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymconnectApplication.class, args);
	}

}
