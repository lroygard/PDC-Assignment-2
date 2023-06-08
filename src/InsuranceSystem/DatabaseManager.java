package InsuranceSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//Heavily based on database manager in the lab
public class DatabaseManager {

    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:Assignment2DB;create=true";

    private static DatabaseManager instance;

    Connection conn;

    private DatabaseManager() {
        initialise();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initialise() {
        establishConnection();
    }

    /**
     * Returns the connection.
     *
     * @return The connection
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Establishes a connection to the database if one doesn't already exist.
     * Displays a warning message if connection fails and terminates the
     * application.
     */
    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Please close the previous application before starting a new one", "Message", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }
    }

    /**
     * Closes the connection to the database if it is open.
     */
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
