/* 
 * @(#)ProxyImpl.java  2013-5-22
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
package com.yuanwang.web.service.proxy.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.yuanwang.web.service.proxy.BaseProxy;

public class ProxyImpl extends BaseProxy{

	public ProxyImpl(Object obj) {
		super(obj);
	}

	public static Object getInstance(Object objByproxy) {
		return getInstance(objByproxy, new ProxyImpl(objByproxy));
	}
	
	@Override
	public void doBegin() {
	}
	

	@Override
	public void doEnd() {
		
	}
	
	
	

}
