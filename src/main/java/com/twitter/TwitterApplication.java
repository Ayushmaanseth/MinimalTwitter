package com.twitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
