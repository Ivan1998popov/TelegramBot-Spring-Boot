package com.telegram.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class Application extends TelegramLongPollingBot{

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi =new TelegramBotsApi();

		try{
			telegramBotsApi.registerBot(new Application());
		} catch (TelegramApiException e){
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void onUpdateReceived(Update update) {
		Message message =update.getMessage();
		if (message!=null&&message.hasText()){
			if(message.getText().equals("/time_now")){
				Date date = new Date();
				SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss a zzz");
				sendMsg(message,"Привет, сегодня "+formatForDateNow.format(date));
			}else{
				sendMsg(message,"Я тестовый бот");
			}
		}
	}

	private void sendMsg(Message message, String s) {
		SendMessage sendMessage =new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(s);
		try{
			sendMessage(sendMessage);
		}catch (TelegramApiException e){
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return "Ivan777_bot";
	}

	@Override
	public String getBotToken() {
		return "555686449:AAH9vle-Bbj-jYeWSArxeiDa_lvL0vb1h_0";
	}
}
