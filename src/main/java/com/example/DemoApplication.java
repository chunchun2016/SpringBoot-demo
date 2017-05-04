package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	@PostConstruct
	public void initApplication() throws IOException {
		logger.info("Running with Spring profile(s) : {}");
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
