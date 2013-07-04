/* 
 * @(#)Test.java  2013-5-17
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
  V3.0     2013-5-17       	王栋                                          create
-----------------------------------------------------------------------------------
*/
package com.yuanwang.web.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;



import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;


public class S_MTest {
	
	@org.junit.Test
	public void test(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		factory.close();
	}
	

	/**
	 * 创建新的会话
	 */
	@org.junit.Test
	public void createSession(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Session session=new Session(UUID.randomUUID().toString(), "admin", "common", "hello world!", new Date());
//		session.addMessage(new Message("A", new Date(), "hello B"));
//		session.addMessage(new Message("A", new Date(), "hello B"));
		em.persist(session);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	/**
	 * 得到会话全部信息(包含会话的所有消息记录)
	 */
	@org.junit.Test
	public void getSession(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Session session=em.find(Session.class, "18849c56-a294-44cf-bfb8-b83f35feb00c");
		System.out.println(session.getMsgs().size());
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	/**
	 * 添加消息到已存在会话
	 */
	@org.junit.Test
	public void addMsgToSession(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Session session=em.getReference(Session.class, "18849c56-a294-44cf-bfb8-b83f35feb00c");
		session.addMessage(new Message("A", new Date(), "hello B"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	/**
	 * 删除会话(相关会话所有信息)
	 */
	@org.junit.Test
	public void delSession(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Session session=em.getReference(Session.class, "89a38a0f-450e-4414-bdd7-e29270642f7b");
		em.remove(session);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	/**
	 * 删除信息
	 */
	@org.junit.Test
	public void delMsg(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Message msg=em.find(Message.class, 6);
		em.remove(msg);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	
	
	@org.junit.Test
	public void test1(){
		Set<Message> sets=new HashSet<Message>();
		sets.add(new Message(1,"A", new Date(), "hello B"));
		sets.add(new Message(2,"A", new Date(), "hello B"));
		sets.add(new Message(3,"A", new Date(), "hello B"));
		sets.add(new Message(3,"A", new Date(), "hello B"));
		System.out.println(sets.size());
		sets.remove(new Message(1,"A", new Date(), "hello B"));
		System.out.println(sets.size());
		
	}
	
	@Test
	public void finduserByname(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select o from User o where o.username=?");
		query.setParameter(1, "admin");
		User user=(User)query.getSingleResult();
		System.out.println(user.getUsername());
		
		em.close();
		factory.close();
	}
	
	@Test
	public void findsessionBySR(){
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		Query query=em.createQuery("select s from Session s where (s.receiverName=? and s.senderName=?) or (s.receiverName=? and s.senderName=? )");
		query.setParameter(1, "adm1in");
		query.setParameter(2, "common");
		query.setParameter(3, "com2mon");
		query.setParameter(4, "admin");
		try {
			Session session=(Session) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		factory.close();
		
	}
	
	@Test
	public void test3(){
		EntityManagerFactory factory = Persistence
		.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select u from User u where u.username=?");
		query.setParameter(1, "common");
		User user=(User) query.getSingleResult();
		user.addSession(em.find(Session.class, "9aad75ef-55e1-4b98-b807-2a97447bbc6d"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
