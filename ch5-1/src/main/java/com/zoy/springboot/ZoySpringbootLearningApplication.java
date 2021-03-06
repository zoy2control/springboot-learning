package com.zoy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZoySpringbootLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoySpringbootLearningApplication.class, args);
	}

}

