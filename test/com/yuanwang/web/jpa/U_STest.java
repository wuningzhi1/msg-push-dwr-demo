/* 
 * @(#)S_UTest.java  2013-5-20
 *
 * YUANWANG HIGHLY CONFIDENTIAL INFORMATION: 
 * THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF YUANWANG 
 * INCORPORATED AND MAY BE PROTECTED BY ONE OR MORE PATENTS. USE, DISCLOSURE, OR 
 * REPRODUCTION OF ANY PORTION OF THIS SOFTWARE IS PROHIBITED WITHOUT THE PRIOR 
 * EXPRESS WRITTEN PERMISSION OF YUANWANG INCORPORATED. 
 * Copyright 2009 YUANWANG Incorporated. All rights reserved as an unpublished 
 * work. 
 * 
 */
/*
 Modification Log:
 -----------------------------------------------------------------------------------
 Version 	Date       		By           		Notes
 ----   ----------   ----------------   -------------------------------------------
 V3.0     2013-5-20       	王栋                                          create
 -----------------------------------------------------------------------------------
 */
package com.yuanwang.web.jpa;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.sql.Select;
import org.junit.Test;

import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;

public class U_STest {
	@Test
	public void test() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		factory.close();
	}

	/**
	 * 添加用户
	 */
	@Test
	public void addUser() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new User("admin", "123"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 向既有的用户添加既有的会话
	 */
	@Test
	public void addSessionToUser() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, 2);
		user.addSession(em.find(Session.class,
				"6ef83274-8984-4aef-8b70-ae18f2f1ceb8"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 向既有的用户添加新的会话
	 */
	@Test
	public void addNewSessionToUser() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, 2);
		user.addSession(new Session(UUID.randomUUID().toString(), "", "", "",
				new Date()));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 下面执行出错，猜测是维护关系造成？
	 */
	@Test
	public void addUserToSession() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Session session = em.find(Session.class,
				"a5914b79-b811-4175-8e67-778491cb2605");
		session.addUser(new User("F", "123"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 得到用户-会话-消息信息等全部内容
	 */
	@Test
	public void getUser() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, 1);
		Set<Session> sessions = user.getSessions();
		System.out.println(user.getSessions().size());
		for (Session session : sessions) {
			System.out.println(session.getSenderName() + ":"
					+ session.getMsgInfo());
		}
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 得到会话-用户-消息信息等全部内容
	 */
	@Test
	public void getSession() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Session session = em.find(Session.class,
				"6ef83274-8984-4aef-8b70-ae18f2f1ceb8");
		System.out.println(session.getUsers().size());

		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testJoin() {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m from Message m where m.sessionId=Session.sessionId and m.sessionId=?");
		query.setParameter(1, "0c36bdca-14fb-40d3-9185-782f3c9affec");
		List<Object> l = query.getResultList();
		System.out.println(l.size());
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
