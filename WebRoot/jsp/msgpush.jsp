<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎 ${sessionScope.user.username }</title>

<link rel="stylesheet" href="../js/poshytip-1.1/tip-darkgray/tip-darkgray.css" type="text/css" />
<script type='text/javascript' src='../dwr/engine.js'> </script>
<script type='text/javascript' src='../dwr/interface/MsgPushService.js'> </script>
<script type='text/javascript' src='../dwr/util.js'> </script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/msgpush.js"></script>
<script type="text/javascript" src="../js/poshytip-1.1/jquery.poshytip.js"></script>



</head>
<body>
	<a id="notice" title="">消息通知中心</a>
	 <a href="sessionMgmt.do?pageIndex=0" onclick="hide();" target="_blank">会话管理中心</a>
	<hr>
	在线用户列表：
	<s:iterator value="#application.OnlineUsers" id="user">
		<span id="<s:property value='#user' />">
			<input type="radio" name="rusers" value="<s:property value='#user' />"/>
			<s:property value='#user' />
		</span>
	</s:iterator>
	<s:if test="#application.OnlineUsers.size==1">
		暂没有可发送消息的对象
	</s:if>
	<hr>
	<form>
		<table>
			<tr>
				<td>消息内容:</td>
				<td>
					<input type="text" id="title"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>内容</td>
				<td>
					<textarea rows="3" cols="20" id="content" ></textarea>
				</td>
			</tr>
			 -->
			<tr>
				<td></td>
				<td>
					<input type="button" value="发送" onclick="javascript:pushMsg();"/>
					<input type="reset" value="清空"/>
				</td>
			</tr>
		</table>
	</form>
	<input type="hidden" value="${sessionScope.user.username }" id="username"/>
</body>
</html>