/* 
 * @(#)User.java  2013-5-15
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
  V3.0     2013-5-15       	王栋                                          create
-----------------------------------------------------------------------------------
*/
package com.yuanwang.web.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class User {
	/**
	 * 用户唯一编号
	 */
	private Integer userId;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	
	private Set<Session> sessions=new HashSet<Session>();
	
	/**
	 * @param userId
	 * @param username
	 * @param password
	 * @param sessions
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User() {
		super();
	}
	/**
	 * @return the username
	 */
	@Column(nullable=false,length=10)
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userId
	 */
	@Id @GeneratedValue
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the sessions
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "User_Session", joinColumns = { @JoinColumn(name = "User_ID", referencedColumnName = "userId") }, inverseJoinColumns = { @JoinColumn(name = "Session_ID", referencedColumnName = "sessionId") })
	@OrderBy("lastupdateTime DESC")
	public Set<Session> getSessions() {
		return sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session){
		this.sessions.add(session);
	}

}
