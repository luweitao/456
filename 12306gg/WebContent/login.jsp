<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.util.TextUtils" %>
    <%
    String username="";
    String autoLogin="";
    Cookie[] cookies=request.getCookies();
    if(cookies!=null){
    	for(Cookie c:cookies){
    		if("username".equals(c.getName())){
    			username=c.getValue();
    		}else if("autoLogin".equals(c.getName())){
    			autoLogin=c.getValue();
    		}
    	}
    }
    String action=request.getParameter("action");
    if(!"logout".equals(action)){
    	if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(autoLogin)){
    		response.sendRedirect(request.getContextPath()+"/login?action=autologin");
    	}
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>12306购票系统</title>
<link href="css/css.css" rel="stylesheel" type="text/css">
	<script language="javascript" type="text/javascript">
		function UserRegistration(){
			window.location.href = "register.html";
		}
	</script>
	<script language="javascript" type="text/javascript">
			function reloadcode(){
				alert("wojdshfja");
				document.getElementById("codeImg").src="ValidateCodeServlet?"+new Date();
			}
	</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form action="login?action=login" id="f_check" method="post" >
			<table width="933px" height="412px" background="img/bg_img1.jpg" >
				<tr>
					<td width="550px" height="120px"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table >
							<tr>
								<td>登录名</td>
								<td><input type="text" name="username" value=""/></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
								<td><input type="password" name="password" id="password"/></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>验证码</td>
								<td><input type="text" name="code"/></td>
								<td><img id="codeImg"src="ValidateCodeServlet" alt="点击换一张" style="cursor:hand" onclick="javascript:reloadcode()"/></td>
								<td><img src="img/text_sx.gif" onclick="javascript:reloadcode()"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><input type="checkbox" checked="checked" name="autoLogin" value="1" style="margin:0 auto;"/>自动登录</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table>	
							<tr>
								<td>&nbsp;</td>
								<td>
									<input type="submit" value="登录" />
								</td>
								<td><input type="button" value="注册" onClick="UserRegistration()"/>
								</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
								<%
								String msg=(String)request.getAttribute("msg");
								if(TextUtils.isEmpty(msg)){
								%>
								<%=msg %>
								<%} %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
</body>
</html>