/* 
 * @(#)MsgSessionListener.java  2013-5-15
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
package com.yuanwang.web.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuanwang.web.action.MsgAction;

public class OnlineUserBindingListener implements HttpSessionBindingListener {

	private static final Log logger = LogFactory.getLog(MsgAction.class);

	private String username;
	
	public OnlineUserBindingListener(String username){
		this.username=username;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		logger.info("将"+username+"进行绑定...");
		final HttpSession session=event.getSession();
		final ServletContext application=session.getServletContext();
		Set<String> onlineUsers=(Set<String>) application.getAttribute("OnlineUsers");
		if(onlineUsers==null){
			onlineUsers=new HashSet<String>();
			application.setAttribute("OnlineUsers", onlineUsers);
		}
		if(username!=null&&!"".equals(username)){
			onlineUsers.add(this.username);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingSetener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		logger.info("将"+username+"解除绑定...");
		final HttpSession session=event.getSession();
		final ServletContext application=session.getServletContext();
		Set<String> onlineUsers=(Set<String>) application.getAttribute("OnlineUsers");
		if(onlineUsers!=null){
			onlineUsers.remove(this.username);
		}
	}

}
