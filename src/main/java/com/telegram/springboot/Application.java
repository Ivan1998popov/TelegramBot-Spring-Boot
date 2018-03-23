package com.telegram.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Application {

	// magic time!
	static  {
		ApiContextInitializer.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
