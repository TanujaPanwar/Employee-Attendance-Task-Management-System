<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String errormessage=(String)request.getAttribute("errorMessage");
if(errormessage!=null){
%>
<h1><%=errormessage %></h1>
<%
}
%>
<% 
String updationMessage=(String)request.getAttribute("updationMessage");
if(updationMessage!=null){
%>
<h1><%=updationMessage %></h1>
 <%
}
%>
<form action="login">
EmailId:<input type="text" name="emailId"><br><br>
Password:<input type="text" name="password"><br><br>
<input type="submit" value="LOGIN">
</form>
<h1><a href="checkEmail.jsp">FORGOT PASSWORD</a></h1>
<h1><a href="register.jsp">NEW USER ?</a></h1>
</body>
</html>