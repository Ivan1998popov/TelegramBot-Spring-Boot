package com.telegram.springboot;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void contextLoads() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
		em.persist(new Schedule());
		transactionManager.commit(status);
		System.out.println(em.createQuery("SELECT c FROM Schedule c").getResultList());
	}

}
