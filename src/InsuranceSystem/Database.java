package InsuranceSystem;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    
    private final DatabaseManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public Database() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
    }
    
    public void staffDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "Staff";

            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();

            if (!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "ID INT PRIMARY KEY," +
                        "FIRSTNAME VARCHAR(50)," +
                        "LASTNAME VARCHAR(50)," +
                        "BIRTHYEAR INT," +
                        "EXTENSION INT," +
                        "EMAIL VARCHAR(100)," +
                        "PASSWORD VARCHAR(100)," +
                        "MANAGER BOOLEAN" +
                        ")";

                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void custDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "Customer";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "ID INT PRIMARY KEY,"+
                        "FIRSTNAME VARCHAR(50)," +
                        "LASTNAME VARCHAR(50)," +
                        "PHONENUMBER VARCHAR(50)," +
                        "EMAIL VARCHAR(50)," +
                        //TODO: Policies
                        ")";
                
                statement.executeUpdate(createTableQuery);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void autoPolicyDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "AutoPolicy";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "policyId INT PRIMARY KEY," +
                        "customerId INT,"+
                        "assetTotal DOUBLE," +
                        "coverage DOUBLE,"+
                        "paymentFrequency VARCHAR(50),"+
                        "premium VARCHAR(50),"+
                        "make VARCHAR(50),"+
                        "model VARCHAR(50),"+
                        "year INT,"+
                        "currentLicense VARCHAR(20),"+
                        "accidentHistory BOOLEAN,"+
                        "commercialUse BOOLEAN,"+
                        ")";
                        
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void lifePolicyDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "LifePolicy";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "policyId INT PRIMARY KEY," +
                        "customerId INT,"+
                        "assetTotal DOUBLE," +
                        "coverage DOUBLE,"+
                        "paymentFrequency VARCHAR(50),"+
                        "premium VARCHAR(50)," +
                        //TODO: medical history
                        "occupationRisk VARCHAR(10),"+
                        "hobbyRisk VARCHAR(10)"+
                        "gym BOOLEAN," +
                        "smoker BOOLEAN," +
                        ")";
                
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void homePolicyDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "HomePolicy";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "policyId INT PRIMARY KEY," +
                        "customerId INT,"+
                        "assetTotal DOUBLE," +
                        "coverage DOUBLE,"+
                        "paymentFrequency VARCHAR(50),"+
                        "premium VARCHAR(50),"+
                        "address VARCHAR(50),"+
                        "yearBuilt INT,"+
                        "levels INT,"+
                        "squareMeters INT,"+
                        "noBuildings INT," +
                        "wallMaterial VARCHAR(50),"+
                        "roofMaterial VARCHAR(50),"+
                        "constructionQuality VARCHAR(10),"+
                        ")";
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Staff> getStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        
        try {
            statement = conn.createStatement();
            
            String getStaff = "SELECT * FROM STAFF";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getStaff);
            
            while(rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int birthYear = rs.getInt("BIRTHYEAR");
                int extension = rs.getInt("EXTENSION");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                boolean manager = rs.getBoolean("MANAGER");
                
                staffList.add(new Staff(id,firstName,lastName,birthYear,extension,email,password,manager));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return staffList;
    }
    
    /*public ArrayList<Customer> getCustList() {
        ArrayList<Staff> custList = new ArrayList<>();
        
        try {
            statement = conn.createStatement();
            
            String getStaff = "SELECT * FROM CUSTOMEr";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getStaff);
            
            while(rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int birthYear = rs.getInt("BIRTHYEAR");
                String phoneNumber = rs.getString("EXTENSION");
                String email = rs.getString("EMAIL");
                
                custList.add(new Customer(id,firstName,lastName,birthYear,phoneNumber,email));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return custList;
    }*/
}
