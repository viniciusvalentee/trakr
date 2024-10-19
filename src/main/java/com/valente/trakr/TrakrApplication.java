package com.valente.trakr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrakrApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrakrApplication.class, args);
	}

}
