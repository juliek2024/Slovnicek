package com.example.Slovnicek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SlovnicekApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlovnicekApplication.class, args);
	}

}