package com.telegram.springboot;

import ch.qos.logback.core.util.Loader;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class Bot extends TelegramLongPollingBot {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void onUpdateReceived(Update update) {
        Message message =update.getMessage();
        sendMsg(message,onUpdate(message.getText()));
    }

    public String onUpdate(String msg){

        String query="SELECT  c.description FROM Schedule c WHERE concat(c.data_ ,' ',c.group_) = '"+msg+"'";
        if (!msg.isEmpty()) {
            if (msg.equals("/time_now")) {
                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss a zzz");
                return ("Привет, сегодня " + formatForDateNow.format(date));
            } else if (!em.createQuery(query)
                    .getResultList().isEmpty()) {

                return em.createQuery(query)
                        .getResultList().toString();
            } else {
                return "Больше ничего пока не знаю!";
            }
        }
        return null;
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
