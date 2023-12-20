package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    public DBConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DBConnection getInstance() throws ClassNotFoundException {
        if (dbConnection==null){
            dbConnection=new DBConnection();
            return dbConnection;
        }else{
            return dbConnection;
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
