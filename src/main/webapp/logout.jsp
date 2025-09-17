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
<form action="logout">
ENTER ATTENDENCE ID:<input type="text" name="attendenceId">
<input type="submit" value="LOGOUT">
</form>
</body>
</html>