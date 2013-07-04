/* 
 * @(#)BaseProxy.java  2013-5-22
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
package com.yuanwang.web.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class BaseProxy implements InvocationHandler {

	private static final Log logger = LogFactory.getLog(BaseProxy.class);
	
	private Object obj;
	/**
	 * @param obj
	 */
	public BaseProxy(Object obj) {
		super();
		this.obj = obj;
	}

	/**
	 * 得到代理对象
	 * @param objByproxy  被代理对象
	 * @param proxyObj  代理对象
	 * @return
	 */
	public static Object getInstance(Object objByproxy,
			InvocationHandler proxyObj) {
		return Proxy.newProxyInstance(objByproxy.getClass().getClassLoader(),
				objByproxy.getClass().getInterfaces(), proxyObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=null;
		try {
			this.doBegin();
			result=method.invoke(obj, args);
		} catch (Exception e) {
			logger.info("do the proxy error...");
			e.printStackTrace();
		}finally{
			this.doEnd();
		}
		
		return result;
	}
	
	public abstract void doBegin();
	public abstract void doEnd();

}
