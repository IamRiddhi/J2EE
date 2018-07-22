/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

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
    private String Username,Password,Address1,Name,PhNo,Email,Remarks;     
    private Date DOB;      
    private int StateID,CityID;   
    char Gender,Marital;  
    boolean hasCar,hasHome;
    Image img;

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

    public void setAddress1(String Address1)
    {
        this.Address1 = Address1;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setPhNo(String PhNo)
    {
        this.PhNo = PhNo;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public void setRemarks(String Remarks)
    {
        this.Remarks = Remarks;
    }

    public void setDOB(Date DOB)
    {
        this.DOB = DOB;
    }

    public void setStateID(int StateID)
    {
        this.StateID = StateID;
    }

    public void setCityID(int CityID)
    {
        this.CityID = CityID;
    }

    public void setGender(char Gender)
    {
        this.Gender = Gender;
    }

    public void setMarital(char Marital)
    {
        this.Marital = Marital;
    }

    public void setHasCar(boolean hasCar)
    {
        this.hasCar = hasCar;
    }

    public void setHasHome(boolean hasHome)
    {
        this.hasHome = hasHome;
    }

    public void setImg(Image img)
    {
        this.img = img;
    }
    
    
    public User()
    {
    }
    public User(int UserID, String Username, String Password, String Address1, String Name, String PhNo, String Email, String Remarks, Date DOB, int StateID, int CityID, char Gender, char Marital, boolean hasCar, boolean hasHome, Image img)
    {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.Address1 = Address1;
        this.Name = Name;
        this.PhNo = PhNo;
        this.Email = Email;
        this.Remarks = Remarks;
        this.DOB = DOB;
        this.StateID = StateID;
        this.CityID = CityID;
        this.Gender = Gender;
        this.Marital = Marital;
        this.hasCar = hasCar;
        this.hasHome = hasHome;
        this.img = img;
    }
    
    
    public static User createUser(String username,String password)
    {
        try
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection con = DriverManager.getConnection(url,"Login","login");
            PreparedStatement pstmt = con.prepareStatement("Select * from MyUsers where Username=? and password=?");
            pstmt.setString(1,username); pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                boolean car = (rs.getString("hasCar"))=="Y";
                boolean home = (rs.getString("hasHome"))=="Y";
//                Blob b = rs.getBlob("Pic"); InputStream in = b.getBinaryStream(); Image i = null;
//                try {
//                    i = ImageIO.read(in);
//                } catch (IOException ex) {
//                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//                }
                User user = new User(rs.getInt("UserID"),rs.getString("username"),rs.getString("password"),rs.getString("Address1"),rs.getString("Name"),rs.getString("PhNo"),rs.getString("email"),
                rs.getString("remarks"),rs.getDate("DOB"),rs.getInt("stateid"),rs.getInt("cityid"),rs.getString("gender").charAt(0),rs.getString("Marital").charAt(0),car,home,null);
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
   
    
    public static boolean update(User u,boolean edit)
    {
        try
        {
            try
            {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection con = DriverManager.getConnection(url,"Login","login");
            con.setAutoCommit(false);
            if(edit) //if editing delete exisiting
            {
                PreparedStatement pstmt = con.prepareStatement("delete from MyUsers where UserID=?");
                pstmt.setInt(1,u.getUserID()); 
                pstmt.executeUpdate();
            }    
            
            // inserting
            if(u.getUserID()==-1) // new user
            {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select seqId.nextVal from dual");
                rs.next();
                int id = rs.getInt(1);
                u.setUserID(id);
            }
            PreparedStatement pstmt = con.prepareStatement("insert into MyUsers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, u.getUserID());
            pstmt.setString(2, u.getUsername()); 
            pstmt.setString(3, u.getPassword());
            pstmt.setString(4, u.getAddress1());
            pstmt.setString(5, u.getName());
            pstmt.setDate(6,u.getDOB());
            pstmt.setInt(7, u.getStateID());
            pstmt.setInt(8, u.getCityID());
            pstmt.setString(9, u.getGender()+"");
            pstmt.setString(10, u.getMarital()+"");
            pstmt.setString(11, u.getPhNo());
            pstmt.setString(12, u.getEmail());
            pstmt.setString(13, u.getRemarks());
            pstmt.setString(14, u.isHasCar()==true?"Y":"N");
            pstmt.setString(15, u.isHasHome()==true?"Y":"N");
//            Blob b = con.createBlob();
//            OutputStream out = b.setBinaryStream(0);
//            try
//            {
//                ImageIO.write((RenderedImage) u.getImg(),"JPG",out);
//            } catch (IOException ex)
//            {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
            Blob b  = null;
            pstmt.setBlob(16,b);

            pstmt.executeUpdate();
            con.commit(); con.setAutoCommit(true); con.close();
            
            
        } catch (SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public int getUserID()
    {
        return UserID;
    }

    public String getPassword()
    {
        return Password;
    }

    public String getAddress1()
    {
        return Address1;
    }

    public String getName()
    {
        return Name;
    }

    public String getPhNo()
    {
        return PhNo;
    }

    public String getEmail()
    {
        return Email;
    }

    public String getRemarks()
    {
        return Remarks;
    }

    public Date getDOB()
    {
        return DOB;
    }

    public int getStateID()
    {
        return StateID;
    }

    public String getUsername()
    {
        return Username;
    }

    public int getCityID()
    {
        return CityID;
    }

    public char getGender()
    {
        return Gender;
    }

    public char getMarital()
    {
        return Marital;
    }

    public boolean isHasCar()
    {
        return hasCar;
    }

    public boolean isHasHome()
    {
        return hasHome;
    }

    public Image getImg()
    {
        return img;
    }
    
    
    
    
}
