/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Riddhi Dutta
 */
public class LoginExecuteServlet extends HttpServlet
{

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
            throws ServletException, IOException
    {
        
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            HttpSession session = request.getSession();


            User u = User.createUser(username, password);

            if(u==null)
            {
                session.setAttribute("invalid","TT");
                response.sendRedirect("loginpage.do");
            }
            else
            {
                session.setAttribute("invalid","FF");
                session.setAttribute("user",u);
                if(remember!=null && remember.equals("true"))
                {
                    WebUtil.createCookie(response,"username",username,24*60);
                    WebUtil.createCookie(response,"password",password,24*60);
                    WebUtil.createCookie(response,"remember","true",24*60);
                }
               else
                {
                   WebUtil.createCookie(response,"remember","false",24*60);
                   if(WebUtil.getCookieByName(request,"username")!=null)
                       WebUtil.removeCookie(request, response, username);
                    if(WebUtil.getCookieByName(request,"password")!=null)
                       WebUtil.removeCookie(request, response, password);
                }   
               response.sendRedirect("showpost.do");
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
