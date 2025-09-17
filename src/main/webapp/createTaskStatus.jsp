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
<h2>Update Task Status</h2>
<form action="updateTask">
    TASK ID: <input type="text" name="taskId"><br><br>
    <input type="submit" value="Update Task Status">
</form>
</body>
</html>