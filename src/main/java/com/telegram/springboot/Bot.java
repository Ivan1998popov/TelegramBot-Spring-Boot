package com.telegram.springboot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.h2.value.ValueLob.createBlob;


@Service
public class Bot extends TelegramLongPollingBot {

    @PersistenceContext
    private EntityManager em;

    public static long chat_id;
    @Override
    public void onUpdateReceived(Update update) {
        Message message =update.getMessage();
        chat_id = update.getMessage().getChatId();
        if(onUpdate(message.getText()).equals("Photo")){
            send_Photo(chat_id);
        }else {
            sendMsg(message, onUpdate(message.getText()));
        }
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
            } else if(msg.equals("/pic")) {

                return "Photo";
            }else{
                return "Больше ничего пока не знаю!";
            }
        }
        return null;
    }

    private void send_Photo(long chat_id) {
        String query_map="SELECT  c.image FROM GoogleMaps c WHERE c.id = 1";
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chat_id);
        // sendPhoto.setNewPhoto(new File(em.createQuery(query_map).getResultList().toString()));
        String image = em.createQuery(query_map).getResultList().toString();
        System.out.println(image);
        sendPhoto.setNewPhoto(new File("C:\\Users\\Иван\\Desktop\\Maps\\A.png"));

        try {
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
