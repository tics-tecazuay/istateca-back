package com.istateca.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IstatecaAppApplication {


	public static void main(String[] args) {
		System.out.println("prueba 8/8/23 - 21:05 <<<<<------------------");
		SpringApplication.run(IstatecaAppApplication.class, args);

	}

}
