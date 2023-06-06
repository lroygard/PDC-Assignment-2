package InsuranceSystem;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    
    private static final DatabaseManager dbManager = new DatabaseManager();
    private static final Connection conn = dbManager.getConnection();
    private static Statement statement;
    
    public Database() {        
        //dropAllTables();
        
        createStaffTable();
        createCustomerTable();
        createAutoPolicyTable();
        createHomePolicyTable();
        createLifePolicyTable();
        createMedicalHistoryTable();
        createIdTable();
    }
    
    public static void dropAllTables() {
        try {
            statement = conn.createStatement();
            String[] tables = new String[]{"STAFF","CUSTOMER","AUTOPOLICY","HOMEPOLICY","LIFEPOLICY","MEDICALHISTORY","IDTABLE"};
            
            for(int i = 0; i < 6; i++) {
                ResultSet table = conn.getMetaData().getTables(null, null, tables[i], null);
                boolean tableExists = table.next();
                
                if(tableExists) {
                    statement.executeUpdate("DROP TABLE " + tables[i]);
                }
                
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createStaffTable() {
        try {
            statement = conn.createStatement();
            String tableName = "STAFF";

            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();

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
                String insertQuery = "INSERT INTO STAFF VALUES (201111, 'Bob', 'Smith', 1989, 101, '201111@blacktieinsurance.co.nz', 'BobSmith1!', true)";
                statement.executeUpdate(insertQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createCustomerTable() {
        try {
            statement = conn.createStatement();
            String tableName = "CUSTOMER";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "ID INT PRIMARY KEY,"+
                        "FIRSTNAME VARCHAR(50)," +
                        "LASTNAME VARCHAR(50)," +
                        "BIRTHYEAR INT," +
                        "PHONENUMBER VARCHAR(50)," +
                        "EMAIL VARCHAR(50)" +
                        ")";
                
                statement.executeUpdate(createTableQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createAutoPolicyTable() {
        try {
            statement = conn.createStatement();
            String tableName = "AUTOPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
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
                        "YEARMADE INT,"+
                        "CURRENTLICENSE VARCHAR(20),"+
                        "ACCIDENTHISTORY BOOLEAN,"+
                        "COMMERCIALUSE BOOLEAN"+
                        ")";
                        
                statement.executeUpdate(createTableQuery);
                
                tables.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createLifePolicyTable() {
        try {
            statement = conn.createStatement();
            String tableName = "LIFEPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT PRIMARY KEY," +
                        "CUSTOMERID INT,"+
                        "ASSETTOTAL DOUBLE," +
                        "COVERAGE DOUBLE,"+
                        "PAYMENTFREQUENCY VARCHAR(50),"+
                        "PREMIUM DOUBLE," +
                        "OCCUPATIONRISK VARCHAR(10),"+
                        "HOBBYRISK VARCHAR(10),"+
                        "HAS_GYM BOOLEAN," +
                        "SMOKER BOOLEAN" +
                        ")";
                
                statement.executeUpdate(createTableQuery);
                
                tables.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createMedicalHistoryTable() {
        try {
            statement = conn.createStatement();
            String tableName = "MEDICALHISTORY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT," +
                        "MEDICALCONDITION VARCHAR(50)"+
                        ")";
                
                statement.executeUpdate(createTableQuery);
            }
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createHomePolicyTable() {
        try {
            statement = conn.createStatement();
            String tableName = "HOMEPOLICY";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
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
                        "CONSTRUCTIONQUALITY VARCHAR(10)"+
                        ")";
                statement.executeUpdate(createTableQuery);
                tables.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void createIdTable() {
        try {
            statement = conn.createStatement();
            String tableName = "ID";
            
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "CUSTOMER INT,"+
                        "STAFF INT," +
                        "AUTOPOLICY INT," +
                        "LIFEPOLICY INT," +
                        "HOMEPOLICY INT" +
                        ")";
                
                statement.executeUpdate(createTableQuery);
                
                String insertQuery = "INSERT INTO ID VALUES (101111,201112,301111,401111,501111)";
                statement.executeUpdate(insertQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static ArrayList<Staff> getStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        
        try {
            statement = conn.createStatement();
            
            String getStaff = "SELECT * FROM STAFF";
            ResultSet rs = statement.executeQuery(getStaff);
                        
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
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return staffList;
    }
    
    public static ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> custList = new ArrayList<>();
        
        try {
            statement = conn.createStatement();
            
            String getCustomer = "SELECT * FROM CUSTOMER";
            ResultSet rs = statement.executeQuery(getCustomer);
                        
            while(rs.next()) {
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int birthYear = rs.getInt("BIRTHYEAR");
                String phoneNumber = rs.getString("PHONENUMBER");
                String email = rs.getString("EMAIL");
                
                custList.add(new Customer(id, firstName, lastName, birthYear, phoneNumber, email));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return custList;
    }
    
    public static ArrayList<Policy> getPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        policyList.addAll(getAutoPolicyList());
        policyList.addAll(getHomePolicyList());
        policyList.addAll(getLifePolicyList());
        return policyList;
    }
    
    private static ArrayList<Policy> getAutoPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getAutoPolicy = "SELECT * FROM AUTOPOLICY";
            ResultSet rs = statement.executeQuery(getAutoPolicy);
            
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                int customerId = rs.getInt("CUSTOMERID");
                double assetTotal = rs.getDouble("ASSETTOTAL");
                double coverage = rs.getDouble("COVERAGE");
                String frequency = rs.getString("PAYMENTFREQUENCY");
                double premium = rs.getDouble("PREMIUM");
                String make = rs.getString("MAKE");
                String model = rs.getString("MODEL");
                int year = rs.getInt("YEARMADE");
                String currentLicense = rs.getString("CURRENTLICENSE");
                boolean accidentHistory = rs.getBoolean("ACCIDENTHISTORY");
                boolean commercialUse = rs.getBoolean("COMMERICALUSE");
                
                policyList.add(new AutoPolicy(policyId,customerId,assetTotal,coverage,
                        premium,frequency,make,model,year,currentLicense,accidentHistory,
                        commercialUse));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }

    private static ArrayList<Policy> getHomePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getHomePolicy = "SELECT * FROM HOMEPOLICY";
            ResultSet rs = statement.executeQuery(getHomePolicy);
            
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
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }

    private static ArrayList<Policy> getLifePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        ArrayList<String> medicalConditions = getMedicalHistoryDatabase();

        try {
            statement = conn.createStatement();
            
            String getLifePolicyList = "SELECT * FROM LIFEPOLICY";
            ResultSet rs = statement.executeQuery(getLifePolicyList);
            
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                int customerId = rs.getInt("CUSTOMERID");
                double assetTotal = rs.getDouble("ASSETTOTAL");
                double coverage = rs.getDouble("COVERAGE");
                double premium = rs.getDouble("PREMIUM");
                String frequency = rs.getString("PAYMENTFREQUENCY");
                String occupationRisk = rs.getString("OCCUPATIONRISK");
                String hobbyRisk = rs.getString("HOBBYRISK");
                boolean gym = rs.getBoolean("HAS_GYM");
                boolean smoker = rs.getBoolean("SMOKER");
                
                ArrayList<String> customerConditions = new ArrayList<>();
                
                for (String condition : medicalConditions) {
                    String[] parts = condition.split(":");
                    int conditionPolicyId = Integer.parseInt(parts[0]);
                    if (conditionPolicyId == policyId) {
                        customerConditions.add(parts[1]);
                    }
                }
                
                policyList.add(new LifePolicy(policyId, customerId, assetTotal, 
                        coverage, premium, frequency, customerConditions, hobbyRisk, 
                        occupationRisk, gym, smoker));
                
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return policyList;
    }
    
    private static ArrayList<String> getMedicalHistoryDatabase() {
        ArrayList<String> medicalConditions = new ArrayList<>();
        try {
            statement = conn.createStatement();
            
            String getMedicalHistoryDatabase = "SELECT * FROM MEDICALHISTORY";
            ResultSet rs = statement.executeQuery(getMedicalHistoryDatabase);
            
            while(rs.next()) {
                int policyId = rs.getInt("POLICYID");
                String medicalCondition = rs.getString("MEDICALCONDITION");
                String condition = policyId + ":" + medicalCondition;

                medicalConditions.add(condition);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return medicalConditions;
   }
   
    public static void addStaff(Staff staff) {
        try {
            statement = conn.createStatement();
            //Insert one person so that you aren't locked out of the program
            String insertQuery = "INSERT INTO STAFF VALUES (" +
                    staff.getId() + ", '" +
                    staff.getFirstName() + "', '" +
                    staff.getLastName() + "', " +
                    staff.getBirthYear() + ", " +
                    staff.getExtension() + ", '" +
                    staff.getEmail() + "', '" +
                    staff.getPassword() + "', " +
                    staff.isManager() +
                    ")";
            
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void addCustomer(Customer customer) {
        try {
            statement = conn.createStatement();
            //Insert one person so that you aren't locked out of the program
            String insertQuery = "INSERT INTO CUSTOMER VALUES (" +
                customer.getId() + ", " +
                "'" + customer.getFirstName() + "', " +
                "'" + customer.getLastName() + "', " +
                      customer.getBirthYear() + ", " +
                "'" + customer.getPhoneNumber() + "', " +
                "'" + customer.getEmail() + "'" +
                ")";
            
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void addPolicy(Policy policy) {
        if (policy instanceof AutoPolicy) {
            addAutoPolicy((AutoPolicy) policy);
        } else if (policy instanceof HomePolicy) {
            addHomePolicy((HomePolicy) policy);
        } else {
            addLifePolicy((LifePolicy) policy);
        }
    }
    
    private static void addAutoPolicy(AutoPolicy autoPolicy) {
        try {
            statement = conn.createStatement();
            //Insert one person so that you aren't locked out of the program
            String insertQuery = "INSERT INTO AUTOPOLICY VALUES (" +
                "'" + autoPolicy.getPolicyId() + "', " +
                "'" + autoPolicy.getCustomerId() + "', " +
                "'" + autoPolicy.getAssetTotal() + "', " +
                "'" + autoPolicy.getCoverage() + "', " +
                "'" + autoPolicy.getFrequency() + "', " +
                "'" + autoPolicy.getYearlyPremium() + "', " +
                "'" + autoPolicy.getMake() + "', " +
                "'" + autoPolicy.getModel() + "', " +
                "'" + autoPolicy.getYear() + "', " +
                "'" + autoPolicy.getCurrentLicense() + "', " +
                "'" + autoPolicy.hasAccidentHistory() + "', " +
                "'" + autoPolicy.isCommercialUse() + "'" +
                ")";
            
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private static void addHomePolicy(HomePolicy homePolicy) {
        try {
            statement = conn.createStatement();
            //Insert one person so that you aren't locked out of the program
            String insertQuery = "INSERT INTO HOMEPOLICY VALUES (" +
                "'" + homePolicy.getPolicyId() + "', " +
                "'" + homePolicy.getCustomerId() + "', " +
                "'" + homePolicy.getAssetTotal() + "', " +
                "'" + homePolicy.getCoverage() + "', " +
                "'" + homePolicy.getFrequency() + "', " +
                "'" + homePolicy.getYearlyPremium() + "', " +
                "'" + homePolicy.getAddress() + "', " +
                "'" + homePolicy.getYearBuilt() + "', " +
                "'" + homePolicy.getLevels() + "', " +
                "'" + homePolicy.getSquareMeters() + "', " +
                "'" + homePolicy.getNoBuildings() + "', " +
                "'" + homePolicy.getWallMaterial() + "', " +
                "'" + homePolicy.getRoofMaterial() + "', " +
                "'" + homePolicy.getConstructionQuality() + "'" +
                ")";
            
            statement.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private static void addLifePolicy(LifePolicy lifePolicy) {
        try {
            statement = conn.createStatement();
            //Insert one person so that you aren't locked out of the program
            String insertQuery = "INSERT INTO LIFEPOLICY VALUES (" +
                "'" + lifePolicy.getPolicyId() + "', " +
                "'" + lifePolicy.getCustomerId() + "', " +
                "'" + lifePolicy.getAssetTotal() + "', " +
                "'" + lifePolicy.getCoverage() + "', " +
                "'" + lifePolicy.getFrequency() + "', " +
                "'" + lifePolicy.getYearlyPremium() + "', " +
                "'" + lifePolicy.getOccupationRisk() + "', " +
                "'" + lifePolicy.getHobbyRisk() + "', " +
                "'" + lifePolicy.isGym() + "', " +
                "'" + lifePolicy.isSmoker() + "'" +
                ")";
            
            String insertConditions = "INSERT INTO TABLE MEDICALHISTORY VALUES ";
            
            for(LifePolicy.MedicalCondition condition:lifePolicy.getMedicalHistory()) {
                if(lifePolicy.getMedicalHistory().indexOf(condition) != 0) {
                    insertConditions+=", ";
                }
                insertConditions+="("+lifePolicy.getPolicyId()+", '"+condition.toString()+"')";
            }
            
            statement.executeUpdate(insertQuery);
            statement.executeUpdate(insertConditions);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static int getNextId(String text) {
        int id = -1;
        try {
            statement = conn.createStatement();
            
            String query = "SELECT * FROM ID";
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                id = rs.getInt(1);
            }

            String updateQuery = "UPDATE ID SET " + text + " = " + ++id;
            statement.executeUpdate(updateQuery);
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return id;
    }
}
