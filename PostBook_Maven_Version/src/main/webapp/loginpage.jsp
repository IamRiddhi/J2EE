<%-- 
    Document   : loginpage.jsp
    Created on : Jul 18, 2018, 1:06:25 PM
    Author     : Riddhi Dutta
--%>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.container22 {
    display: block;
    position: relative;
    padding-left: 35px;
    margin-bottom: 12px;
    cursor: pointer;
    font-size: 22px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

/* Hide the browser's default radio button */
.container22 input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
}

/* Create a custom radio button */
.checkmark {
    position: absolute;
    top: 0;
    left: 0;
    height: 25px;
    width: 25px;
    background-color: #eee;
    border-radius: 50%;
}

/* On mouse-over, add a grey background color */
.container22:hover input ~ .checkmark {
    background-color: #ccc;
}

/* When the radio button is checked, add a blue background */
.container22 input:checked ~ .checkmark {
    background-color: #2196F3;
}

/* Create the indicator (the dot/circle - hidden when not checked) */
.checkmark:after {
    content: "";
    position: absolute;
    display: none;
}

/* Show the indicator (dot/circle) when checked */
.container22 input:checked ~ .checkmark:after {
    display: block;
}

/* Style the indicator (dot/circle) */
.container22 .checkmark:after {
 	top: 9px;
	left: 9px;
	width: 8px;
	height: 8px;
	border-radius: 50%;
	background: white;
}
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}
a:link, a:visited {
    background-color: #f44336;
    color: white;
    padding: 14px 25px;
    margin:10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}


a:hover, a:active {
    background-color: red;
}
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box}
/* Full-width input fields */
input[type=text], input[type=date],input[type=password],textarea {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus,textarea:focus,input[type=date]:focus {
    background-color: #ddd;
    outline: none;
}

/* Set a style for all buttons */
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

button:hover {
    opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
    padding: 14px 20px;
    background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: #474e5d;
    padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}
 
/* The Close Button (x) */
.close {
    position: absolute;
    right: 35px;
    top: 15px;
    font-size: 40px;
    font-weight: bold;
    color: #f1f1f1;
}

.close:hover,
.close:focus {
    color: #f44336;
    cursor: pointer;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 20%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
</style>
</head>
<body>
	<% String s = (String)(session.getAttribute("invalid"));
		if(s!=null && s.equals("TT")){ %>
		<script>alert("Username/Password is Incorrect");</script>
	<% session.removeAttribute("invalid");} %>	
	<div id="id01" class="modal">
	  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		<form class="modal-content"  name="signup" action="saveprofile.do" method="post" onSubmit="return validateForm(event);">
				<div class="container">
					<h1>Sign Up</h1>
                     <input type="text" name="save" value="new" style="display:none;">                   
		      <p>Please fill in this form to create an account.</p>
		      <hr>
				<b>Username</b> <input type="text" name="username" required>
				<br><b>Password</b> <input type="password" name="password" required onkeyup="check();">
                                <br><b>Retype Password</b> <input type="password" name="password_retype" required onkeyup="check();">
                                <br><span id='message'></span>
		      <div class="clearfix">
		        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
		        <button type="submit" class="signupbtn">Sign Up</button>
		      </div>
			</div>
		</form>
	</div>
	<form action = "loginExecute.do" method="post">
		  <div class="imgcontainer">
		    <img src="images/login.png" alt="Login" class="avatar">
		  </div>

		  <div class="container">
		    <label for="username"><b>Username</b></label>
		    <input type="text" placeholder="Enter Username" name="username" required>

		    <label for="password"><b>Password</b></label>
		    <input type="password" placeholder="Enter Password" name="password" required>
		       <label>
                           <input type="checkbox" name="remember" value="true" <%= (Boolean)(request.getAttribute("remember"))==Boolean.TRUE?"checked":"" %>  > Remember me
		    </label><br> 
		    <button type="submit">Login</button> 
		    
		  </div>
	</form>
	
	    <button onclick="document.getElementById('id01').style.display='block'" style="width:auto; background-color: red;width:100%;">Sign Up</button>
	     <script>
			// Get the modal
			var modal = document.getElementById('id01');

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			    if (event.target == modal) {
			        modal.style.display = "none";
			    }
			}
                        var check = function(){
                              document.getElementById('message').innerHTML="";
                        }
                        function validateForm(event)
                        {
                            event.preventDefault(); // this will prevent the submit event.
                            if(document.signup.password.value!=document.signup.password_retype.value)
                            {
                              document.getElementById('message').style.color = 'red';
                                document.getElementById('message').innerHTML = 'Password not matching';
                              document.signup.password_retype.focus();
                              return false;
                            }
                            else
                            {
                                document.signup.submit();// fire submit event
                            }
                        }
                        
                        
	</script>
</body>
</html>