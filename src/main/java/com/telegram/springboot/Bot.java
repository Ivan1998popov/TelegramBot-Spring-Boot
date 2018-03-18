package com.telegram.springboot;

import ch.qos.logback.core.util.Loader;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.telegram.springboot.Schedule.getDBConnection;


@Service
public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message =update.getMessage();
        if (message!=null&&message.hasText()){
            if(message.getText().equals("/time_now")){
                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss a zzz");
                sendMsg(message,"Привет, сегодня "+formatForDateNow.format(date));
            }else if(message.getText().equals("КТбо3-2 19.03.2018")){
                Connection connection = getDBConnection();
                Statement stmt = null;
                try {
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();
                    ResultSet answer= stmt.executeQuery("select DATA  from TBL_SCHEDULE WHERE DATA = '2018-03-19' " +
                            "AND GROUP_='КТбо3-2'");
                    sendMsg(message,answer.getString("group_"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else {
                sendMsg(message,"Больше ничего пока не знаю!");
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
