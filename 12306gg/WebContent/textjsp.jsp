<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!String getHello(String name){
	return "Hi,"+name+"!";
} %>
<%Date now=new Date(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP 页面元素</title>
</head>

<body>
<!-- 这是html注释，客户端可见 -->
<%--这也是注释，JSP注释，客户端不可见 --%>
<h1>JSP页面元素构成</h1>
<%=getHello("aweitao") %>,现在时间是，<%=now %>
<br>
<jsp:include flush="true" page="register.html">
<jsp:param value="参数" name="str"/>
</jsp:include>
</body>
</html>