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
        <title>PostBook</title>
    <style>
              
         ul {
           list-style-type: georgian;
           width: 70%;
           heigth:auto;
         }

         h3 {
           font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
         }

         li p {
           font: 200 12px/1.5 Georgia, Times New Roman, serif;
         }

         li {
           overflow: auto;
         }

         li:hover {
           background: #eee;
           cursor: pointer;
         }
         /* width */
    ::-webkit-scrollbar {
        width: 20px;
    }

    /* Track */
    ::-webkit-scrollbar-track {
        box-shadow: inset 0 0 5px grey; 
        border-radius: 10px;
    }

    /* Handle */
    ::-webkit-scrollbar-thumb {
        background: grey; 
        border-radius: 10px;
    }

    /* Handle on hover */
    ::-webkit-scrollbar-thumb:hover {
        background: grey; 
    }
         .thumbnail{
             float:left:
                 background-color:yellow;
             width:30%;
             margin-left:2%;
             position: absolute;
            height: 70%;
            overflow-y: auto;
            border="1px solid black"
         }
         .content{
             width:60%;
             float:right;
             margin:5%;
             overflow: auto;
             overflow-style: scrollbar;
         }
         h1
         {
              margin:5%;
              margin-bottom: 1%;
              width:50%;
         }

        </style>
    </head>
    <body>
     <center style="font-family:Helvetica;font-size:40px;color:red"> <b> Welcome <%= ((User)(session.getAttribute("user"))).getUsername() %> </b></center>
        <h1>Select Post</h1>
        <hr>
        <% ArrayList<Post> posts = (ArrayList<Post>)(request.getAttribute("posts")); %>
      <div class="thumbnail" >
        <ul>
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
                xhttp.open("GET", "getpost.do?id="+id, true);
                xhttp.send();
            }
            </script>
            <script>
                window.onscroll = function() {myFunction()};

                var header = document.getElementById("myHeader");
                var sticky = header.offsetTop;

                function myFunction() {
                  if (window.pageYOffset > sticky) {
                    header.classList.add("sticky");
                  } else {
                    header.classList.remove("sticky");
                  }
                }
                </script>
    </body>
</html>
