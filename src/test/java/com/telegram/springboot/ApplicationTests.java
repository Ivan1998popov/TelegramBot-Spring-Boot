package com.telegram.springboot;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;

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
		Assert.assertEquals(bot.onUpdate("/help"), "Привет, я могу тебе помочь: " +
				"\n узнать время (Время) " +
				"\n узнать расписание ЮФУ (19.03.18 КТбо3-2) " +
				"\n узнать местоположение корпуса ЮФУ(Где находится корпус А) " +
				"\n узнать местоположение общежития (Где находится общежитие №1)");
	}

}
