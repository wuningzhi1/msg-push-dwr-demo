/* 
 * @(#)Session.java  2013-5-17
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
package com.yuanwang.web.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Session {
	/**
	 * 会话唯一ID
	 */
	private String sessionId;
	/**
	 * 发送者名称
	 */
	private String senderName;
	/**
	 * 接受者名称
	 */
	private String receiverName;
	/**
	 * 消息内容
	 */
	private String msgInfo;
	/**
	 * 会话最新更新时间
	 */
	private Date lastupdateTime;

	/**
	 * 会话包含的消息项
	 */
	private Set<Message> msgs = new HashSet<Message>();

	/**
	 * 会话包含的用户项
	 */
	private Set<User> users = new HashSet<User>();

	private int msgNums;
	
	public Session() {
		super();
	}

	/**
	 * @param sessionId
	 * @param senderName
	 * @param receiverName
	 * @param msgInfo
	 * @param lastupdateTime
	 * @param msgs
	 */
	public Session(String sessionId, String senderName, String receiverName,
			String msgInfo, Date lastupdateTime) {
		super();
		this.sessionId = sessionId;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.msgInfo = msgInfo;
		this.lastupdateTime = lastupdateTime;
	}

	/**
	 * @return the senderName
	 */
	@Column(length = 10)
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the receiverName
	 */
	@Column(length = 10)
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the msgInfo
	 */
	@Lob
	public String getMsgInfo() {
		return msgInfo;
	}

	/**
	 * @param msgInfo
	 *            the msgInfo to set
	 */
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	/**
	 * @return the lastupdateTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdateTime() {
		return lastupdateTime;
	}

	/**
	 * @param lastupdateTime
	 *            the lastupdateTime to set
	 */
	public void setLastupdateTime(Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	/**
	 * @return the msgs
	 */
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "session")
	@OrderBy("sendTime DESC")
	public Set<Message> getMsgs() {
		return msgs;
	}

	/**
	 * @param msgs
	 *            the msgs to set
	 */
	public void setMsgs(Set<Message> msgs) {
		this.msgs = msgs;
		this.msgNums=msgs.size();
	}

	/**
	 * @return the sessionId
	 */
	@Id
	@Column(length = 36)
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 向已存在会话添加消息
	 * @param msg
	 */
	public void addMessage(Message msg) {
		this.msgs.add(msg);
		msg.setSession(this);
	}

	/**
	 * 向已存在会话删除消息(暂时未找到删除方法)
	 * @param msg
	 */
	public void removeMessage(Message msg) {
		this.msgs.remove(msg);
		msg.setSession(this);
	}

	/**
	 * @return the users
	 */
	@ManyToMany(mappedBy="sessions")
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	public void addUser(User user){
		this.users.add(user);
	}

	public int getMsgNums() {
		return msgNums;
	}

	public void setMsgNums(int msgNums) {
		this.msgNums = msgNums;
	}

	
}
