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
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter pw = response.getWriter();
        int postid = Integer.parseInt(request.getParameter("id"));
        Post post = Post.getPostById(postid);
        
        pw.println("<h2>"+post.getPostTitle()+"</h2><br>"+"<hr>");
        pw.println("<p>"+post.getPostAbstract()+"</p>");
        pw.println("<hr><p>Posted on "+post.getPostTime()+"</p>");
        pw.println("<br>Posted by"+User.getUser(post.getUserId()).getUsername());
        
        ArrayList<Comment> comments = post.getComments();
        pw.println("<hr><h3>Comments("+comments.size()+")</h3>");
        
        for(Comment c:comments)
        {
            pw.println("<p>"+ c.getCommentText()+"</p>");
            pw.println("<p>Posted By "+ User.getUser(c.getUserId()).getUsername()+"</p>");
            pw.println("<p>Posted On "+ c.getCommentTime()+"</p><hr>");
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
