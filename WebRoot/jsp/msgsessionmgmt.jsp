<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
</head>
<body>

	<s:iterator value="sessions" id="session" > 
	<p>${session.senderName }：${session.msgInfo }</p>
	<p>时间：${session.lastupdateTime }  &nbsp; &nbsp; &nbsp; 回复|共<a href="msgMgmt.do?sessionId=${session.sessionId }&pageIndex=0" target="_blank"><s:property value="#session.msgs.size"/></a>条消息|删除</p>
	<hr>
	</s:iterator>
	
	<SPAN>
		<a href="javascript:void(0)" onclick="page(0);">首页</a>|
	</SPAN>
	<SPAN>
		<a href="javascript:void(0)" onclick="page(${pageIndex-1});">上一页</a>|
	</SPAN>
	<SPAN>
		<a href="javascript:void(0)" onclick="page(${pageIndex+1});">下一页</a>|
 	</SPAN>
 	<SPAN>
 		<a href="javascript:void(0)" onclick="page(${totalPage-1});">尾页</a>|
 	</SPAN>
 		&nbsp;&nbsp;当前${pageIndex+1 }页/共${totalPage }页
 	
 	<input type="hidden" value="${totalPage }"/>
 	
 	<script type="text/javascript">
 		function page(pageIndex){
 			if(pageIndex<$("input[type=hidden]").val()&&pageIndex>-1){
 				window.location.href="sessionMgmt.do?pageIndex="+pageIndex;
 			}
 		}
 	</script>
</body>
</html>