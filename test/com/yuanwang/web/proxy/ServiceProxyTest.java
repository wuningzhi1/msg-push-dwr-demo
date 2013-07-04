/* 
 * @(#)ServiceProxyTest.java  2013-5-22
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
package com.yuanwang.web.proxy;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.yuanwang.web.service.mm.MsgMgmtService;
import com.yuanwang.web.service.mm.impl.MsgMgmtServiceImpl;
import com.yuanwang.web.service.proxy.impl.ProxyImpl;

public class ServiceProxyTest {
	@Test
	public void test1(){
		MsgMgmtServiceImpl mms=new MsgMgmtServiceImpl();
		MsgMgmtService pmms=(MsgMgmtService) ProxyImpl.getInstance(mms);
		//pmms.addUser();
		
	}
}
