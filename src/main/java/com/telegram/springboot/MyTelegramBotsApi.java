package com.telegram.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Service
public class MyTelegramBotsApi extends TelegramBotsApi {

    @Autowired
    public MyTelegramBotsApi(Bot bot){
        try {
            registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}