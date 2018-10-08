package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbUtil.dbConnection;

import javax.xml.transform.Result;


public class LoginModel {
    Connection connection= null;

    public LoginModel(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = (Connection) dbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.connection ==null){
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return  this.connection !=null;
    }
    public boolean isLogin(String user, String pass, String opt)
            throws SQLException
    {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";
        try
        {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);

            rs = pr.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (SQLException e)
        {
            return false;
        }
        finally
        {
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
