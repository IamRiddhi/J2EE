/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RIDDHI DUTTA
 */
public class AddPostCommentServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            String pc = request.getParameter("pc");
            int userid = ((User)(session.getAttribute("user"))).getUserID();
            if(("post").equals(pc))
            {
                String posttitle = request.getParameter("posttitle");
                String postabstract = request.getParameter("postabstract");
                if(Post.addPost(userid, posttitle, postabstract))
                    response.sendRedirect("showpost.do");
            }  
            else if("comment".equals(pc))
            {
                int postid = Integer.parseInt((request.getParameter("postid")));
                String commentText = (String)(request.getParameter("mycomment"));
                int commentid = Comment.postComment(userid,postid,commentText);
                if(commentid!=-1)
                {
                    Date commenttime = Comment.getTime(commentid); 
                    PrintWriter pw = response.getWriter();
                   // get Latest comment
                   Comment c = new Comment(postid,userid,commentid,commentText,commenttime);
                    pw.println("<p>"+ c.getCommentText()+"</p>");
                    pw.println("<p>- "+ User.getUser(c.getUserId()).getUsername()+"</p>");
                    pw.println("<p>: "+ c.getCommentTime()+"</p><hr>");
                }
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
