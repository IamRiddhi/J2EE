/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RIDDHI DUTTA
 */
public class GetPostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(WebUtil.isLoggedIn(request, response, true))
        {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            
            PrintWriter pw = response.getWriter();
            int postid = Integer.parseInt(request.getParameter("id"));
            Post post = Post.getPostById(postid);
            
            int curUserId = ((User)(session.getAttribute("user"))).getUserID();
            if(post.getUserId()==curUserId)
            {
                pw.println("<form action=\"delete.do\" style=\"border:none;width:200%;\"><input type=\"text\" name=\"pc\" value=\"post\" style=\"display:none;\"></input>");
                pw.println(" <button name=\"delete\" value=\""+ postid +"\"class=\"cancelbtn\">Delete Post</button></form>");
            }
            
            pw.println("<h3>"+post.getPostTitle()+"</h3><br>"+"<hr>");
            pw.println("<p>"+post.getPostAbstract()+"</p>");
            pw.println("<hr><p>Posted on "+post.getPostTime()+"</p>");
            pw.println("<br>Posted by "+User.getUser(post.getUserId()).getUsername());

            ArrayList<Comment> comments = post.getComments();
            pw.println("<hr><h3>Comments(<span id=\"comment_size\">"+comments.size()+"</span>)</h3>");

            pw.println("<div id=\"comment_section\">");
            for(Comment c:comments)
            {
                pw.println("<div id = \""+c.getCommentId()+"\">");  
                pw.println("<p>"+ c.getCommentText()+"</p>");
                pw.println("<p>- "+ User.getUser(c.getUserId()).getUsername()+"</p>");
                pw.println("<p>: "+ c.getCommentTime()+"</p>");
                if(c.getUserId()==curUserId || post.getUserId()==curUserId)
                {
                    pw.println("<button type=\"button\" class=\"btn btn-danger\" style=\"background-color:#f44336;\" id=\""+c.getCommentId()+"\" onclick=\"delete_comment(this.id)\">");
                    pw.println("Delete</button>");
                }
                pw.println("<hr></div>");            
            }
            pw.println("</div>");
            pw.println("<h5> Comment within 255 characters</h5>");
            pw.println("<textarea id=\"my_comment\"> </textarea><br>");
            pw.println("<button class=\"signupbtn\" style=\"width:20%;\" id="+postid+" onclick=\"add_comment(this.id)\" >Add Comment</button>");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
