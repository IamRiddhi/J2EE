/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riddhi.j2ee.userlogin;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RIDDHI DUTTA
 */
public class Post
{
    private int postId,userId;
    private String postAbstract,postTitle;
    private Date postTime;
    private ArrayList<Comment> comments; 
    
    
    public static ArrayList<Post> getAllPosts()
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
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user_post");
            ArrayList<Post> posts = new ArrayList<Post>();
            while(rs.next())
            {
                /* getting comment array*/
                int id = rs.getInt("PostId");
                ArrayList<Comment> cmt = Comment.getAllComments(id);

                Post post = new Post(id,rs.getInt("userid"),rs.getString("postabstract"),rs.getString("posttitle"),rs.getDate("posttime"));
                post.setComments(cmt);
                
                posts.add(post);
            }
            con.close();
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; //show off
    }
    
    public static Post getPostById(int postid)
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
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from user_post where postid="+postid);
                if(rs.next())
                {
                    Post post = new Post(postid, rs.getInt("userid"), rs.getString("postabstract"), rs.getString("posttitle"), rs.getDate("posttime"));
                    post.setComments(Comment.getAllComments(postid));
                    return post;
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }
    
    public Post()
    {
        
    }
    
    public static boolean addPost(int userId,String postTitle,String postAbstract)
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
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO `user_post` (posttitle,postabstract,userid) VALUES(?,?,?);");
            pstmt.setString(1,postTitle);
            pstmt.setString(2,postAbstract);
            pstmt.setInt(3,userId);
            pstmt.executeUpdate();
            con.close();
            return true;
        } catch (SQLException ex)
            {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }
    public Post(int postId, int userId, String postAbstract, String postTitle, Date postTime) {
        this.postId = postId;
        this.userId = userId;
        this.postAbstract = postAbstract;
        this.postTitle = postTitle;
        this.postTime = postTime;
        this.comments = new ArrayList<Comment>();
    }
    
    public ArrayList<Comment> getComments()
    {
        return comments;
    }
    
    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPostAbstract() {
        return postAbstract;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPostAbstract(String postAbstract) {
        this.postAbstract = postAbstract;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    
    
}
