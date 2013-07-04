/* 
 * @(#)Message.java  2013-5-17
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Message {
	/**
	 * 唯一消息ID
	 */
	private Integer msgId=0;
	/**
	 * 发送者名称
	 */
	private String senderName;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 消息内容
	 */
	private String msgInfo;
	/**
	 * 所属会话
	 */
	private Session session;

	public Message() {
		super();
	}

	/**
	 * @param msgId
	 */
	public Message(Integer msgId) {
		super();
		this.msgId = msgId;
	}

	/**
	 * @param senderName
	 * @param sendTime
	 * @param msgInfo
	 * @param session
	 */
	public Message(String senderName, Date sendTime, String msgInfo) {
		super();
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.msgInfo = msgInfo;
	}

	
	
	/**
	 * @param msgId
	 * @param senderName
	 * @param sendTime
	 * @param msgInfo
	 * @param session
	 */
	public Message(Integer msgId, String senderName, Date sendTime,
			String msgInfo) {
		super();
		this.msgId = msgId;
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.msgInfo = msgInfo;
	}

	/**
	 * @return the senderName
	 */
	@Column(nullable = false, length = 10)
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
	 * @return the sendTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime
	 *            the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
	 * 只需要级联更新
	 * 
	 * @return the session
	 */
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "sessionId")
	public Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the msgId
	 */
	@Id
	@GeneratedValue
	public Integer getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId
	 *            the msgId to set
	 */
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean flag=false;
		if(obj instanceof Message){
			Message msg=((Message)obj);
			if(msg.msgId!=0){
				flag=((Message)obj).msgId==this.msgId;
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 移除自定义对象，必须重写hashCode方法
	 */
	@Override
	public int hashCode() {
		return this.msgId;
	}
	
}
