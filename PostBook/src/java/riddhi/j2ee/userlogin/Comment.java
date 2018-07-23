/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RIDDHI DUTTA
 */

public class Comment 
{
    private int postId,userId,commentId;
    private String commentText;
    private Date commentTime;
    
    public static ArrayList<Comment> getAllComments(int id)
    {
            try {
                String driverClassName = "com.mysql.jdbc.Driver";
                try {
                    Class.forName(driverClassName);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ShowPostServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                String url = "jdbc:mysql://localhost/intern?user=root&password=riddhi";
                Connection con = DriverManager.getConnection(url);
                
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select * from post_comment where Postid="+id);
                ArrayList<Comment> cmt = new ArrayList<Comment>();
                while(rs2.next())
                {
                    /* getting comment*/
                    Comment c = new Comment();
                    c.setCommentId(rs2.getInt("commentId"));
                    c.setCommentText(rs2.getString("commenttext"));
                    c.setCommentTime(rs2.getDate("commenttime"));
                    c.setPostId(rs2.getInt("postid"));
                    c.setUserId(rs2.getInt("userid"));
                    /* comment got*/
                    
                    /* add comment to comments array*/
                    cmt.add(c);
                }
                /* comment array got*/
                return cmt;
                
            } catch (SQLException ex) {
                Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new ArrayList<Comment>();
    }
    
    public Comment()
    {
    }

    public Comment(int postId, int userId, int commentId, String commentText, Date commentTime) {
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
        this.commentText = commentText;
        this.commentTime = commentTime;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
    
}
