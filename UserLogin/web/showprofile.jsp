<%-- 
    Document   : showprofile.jsp
    Created on : Jul 18, 2018, 1:45:21 PM
    Author     : Riddhi Dutta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="riddhi.j2ee.userlogin.User,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>Your Profile</title>
	<style >
		table {
    border-collapse: collapse;
    width: 100%;
}
td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 10%;
    opacity: 0.9;
}

input[type="submit"]:hover {
    opacity:1;
}
	</style>
</head>
<body>
<% User u = (User)(session.getAttribute("user")); %>
<table>
	<tr> <td>Name</td> <td><%= u.getName() %></td></tr>
	<tr> <td>Address</td> <td><%= u.getAddress1() %></td></tr>
	<tr><td>Date Of Birth</td> <td> <%= u.getDOB() %></td></tr>
	<tr><td>Gender</td> <td><%= u.getGender() %></td></tr>
	<tr><td>Phone No</td> <td><%= u.getPhNo() %></td></tr>
	<tr><td>Email</td> <td><%= u.getEmail() %></td></tr>
	<tr><td>About Me</td> <td><%= u.getRemarks() %></td></tr>
</table>	
<br><br><center> <form action="editprofile.do"> <input type="submit" value="Edit"> </form></center>
<br><center> <form action="logout.do"> <input type="submit" value="Logout"> </form></center>
<form>
</body>
</html> 
