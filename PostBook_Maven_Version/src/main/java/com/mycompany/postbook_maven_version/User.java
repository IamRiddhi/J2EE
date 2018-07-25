/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.postbook_maven_version;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Riddhi Dutta
 */
public class User
{
    private int UserID;
    private String Username,Password;     

    public void setUserID(int UserID)
    {
        this.UserID = UserID;
    }

    public void setUsername(String Username)
    {
        this.Username = Username;
    }

    public void setPassword(String Password)
    {
        this.Password = Password;
    }

    
    
    public User()
    {
    }
    public User(int UserID, String Username, String Password)
    {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
    }
    
    public static User getUser(int userId)
    {
            try
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
                String url = "jdbc:mysql://localhost/intern?user=root&password=riddhi";
                Connection con = DriverManager.getConnection(url);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from user_detail where userid="+userId);
                User u = new User();
                if(rs.next())
                {
                    u.setPassword(rs.getString("password"));
                    u.setUsername(rs.getString("username"));
                    u.setUserID(userId);
                    con.close();
                    return u;
                }
            } catch (SQLException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }
    public static User createUser(String username,String password)
    {
        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "jdbc:mysql://localhost/intern?user=root&password=riddhi";
            Connection con = DriverManager.getConnection(url);
            PreparedStatement pstmt = con.prepareStatement("Select * from user_detail where Username=? and password=?");
            pstmt.setString(1,username); pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                User user = new User(rs.getInt("UserID"),rs.getString("username"),rs.getString("password"));
                con.close();
                return user;
            }
            else
                return null;
        } catch (SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null; //for showoff
    }
   
    
    public static int update(User u)
    {
        try
        {
             try
            {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "jdbc:mysql://localhost/intern?user=root&password=riddhi";
            Connection con = DriverManager.getConnection(url);
            
            PreparedStatement pstmt = con.prepareStatement("insert into user_detail (username,password) values(?,?)",Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.getUsername()); 
            pstmt.setString(2, u.getPassword());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            
            int key = -1;
            if(rs.next())
                key = rs.getInt(1);
            con.close();
            return key;
            
        } catch (SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public int getUserID()
    {
        return UserID;
    }

    public String getPassword()
    {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

}
