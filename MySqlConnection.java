import java.sql.*;
import java.util.Scanner;


public class MySqlConnection {
    private String database = "jdbc:mysql://localhost:3306/CarRental";
    private String username = "root";
    private String password = "DBpassword95";

    public Connection getConnection() {
        return connection;
    }

    private Connection connection = null;

    public MySqlConnection() {
        createConnection();
    }
    private void createConnection() {
        if (connection != null)
            return; // If connection already created, just return that to ensure singleton
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        connection = null;
    }
}
