/* 
 * @(#)MsgMgmtDaoImpl.java  2013-5-23
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
  V3.0     2013-5-23       	王栋                                          create
-----------------------------------------------------------------------------------
*/
package com.yuanwang.web.dao.mm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.yuanwang.web.dao.mm.MsgMgmtDao;
import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;



public class MsgMgmtDaoImpl implements MsgMgmtDao{

	private static final Log logger = LogFactory.getLog(MsgMgmtDaoImpl.class); 
	
	public User finduserByusername(String userName) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		User user=new User();
		try {
			em.getTransaction().begin();
			Query query=em.createQuery("select o from User o where o.username=?");
			query.setParameter(1, userName);
			user=(User) query.getSingleResult();
		} catch (Exception e) {
			logger.info("no user entity by username!");
		}finally{
			em.getTransaction().commit();
			factory.close();
		}
		return user;
	}

	public Session getsessionBySenderAndReceiver(final String sender, final String receiver) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		Session session=null;
		Query query=em.createQuery("select s from Session s where (s.receiverName=? and s.senderName=?) or (s.receiverName=? and s.senderName=? )");
		query.setParameter(1,sender );
		query.setParameter(2, receiver);
		query.setParameter(3, receiver);
		query.setParameter(4, sender);
		try {
			session=(Session) query.getSingleResult();
		} catch (Exception e) {
			logger.info("no session entity by sender and receiver!");
		}
		em.close();
		factory.close();
		return session;
	}


	public void createSession(final String sender, final String receiver,final String msg) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		Session session=new Session(UUID.randomUUID().toString(), sender, receiver, msg, new Date());
		session.addMessage(new Message(sender,new Date(),msg));
		em.persist(session);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	public void addMsgToSession(String sender, String receiver, String msg) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		final Session session=getsessionBySenderAndReceiver(sender, receiver);
		final Session rsession=em.getReference(Session.class, session.getSessionId());
		//更新会话
		rsession.setMsgInfo(msg);
		rsession.setReceiverName(receiver);
		rsession.setSenderName(sender);
		rsession.setLastupdateTime(new Date());
		rsession.addMessage(new Message(sender, new Date(), msg));
		em.getTransaction().commit();
		em.close();
		factory.close();		
	}

	@SuppressWarnings("unchecked")
	public void addSessionToUser(final String sender,final String receiver, final Session session) {
		EntityManagerFactory factory = Persistence
		.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery("select u from User u where u.username=? or u.username=?");
		query.setParameter(1, sender);
		query.setParameter(2, receiver);
		List<User> users=query.getResultList();
		for (User user : users) {
			user.addSession(em.find(Session.class, session.getSessionId()));
		}
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	public List<Session> getSessionsByuserId(final int userId,final int pageIndex,final int pageSize) {
		EntityManagerFactory factory = Persistence
		.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		final User user = em.find(User.class, userId);
		Set<Session> s_sessions = user.getSessions();
		List<Session> l_sessions=new ArrayList<Session>(s_sessions);
		
		final int totoalCounts=l_sessions.size();
		//0:第一页
		final int startIndex=pageIndex*pageSize;
		final int endIndex=startIndex+pageSize;
		
		if(endIndex>totoalCounts){
			l_sessions=l_sessions.subList(startIndex,totoalCounts);
		}else{
			l_sessions=l_sessions.subList(startIndex,endIndex);
		}
		em.getTransaction().commit();
		em.close();
		factory.close();
		return l_sessions;
	}

	public User finduserByuserid(final int userId) {
		EntityManagerFactory factory = Persistence
		.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		final User user = em.find(User.class, 1);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return user;
	}

	public Session getsessionBysessionid(String sessionId) {
		
		
		return null;
	}

	public List<Message> getMsgsBysessionId(final String sessionId,final int pageIndex,final int pageSize) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		Session session=null;
		List<Message> msgs=new ArrayList<Message>();
		try {
			em.getTransaction().begin();
			session=em.find(Session.class, sessionId);
			msgs=new ArrayList<Message>(session.getMsgs());
			final int totoalCounts=msgs.size();
			//0:第一页
			final int startIndex=pageIndex*pageSize;
			final int endIndex=startIndex+pageSize;
			
			if(endIndex>totoalCounts){
				msgs=msgs.subList(startIndex,totoalCounts);
			}else{
				msgs=msgs.subList(startIndex,endIndex);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.getTransaction().commit();
			em.close();
			factory.close();
		}
		
		
		return msgs;
	}

	public int getSessionTotalCounts(int userId) {
		EntityManagerFactory factory = Persistence
		.createEntityManagerFactory("MyJpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		final User user = em.find(User.class, userId);
		Set<Session> s_sessions = user.getSessions();
		List<Session> l_sessions=new ArrayList<Session>(s_sessions);
		em.getTransaction().commit();
		em.close();
		factory.close();
		return l_sessions.size();
	}

	public int getMsgsTotalCounts(final String sessionId) {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyJpa");
		EntityManager em=factory.createEntityManager();
		Session session=null;
		List<Message> msgs=new ArrayList<Message>();
		try {
			em.getTransaction().begin();
			session=em.find(Session.class, sessionId);
			msgs=new ArrayList<Message>(session.getMsgs());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.getTransaction().commit();
			em.close();
			factory.close();
		}
		return msgs.size();
	}
	
}
