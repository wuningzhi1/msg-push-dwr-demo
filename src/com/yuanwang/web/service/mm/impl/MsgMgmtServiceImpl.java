/* 
 * @(#)MsgMgmtServiceImpl.java  2013-5-22
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
package com.yuanwang.web.service.mm.impl;


import java.util.List;

import com.yuanwang.web.dao.mm.MsgMgmtDao;
import com.yuanwang.web.dao.mm.impl.MsgMgmtDaoImpl;
import com.yuanwang.web.po.Message;
import com.yuanwang.web.po.Session;
import com.yuanwang.web.po.User;
import com.yuanwang.web.service.mm.MsgMgmtService;

public class MsgMgmtServiceImpl implements MsgMgmtService {
	MsgMgmtDao dao=new MsgMgmtDaoImpl();

	/* (non-Javadoc)
	 * @see com.yuanwang.web.service.mm.MsgMgmtService#isExistUser()
	 */
	public boolean isExistUser(final String userName,final String password) {
		User user=dao.finduserByusername(userName);
		if(password.equals(user.getPassword())){
			return true;
		}
		return false;
	}

	public void addSession(String sender, String receiver, String msg) {
		if(!isExistSession(sender, receiver)){
			dao.createSession(sender, receiver, msg);
			dao.addSessionToUser(sender,receiver, dao.getsessionBySenderAndReceiver(sender, receiver));
		}else{
			dao.addMsgToSession(sender, receiver, msg);
		}
	}


	public List<Session> getSessionsByuserId(final int userId,final int pageIndex,final int pageSize) {
		//对会话进行排序，根据会话更新时间
		List<Session> lsessions=dao.getSessionsByuserId(userId,pageIndex,pageSize);
//		for(int i=0;i<lsessions.size();i++){
//			for(int j=i+1;j<lsessions.size();j++){
//				Session si=lsessions.get(i);
//				Session sj=lsessions.get(j);
//				if(si.getLastupdateTime().getTime()<sj.getLastupdateTime().getTime()){
//					lsessions.set(i, sj);
//					lsessions.set(j, si);
//				}
//			}
//		}
		return lsessions;
	}

	public boolean isExistSession(final String sender, final String receiver) {
		return dao.getsessionBySenderAndReceiver(sender, receiver)==null?false:true;
	}

	public User getUser(final String userName, final Integer userId) {
		User user=null;
		if(userName!=null){
			user=dao.finduserByusername(userName);
		}else if(userId!=null){
			user=dao.finduserByuserid(userId);
		}
		return user;
	}

	public List<Message> getMsgsBysessionId(final String sessionId,final int pageIndex,final int pageSize) {
		return dao.getMsgsBysessionId(sessionId,pageIndex,pageSize);
	}

	public int getSessionTotalPage(final int userId,final int pageSize) {
		final int totalCount=dao.getSessionTotalCounts(userId);
		int totalPage=0;
		if(totalCount%pageSize==0){
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		return totalPage;
		
	}

	public int getMsgsTotalPage(final String sessionId,final int pageSize) {
		final int totalCount=dao.getMsgsTotalCounts(sessionId);
		int totalPage=0;
		if(totalCount%pageSize==0){
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		return totalPage;
	}

	
	

}
