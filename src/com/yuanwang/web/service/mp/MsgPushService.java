/* 
 * @(#)MshPushService.java  2013-5-15
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
package com.yuanwang.web.service.mp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

import com.yuanwang.web.service.mm.MsgMgmtService;
import com.yuanwang.web.service.mm.impl.MsgMgmtServiceImpl;

public class MsgPushService {

	private MsgMgmtService service=new MsgMgmtServiceImpl();
	public static Map<String, Object> sessionManager = new HashMap<String, Object>();

	/**
	 * 添加客户端session ps:firfox有错误提示..-已解决，将跳转放置dwr的回调函数中
	 * 1.登录成功后进行调用
	 * @param username
	 */
	public String addScriptSession(String username, HttpServletRequest req) {
		if(!"".equals(username)&&username!=null){
			sessionManager.put(username, WebContextFactory.get().getScriptSession());
		}
		return "ok";
	}

	public ScriptSession getScriptSession(String username) {
		return (ScriptSession) sessionManager.get(username);
	}

	/**
	 * 2.发送消息时进行调用
	 * @param senderName
	 * @param receiverName
	 * @param title
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String pushMsg(String senderName, String receiverName, String title,
			String content, HttpServletRequest req) {
		service.addSession(senderName, receiverName, title);
		ScriptSession receiver_session = this.getScriptSession(receiverName);
		Collection<ScriptSession> cols = new ArrayList<ScriptSession>();
		cols.add(receiver_session);
		Util util = new Util(cols);
		util.addFunctionCall("doReply", "ok");
		return "ok";
	}

	/**
	 * 
	 * 这里暂时没有用到：给所有客户端页面添加脚本
	 * @param function
	 * @param msg
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addFunctionCallOnAllScriptSession(final String function,
			final String msg, HttpServletRequest req) {
		ServletContext sc = req.getSession().getServletContext();
		ServerContext sctx = ServerContextFactory.get(sc);
		Collection<ScriptSession> sessions = sctx
				.getScriptSessionsByPage("/msgpush/jsp/msgPush.do");
		Util util = new Util(sessions);
		util.addFunctionCall(function, msg);
		return "ok";
	}

}
