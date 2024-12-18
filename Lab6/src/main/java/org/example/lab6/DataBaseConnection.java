package org.example.lab6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instance = null;
    public static DataBaseConnection getInstance() throws SQLException{
        if(instance == null)
            instance = new DataBaseConnection();

        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SortingDB","root","");
        }
        catch(SQLException e){
            System.out.println(e.getErrorCode());
        }

        return connection;
    }
}
