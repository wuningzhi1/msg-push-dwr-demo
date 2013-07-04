/* 
 * @(#)Test.java  2013-5-15
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
package com.yuanwang.web.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
	
	@org.junit.Test
	public void test1(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("1", "1");
		map.put("1", "1");
		System.out.println(map.size());
		
		Set<String> set=new HashSet<String>();
		set.add("1");
		set.add("1");
		System.out.println(set.size());
		
	}
}
