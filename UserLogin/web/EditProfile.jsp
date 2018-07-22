<%-- 
    Document   : EdiProfile.jsp
    Created on : Jul 18, 2018, 2:18:08 PM
    Author     : Riddhi Dutta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="riddhi.j2ee.userlogin.User,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>Edit Your Profile</title>
	<style >
		form {border: 3px solid #f1f1f1;}
	input {
	    width: 20%;
	    padding: 12px 20px;
	    margin: 8px 0;
	    display: inline-block;
	    border: 1px solid #ccc;
	    box-sizing: border-box;
	}

	button {
	    background-color: #4CAF50;
	    color: white;
	    padding: 14px 20px;
	    margin: 8px 0;
	    border: none;
	    cursor: pointer;
	    width: 10%;
	}
        .cancelbtn {
            background-color: #f44336;
         }
	button:hover {
	    opacity: 0.8;
	}

	</style>
</head>
<body>
	<% User u = (User)(session.getAttribute("user")); %>
	<form action="saveprofile.do" method="post">
		Name: <input type="text" name="name" value="<%=u.getName()%>">
		<br>Address: <input type="text" name="address1" size="40" value="<%= u.getAddress1()%>">
		<br>Date Of Birth: <input type="date" name="date" value="<%= u.getDOB() %>">
		<% char g = u.getGender(); %>
		<br>Gender:   <input type="radio" name="gender" value="M" <% if(g=='M') { %> checked <% } %>> Male <input type="radio" name="gender" value="F" <% if(g=='F') { %> checked <% } %>> Female
		<br>Phone No: <input type="text" name="phno" value="<%= u.getPhNo() %>">
		<br>Email: <input type="text" name="email" value="<%= u.getEmail() %>">
		<br>About Me: <textarea name="remarks"><%= u.getRemarks() %></textarea>
		<br><br><button type="submit">Save</button>
	</form>
                <a href="welcome.do"><br><button class="cancelbtn">Cancel</button></a>
</body>
</html>