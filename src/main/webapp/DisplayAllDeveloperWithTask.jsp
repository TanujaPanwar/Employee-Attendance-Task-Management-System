<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.eatm.entity.Employee" %>
<%@ page import="com.eatm.entity.Task" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Developers with Tasks</title>
</head>
<body>
<h1>All Developers with Tasks</h1>
<table border="1" cellpadding="4" cellspacing="0">
    <tr>
        <th>Developer ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Task Name</th>
        <th>Status</th>
    </tr>

<%
    List<Employee> developers = (List<Employee>) request.getAttribute("developers");

    if(developers != null){
        for(Employee dev : developers){
            List<Task> tasks = dev.getTasks();

            if(tasks != null && !tasks.isEmpty()){
                for(Task task : tasks){
%>
                    <tr>
                        <td><%= dev.getEmployeeId() %></td>
                        <td><%= dev.getName() %></td>
                        <td><%= dev.getEmailId() %></td>
                        <td><%= task.getTaskName() %></td>
                        <td><%= task.getStatus().name() %></td>
                        
                        
                    </tr>
<%
                }
            } else {
%>
                <tr>
                    <td><%= dev.getEmployeeId() %></td>
                    <td><%= dev.getName() %></td>
                    <td><%= dev.getEmailId() %></td>
                    <td colspan="2">No tasks assigned</td>
                </tr>
<%
            }
        }
    }
%>
</table>
</body>
</html>
