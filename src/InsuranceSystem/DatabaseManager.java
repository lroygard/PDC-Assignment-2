package InsuranceSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:Assignment2DB; create=true";
    
    Connection conn;
    
    public DatabaseManager() {
        establishConnection();
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public void establishConnection() {
        //Establish a connection to Database
        try{
            conn=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println(URL+" connected...");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /*public static void main(String[] args) {
        Database db = new Database();
        System.out.println(db.getConnection());
    }*/
}
