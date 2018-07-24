<%-- 
    Document   : ShowPost
    Created on : 22 Jul, 2018, 8:51:45 PM
    Author     : RIDDHI DUTTA
--%>

<%@page import="riddhi.j2ee.userlogin.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="riddhi.j2ee.userlogin.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>PostBook</title>
    <style>
        ::-webkit-scrollbar {
            width: 12px;
            background-color: #F5F5F5;
           }

        /* Track */
        ::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
            background-color: #F5F5F5;
            border-radius: 10px; 
        }

        /* Handle */
        ::-webkit-scrollbar-thumb {
             border-radius: 10px;
            -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
            background-image: -webkit-gradient(linear, left top, right top, from(#ffecd2), to(#fcb69f));
            background-image: -webkit-linear-gradient(left, #ffecd2 0%, #fcb69f 100%);
            background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 100%);
        }

         
         ul {
           list-style-type: georgian;
           width: 100%;
           heigth:auto;
           background-color: whitesmoke;
         }

         h3 {
           font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
         }

         li p {
           font: 200 12px/1.5 Georgia, Times New Roman, serif;
         }

         li {
           overflow: auto;
           width:100%;
         }
          li:hover {
              background: #3366ff;
              color: white;
           width:100%; 
          cursor: pointer;
         }

         .thumbnail{
             float:left:
             background-color:yellow;
             width:30%;
             margin-left:2%;
             position: absolute;
            height: 70%;
            overflow-y: auto;
            border="2px solid black";
         }
         .content{
             width:60%;
             float:right;
             margin:5%;
             margin-top: 0%;
             overflow-x: auto;
             overflow-style: scrollbar;
         }
         h1
         {
              margin:5%;
              margin-bottom: 1%;
              width:50%;
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
            width: 10%;
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
         .blank{
             width:5%;
             margin:1%;
         }
        </style>
    </head>
    <body>
     <center style="font-family:Helvetica;font-size:40px;color:red"> <b> Welcome <%= ((User)(session.getAttribute("user"))).getUsername()%> </b></center>
 
       
        <!-- modal -->
        <div id="id01" class="modal">
	  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		<form class="modal-content" action="addpc.do" method="post">
				<div class="container">
					<h1>New Post</h1>
                     <input type="text" name="pc" value="post" style="display:none;">                   
		      <hr>
				<b>Title</b> <input type="text" name="posttitle">
                                <br><b>Description (255 characters max)</b> <textarea name="postabstract"> </textarea>
		      <div class="clearfix">
		        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
		        <button type="submit" class="signupbtn">Post</button>
		      </div>
			</div>
		</form>
	</div>
        
     <form action="logout.do" method="post"><button type="submit" class="cancelbtn" style="float:right;">Logout</button></form>
     
        <h1>Select Post</h1>
        <hr>
        <% ArrayList<Post> posts = (ArrayList<Post>)(request.getAttribute("posts")); %>
      <div class="thumbnail" >
        <ul class="list-group">
          <% for(Post p:posts)
            {
                User u = User.getUser(p.getUserId());
            %>
                <li id="<%=p.getPostId()%>" name="post" onclick="getPost(this.id)">
                  <h3> <%= p.getPostTitle()%> </h3>
                  <p> Posted by <%=u.getUsername() %>
                </li>
            <%}%>
        </ul>
        <button onclick="document.getElementById('id01').style.display='block'" style="width:100%;"><center>New Post</center></button>
      </div>
        <div class="content" id="content" >

        </div>
        <script>
            function getPost(id)
            {
                var xhttp;    
                if (id == "") {
                  document.getElementById("content").innerHTML = "";
                  return;
                }
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("content").innerHTML = this.responseText;
                  }
                };
                xhttp.open("POST", "getpost.do?id="+id, true);
                xhttp.send();
            }
            function add_comment(postid)
            {
                var xhttp;    
                if (postid == "") {
                  return;
                }
                var mycomment = document.getElementById("my_comment").value;
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                      document.getElementById("comment_size").innerHTML = parseInt(document.getElementById("comment_size").innerHTML) + 1 + "";
                    document.getElementById("comment_section").innerHTML += this.responseText;
                    document.getElementById("my_comment").value="";
                  }
                };
                xhttp.open("POST", "addpc.do?postid="+postid+"&mycomment="+mycomment+"&pc=comment", true);
                xhttp.send();
            }
            
            function delete_comment(commentid)
            {
                var xhttp;    
                if (commentid == "") {
                  return;
                }
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                      document.getElementById("comment_size").innerHTML = parseInt(document.getElementById("comment_size").innerHTML) -1 + "";
                    document.getElementById(commentid).innerHTML = "";
                  }
                };
                xhttp.open("POST", "delete.do?commentid="+commentid+"&pc=comment", true);
                xhttp.send();
            }
            
            
            </script>
            <script>
                // Get the modal
                var modal = document.getElementById('id01');

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function(event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
                
                </script>
    </body>
</html>
