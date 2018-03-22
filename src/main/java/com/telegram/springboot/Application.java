package com.telegram.springboot;

import org.h2.tools.DeleteDbFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

import static com.telegram.springboot.Schedule.insertWithStatement;


@SpringBootApplication
public class Application {

	@PersistenceContext
	static EntityManager em;

	public static void main(String[] args) {
		ApiContextInitializer.init();
		try {

			DeleteDbFiles.execute("~", "tbl_schedule", true);
			insertWithStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		TelegramBotsApi telegramBotsApi =new TelegramBotsApi();

		try{
			telegramBotsApi.registerBot(new Bot());
		} catch (TelegramApiException e){
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}



}
