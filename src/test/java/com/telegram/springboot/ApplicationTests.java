package com.telegram.springboot;


import com.telegram.springboot.Json.Weather;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.telegram.telegrambots.ApiContextInitializer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests extends TestCase {

	// magic time!
	static  {
		ApiContextInitializer.init();
	}

	@Autowired
	private PlatformTransactionManager transactionManager;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void contextLoads() {

		System.out.println(em.createQuery("SELECT c FROM Schedule c").getResultList());
		String s="19.03.2018 КТбо3-2";
		System.out.println(em.createQuery("SELECT c.description  FROM Schedule c WHERE concat(c.data_,' ',c.group_) ='"+s+"'")
				.getResultList());
	}

	@Autowired
	Bot bot;

	@Test
	public void dateNow(){

		Date date = new Date();
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' hh:mm:ss a zzz");
		Assert.assertEquals(bot.onUpdate("Время"),"Привет, сегодня " + formatForDateNow.format(date));
	}

	@Test
	public void testSchedule(){
		String s="19.03.2018 КТбо3-2";
		Assert.assertEquals(bot.onUpdate(s),em.createQuery("SELECT c.description  " +
				"FROM Schedule c WHERE concat(c.data_,' ',c.group_) ='"+s+"'")
				.getResultList().toString());
	}

	@Test
	public void testGoogleMap(){
		String s="где находится корпус Г";
		Assert.assertEquals(bot.onUpdate(s),"Г");
	}
	@Test
	public void testHelp(){
		Assert.assertEquals(bot.onUpdate("/help"),   "Привет, я могу тебе помочь: " +
				"\n узнать время (Время) " +
				"\n узнать расписание ЮФУ (19.03.2018 КТбо3-2) " +
				"\n узнать местоположение корпуса ЮФУ(Где находится корпус А) " +
				"\n узнать местоположение общежития (Где находится общежитие №1)"+
				"\n узнать какая погода в Таганроге сегодня (Какая погода сегодня)"+
				"\n узнать новости ЮФУ (Новости ЮФУ)"+
				"\n проходной балл для поступления на указанную специальность (Направление 27.03.04," +
				"Направление 11.05.04) ");
	}

	@Test
	public void testHelp2(){
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather?" +
				"q=Taganrog,ru&units=metric&appid=293da20ad6da8e2bb2974cc9760fbf87");
		Response response = target.request().get();
		Weather value = response.readEntity(Weather.class);
		System.out.println(value);

	}

}
