/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Riddhi Dutta
 */
public class SaveServlet extends HttpServlet
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
        String isnew = request.getParameter("save");
        if((isnew!=null && isnew.equals("new"))|| WebUtil.isLoggedIn(request, response,true))
        {
            HttpSession session = request.getSession();
            String username,password;
            boolean edit = false;
            int id = -1;
            if(session.getAttribute("user")!=null)
            {
                id = ((User)(session.getAttribute("user"))).getUserID();
                username = ((User)(session.getAttribute("user"))).getUsername();
                password = ((User)(session.getAttribute("user"))).getPassword();
                edit = true;
            }
            else
            {
                username = request.getParameter("username");
                password = request.getParameter("password");
            }

            User u = new User();
            u.setUserID(id);
            u.setUsername(username);
            u.setPassword(password);
            u.setAddress1(request.getParameter("address1"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                u.setDOB(new Date(sdf.parse(request.getParameter("date")).getTime()));
            } catch (ParseException ex)
            {
                Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            u.setName(request.getParameter("name"));
            u.setRemarks(request.getParameter("remarks"));
            u.setPhNo(request.getParameter("phno")); u.setEmail(request.getParameter("email")); u.setGender(request.getParameter("gender").charAt(0));

            // fou
            u.setCityID(1); u.setStateID(1); u.setHasCar(true); u.setImg(null); u.setHasHome(true);u.setMarital('M'); 

            if(User.update(u,edit))
            {
                session.setAttribute("user", u);
                response.sendRedirect("welcome.do");
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
