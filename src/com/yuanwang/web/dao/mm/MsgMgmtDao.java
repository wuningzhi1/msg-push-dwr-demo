/* 
 * @(#)MsgMgmtDao.java  2013-5-23
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
package com.yuanwang.web.dao.mm;


import java.util.List;

import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;

public interface MsgMgmtDao {
	
	/**
	 * 通过用户名得到用户信息
	 * @param userName
	 * @return
	 */
	public User finduserByusername(final String userName);
	
	/**
	 * 通过用户id得到用户信息
	 * @param userName
	 * @return
	 */
	public User finduserByuserid(final int userId);
	
	
	
	/**
	 * 通过会话得到会话信息
	 * @param sessionId
	 * @return
	 */
	public Session getsessionBysessionid(final String sessionId);
	
	/**
	 * 通过发送者和接受者的名称来得到彼此的会话
	 * @param sender
	 * @param receiver
	 * @return
	 */
	public Session getsessionBySenderAndReceiver(final String sender,final String receiver);
	
	/**
	 * 通过发送者和接受者来创建新的会话，前提是确定没有彼此的会话
	 * @param sender
	 * @param receiver
	 */
	public void createSession(final String sender,final String receiver,final String msg);
	
	/**
	 * 更新已存在会话
	 * @param sender
	 * @param receiver
	 * @param msg
	 */
	public  void addMsgToSession(final String sender,final String receiver,final String msg);
	
	/**
	 * 添加会话到用户
	 * @param username
	 * @param session
	 */
	public void addSessionToUser(final String sender,final String receiver,final Session session);
	
	/**
	 * 通过用户id得到用户的会话
	 * @param userId
	 * @return
	 */
	public List<Session> getSessionsByuserId(final int userId,final int pageIndex,final int pageSize);
	
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
	public int getSessionTotalCounts(final int userId);
	
	/**
	 * 通过會話id得到消息的總條數
	 * @param userId
	 * @return
	 */
	public int getMsgsTotalCounts(final String sessionId);
}
