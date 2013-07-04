/* 
 * @(#)MsgMgmtService.java  2013-5-22
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
  V3.0     2013-5-22       	王栋                                          create
-----------------------------------------------------------------------------------
*/
package com.yuanwang.web.service.mm;

import java.util.List;

import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;


public interface MsgMgmtService {
	
	/**
	 * 验证登陆是否成功
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean isExistUser(final String userName,final String password);
	/**
	 * 验证是否存在彼此会话
	 * @param sender
	 * @param receiver
	 * @return
	 */
	public boolean isExistSession(final String sender,final String receiver);
	
	/**
	 * 添加会话(包含验证过程是否存在会话，以及将会话跟用户绑定)
	 * @param sender
	 * @param receiver
	 * @param msg
	 */
	public void addSession(final String sender, final String receiver,final String msg);
		
	
	/**
	 * 通过用户id得到用户的会话
	 * @param userId
	 * @return
	 */
	public List<Session> getSessionsByuserId(final int userId,final int pageIndex,final int pageSize);
	
	/**
	 * 通过用户id或名来得到用户
	 * @param userName
	 * @param userId
	 * @return
	 */
	public User getUser(final String userName,final Integer userId);
	
	/**
	 * 通过会话id得到该会话的所有消息
	 * @param sessionId
	 * @return
	 */
	public List<Message> getMsgsBysessionId(final String sessionId,final int pageIndex,final int pageSize);
	
	
	/**
	 * 通过用户id得到用户的会话数
	 * @param userId
	 * @return
	 */
	public int getSessionTotalPage(final int userId,final int pageSize);
	
	/**
	 * 通过會話id得到消息的總條數
	 * @param userId
	 * @return
	 */
	public int getMsgsTotalPage(final String sessionId,final int pageSize);
}	
