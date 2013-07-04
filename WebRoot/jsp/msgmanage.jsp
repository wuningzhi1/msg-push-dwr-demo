<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
</head>
<body>
	<s:iterator value="msgs" id="msg">
		<p>${msg.senderName } 说：</p>
		<p>${msg.msgInfo }</p>
		<p>${msg.sendTime }</p>
		<hr/>
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
 	<input type="hidden" value="${sessionId}" id="sessionId"/>
 	
 	<script type="text/javascript">
 		function page(pageIndex){
 			//alert(sessionId);
 			if(pageIndex<$("input[type=hidden]").val()&&pageIndex>-1){
 				window.location.href="msgMgmt.do?pageIndex="+pageIndex+"&sessionId="+$("#sessionId").val();
 			}
 		}
 	
 	</script>
</body>
</html>