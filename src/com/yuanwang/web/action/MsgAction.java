/* 
 * @(#)MsgPushAction.java  2013-5-15
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
package com.yuanwang.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuanwang.web.listener.OnlineUserBindingListener;
import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;
import com.yuanwang.web.service.mm.MsgMgmtService;
import com.yuanwang.web.service.mm.impl.MsgMgmtServiceImpl;

@SuppressWarnings("serial")
public class MsgAction extends BaseAction {
	private User user = new User();
	private List<Session> sessions = new ArrayList<Session>();
	private List<Message> msgs=new ArrayList<Message>();
	private String pageIndex;
	private int totalPage;
	private String sessionId;
	private MsgMgmtService service = new MsgMgmtServiceImpl();
	
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MsgAction.class);

	public String loginMsg() throws IOException {
		if (service.isExistUser(user.getUsername(), user.getPassword())) {
			HttpSession session = getSession();
			session.setAttribute("online", new OnlineUserBindingListener(user
					.getUsername()));
			session.setAttribute("user", service.getUser(user.getUsername(),
					null));

			getWriter().write("yes");
		} else {
			getWriter().write("no");
		}
		getWriter().flush();
		getWriter().close();
		return NONE;
	}

	public String msgPush() {
		return SUCCESS;
	}

	public String sessionMgmt() {
		HttpSession session = getSession();
		final User user=(User) session.getAttribute("user");
		totalPage=service.getSessionTotalPage(user.getUserId(), 4);
		try {
			if(user!=null){
				sessions=service.getSessionsByuserId(user.getUserId(),Integer.parseInt(pageIndex),4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String msgMgmt(){
		//final String sessionId=(String) getRequest().getParameter("sessionId");
		totalPage=service.getMsgsTotalPage(sessionId, 4);
		msgs=service.getMsgsBysessionId(sessionId,Integer.parseInt(pageIndex),4);
		return SUCCESS;
	}
	

	public String test() {
		return SUCCESS;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
