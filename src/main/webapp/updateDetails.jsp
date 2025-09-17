<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String errormessage=(String)request.getAttribute("errorMessage");
if(errormessage!=null){
%>
<h1><%=errormessage %></h1>
<%
}
%>
<body>
<h2>Enter Employee ID to Get Developer Details</h2>
<form action="getDeveloper" method="get">
    Employee ID: <input type="text" name="employeeId" required><br><br>
    <input type="submit" value="Find Developer">
</form>
</body>
</html>