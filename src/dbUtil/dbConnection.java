package dbUtil;

import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN = "jdbc:mysql://localhost:3306/schoolSys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConnection(){

        try{
            return DriverManager.getConnection(CONN,USERNAME,PASSWORD);

        }catch (SQLException e ){
            e.printStackTrace();
        }

        return null;
    }
}
