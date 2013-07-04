<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/msglogin.js"></script>
</head>
<body>
	<form> 
		username：<input type="text" id="username"/><br/>
		password：<input type="password" id="pwd"/><br/>
		<input type="button" value="login" onclick="login();"/>
		<input type="reset" value="clear"/>
	</form>
</body>
</html>