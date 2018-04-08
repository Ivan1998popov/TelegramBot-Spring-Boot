package com.telegram.springboot;

import com.telegram.springboot.Json.WeatherTaganrog;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.swing.UIManager.get;
import static org.apache.tomcat.util.IntrospectionUtils.capitalize;


@Service
public class Bot extends TelegramLongPollingBot {

    @PersistenceContext
    private EntityManager em;

    public static long chat_id;
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        chat_id = update.getMessage().getChatId();
        String answer = onUpdate(message.getText());
        if (answer.matches("\\S")) {
            if (!answer.matches("[0-9]")) {
                answer = capitalize(answer);
            }
            send_Photo(chat_id, answer);
        } else {
            sendMsg(message, answer);
        }
    }

    public String onUpdate(String msg){

        String query="SELECT  c.description FROM Schedule c WHERE concat(c.data_ ,' ',c.group_) = '"+msg+"'";

        if (!msg.isEmpty()) {
            if (msg.equals("Время")) {
                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss a zzz");
                return ("Привет, сегодня " + formatForDateNow.format(date));
            } else if (!em.createQuery(query)
                    .getResultList().isEmpty()) {

                return em.createQuery(query)
                        .getResultList().toString();
            } else if(msg.matches("[Гг]де находится ((корпус [АаГгДдИиКк])|(общежитие №[1-7]))")) {
                char ch=msg.charAt(msg.length()-1);
                return String.valueOf(ch);
            }else if(msg.matches("[Кк]акая погода сегодня")){
                Client client = ClientBuilder.newBuilder().build();
                WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather?q=Taganrog,ru&units=metric&appid=293da20ad6da8e2bb2974cc9760fbf87");
               /* javax.ws.rs.core.Response jsonResponse = client.target("http://api.openweathermap.org/data/" +
                        "2.5/weather?q=Taganrog,ru&units=metric&appid=2" +
                        "93da20ad6da8e2bb2974cc9760fbf87").request(String.valueOf(MediaType.APPLICATION_JSON)).get();*/
                try (Response response = target.request().get()) {

                    //List<WeatherTaganrog> weather = response.readEntity(new GenericType<List<WeatherTaganrog>>(){})
                    // WeatherTaganrog weather = jsonResponse.readEntity(WeatherTaganrog.class);
                    String weather = response.readEntity(String.class);

                    return weather.toString();
                }
            }else  if(msg.equals("/help")) {

                return "Привет, я могу тебе помочь: " +
                        "\n узнать время (Время) " +
                        "\n узнать расписание ЮФУ (19.03.18 КТбо3-2) " +
                        "\n узнать местоположение корпуса ЮФУ(Где находится корпус А) " +
                        "\n узнать местоположение общежития (Где находится общежитие №1)";

            }else{
                return "Больше ничего пока не знаю!";
            }
        }
        return null;
    }

    private void send_Photo(long chat_id,String index) {
        String query_map="SELECT  c.url FROM GoogleMaps c WHERE c.index = '"+index+"'";
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chat_id);
        String image = em.createQuery(query_map).getSingleResult().toString();
        sendPhoto.setPhoto(image);
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
