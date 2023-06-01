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
        staffDatabase();
        custDatabase();
        autoPolicyDatabase();
        homePolicyDatabase();
        lifePolicyDatabase();
        idDatabase();
    }
    
    public void staffDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "STAFF";

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
                
                //Insert one person so that you aren't locked out of the program
                String insertQuery = "INSERT INTO TABLE STAFF VALUES (201111, 'Bob', 'Smith', 1989, '101111@blacktieinsurance.co.nz', BobSmith1!, true)";
                statement.executeUpdate(insertQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void custDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "CUSTOMER";
            
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
            String tableName = "AUTOPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT PRIMARY KEY," +
                        "CUSTOMERID INT,"+
                        "ASSETTOTAL DOUBLE," +
                        "COVERAGE DOUBLE,"+
                        "PAYMENTFREQUENCY VARCHAR(50),"+
                        "PREMIUM DOUBLE,"+
                        "MAKE VARCHAR(50),"+
                        "MODEL VARCHAR(50),"+
                        "YEAR INT,"+
                        "CURRENTLICENSE VARCHAR(20),"+
                        "ACCIDENTHISTORY BOOLEAN,"+
                        "COMMERCIALUSE BOOLEAN,"+
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
            String tableName = "LIFEPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT PRIMARY KEY," +
                        "CUSTOMERID INT,"+
                        "ASSETTOTAL DOUBLE," +
                        "COVERAGE DOUBLE,"+
                        "PAYMENTFREQUENCY VARCHAR(50),"+
                        "PREMIUM DOUBLE," +
                        "OCCUPATIONRISK VARCHAR(10),"+
                        "HOBBYRISK VARCHAR(10)"+
                        "GYM BOOLEAN," +
                        "SMOKER BOOLEAN," +
                        ")";
                
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void medicalHistoryDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "MEDICALHISTORY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT" +
                        "MEDICALCONDITION VARCHAR(50),"+
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
            String tableName = "HOMEPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT PRIMARY KEY," +
                        "CUSTOMERID INT,"+
                        "ASSETTOTAL DOUBLE," +
                        "COVERAGE DOUBLE,"+
                        "PAYMENTFREQUENCY VARCHAR(50),"+
                        "PREMIUM DOUBLE,"+
                        "ADDRESS VARCHAR(50),"+
                        "YEARBUILT INT,"+
                        "LEVELS INT,"+
                        "SQUAREMETERS INT,"+
                        "NOBUILDINGS INT," +
                        "WALLMATERIAL VARCHAR(50),"+
                        "ROOFMATERIALS VARCHAR(50),"+
                        "CONSTRUCTIONQUALITY VARCHAR(10),"+
                        ")";
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void idDatabase() {
        try {
            statement = conn.createStatement();
            String tableName = "ID";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            tables.close();
            
            if(!tableExists) {
                
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "CUSTOMER INT,"+
                        "STAFF INT," +
                        "AUTOPOLICY INT," +
                        "LIFEPOLICY INT," +
                        "HOMEPOLICY INT," +
                        ")";
                
                statement.executeUpdate(createTableQuery);
                
                String insertQuery = "INSERT INTO ID VALUES (101111,201111,301111,401111,501111)";
                statement.executeUpdate(insertQuery);
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
    
    public ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> custList = new ArrayList<>();
        
        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM CUSTOMER";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getCustomer);
            
            while(rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int birthYear = rs.getInt("BIRTHYEAR");
                String phoneNumber = rs.getString("EXTENSION");
                String email = rs.getString("EMAIL");
                
                custList.add(new Customer(id, firstName, lastName, birthYear, phoneNumber, email));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return custList;
    }
    
    public ArrayList<Policy> getPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        policyList.addAll(getAutoPolicyList());
        policyList.addAll(getHomePolicyList());
        policyList.addAll(getLifePolicyList());
        return policyList;
    }
    
    private ArrayList<Policy> getAutoPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM AUTOPOLICY";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getCustomer);
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                int customerId = rs.getInt("CUSTOMERID");
                double assetTotal = rs.getDouble("ASSETTOTAL");
                double coverage = rs.getDouble("COVERAGE");
                String frequency = rs.getString("PAYMENTFREQUENCY");
                double premium = rs.getDouble("PREMIUM");
                String make = rs.getString("MAKE");
                String model = rs.getString("MODEL");
                int year = rs.getInt("YEAR");
                String currentLicense = rs.getString("CURRENTLICENSE");
                boolean accidentHistory = rs.getBoolean("ACCIDENTHISTORY");
                boolean commercialUse = rs.getBoolean("COMMERICALUSE");
                
                policyList.add(new AutoPolicy(policyId,customerId,assetTotal,coverage,
                        premium,frequency,make,model,year,currentLicense,accidentHistory,
                        commercialUse));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }

    private ArrayList<Policy> getHomePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM HOMEPOLICY";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getCustomer);
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                int customerId = rs.getInt("CUSTOMERID");
                double assetTotal = rs.getDouble("ASSETTOTAL");
                double coverage = rs.getDouble("COVERAGE");
                double premium = rs.getDouble("PREMIUM");
                String frequency = rs.getString("PAYMENTFREQUENCY");
                String address = rs.getString("ADDRESS");
                int yearBuilt = rs.getInt("YEARBUILT");
                int levels = rs.getInt("LEVELS");
                int squareMeters = rs.getInt("SQUAREMETERS");
                int noBuildings = rs.getInt("NOBUILDINGS");
                String wallMaterial = rs.getString("WALLMATERIAL");
                String roofMaterial = rs.getString("ROOFMATERIAL");
                String quality = rs.getString("CONSTRUCTIONQUALITY");
                
                policyList.add(new HomePolicy(policyId, customerId, assetTotal, coverage,
                        premium, frequency, address, yearBuilt, levels, squareMeters, noBuildings, 
                        wallMaterial, roofMaterial, quality));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }

    private ArrayList<Policy> getLifePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        ArrayList<String> medicalConditions = getMedicalHistoryDatabase();

        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM LIFEPOLICY";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getCustomer);
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                int customerId = rs.getInt("CUSTOMERID");
                double assetTotal = rs.getDouble("ASSETTOTAL");
                double coverage = rs.getDouble("COVERAGE");
                double premium = rs.getDouble("PREMIUM");
                String frequency = rs.getString("PAYMENTFREQUENCY");
                String occupationRisk = rs.getString("OCCUPATIONRISK");
                String hobbyRisk = rs.getString("HOBBYRISK");
                boolean gym = rs.getBoolean("GYM");
                boolean smoker = rs.getBoolean("SMOKER");
                
                ArrayList<LifePolicy.MedicalCondition> customerConditions = new ArrayList<>();
                
                for (String condition : medicalConditions) {
                    String[] parts = condition.split(":");
                    int conditionPolicyId = Integer.parseInt(parts[0]);
                    if (conditionPolicyId == policyId) {
                        customerConditions.add(LifePolicy.MedicalCondition.valueOf(parts[1]));
                    }
                }
                
                policyList.add(new LifePolicy(policyId, customerId, assetTotal, 
                        coverage, premium, frequency, customerConditions, hobbyRisk, 
                        occupationRisk, gym, smoker));
                
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }
    
    private ArrayList<String> getMedicalHistoryDatabase() {
        ArrayList<String> medicalConditions = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM MEDICALHISTORY";
            ResultSet rs = null;
            
            rs = statement.executeQuery(getCustomer);
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                String medicalCondition = rs.getString("MEDICALCONDITION");
                String condition = policyId + ":" + medicalCondition;

                medicalConditions.add(condition);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return medicalConditions;
   }
   
    public void addStaff() {
        
    }
    
    public void addCustomer() {
        
    }
    
    public void addPolicy() {
        
    }
}
