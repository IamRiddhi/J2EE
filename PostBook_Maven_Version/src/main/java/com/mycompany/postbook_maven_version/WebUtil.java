/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.postbook_maven_version;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Riddhi Dutta
 */
public class WebUtil
{

    public static Cookie getCookieByName(HttpServletRequest req, String cookieName)
    {
        Cookie[] cookies = req.getCookies();
        if(cookies!=null)
        {
            for(Cookie c:cookies)
            {
                if(c.getName().equals(cookieName))
                    return c;
            }
        }    
        return null;
    }
    
    public static void createCookie(HttpServletResponse resp,String name,String value,int time)
    {
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(time*60);
        resp.addCookie(cookie);
    }
    
    public static void removeCookie(HttpServletRequest req, HttpServletResponse resp,String name)
    {
        Cookie[] cookies = req.getCookies();
        if(cookies!=null)
        {
            for(Cookie c:cookies)
            {
                if(c.getName().equals(name))
                {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }
    }
    
    public static boolean isLoggedIn(HttpServletRequest req, HttpServletResponse resp,boolean redirect) throws IOException
    {
        HttpSession session = req.getSession();
        
    // check whether session has user object or not. If found return true
        if(session.getAttribute("user")!=null)
            return true;
        
    // if not found in session, then get "Remember me" Cookie 
        Cookie rememberMe = getCookieByName(req,"remember");
        
        User u = null;
        if(rememberMe!=null && rememberMe.getValue().equals("true"))
        {
            Cookie uname = getCookieByName(req,"username"); 
            Cookie pwd = getCookieByName(req, "password");
            if(uname!=null && pwd!=null)
                u = User.createUser(uname.getValue(),pwd.getValue());
        }
        if(u==null)
        {
            if(redirect) resp.sendRedirect("loginpage.do");
            return false;
        }
        else
        {
             session.setAttribute("user", u);
             return true;
        }
    }
}
