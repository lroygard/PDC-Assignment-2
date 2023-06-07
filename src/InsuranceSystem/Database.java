package InsuranceSystem;


/**
 * The DataBase class manages the insurance system, including staff, customers, and policies.
 * It interacts with the database to retrieve and store data, and provides methods for adding and querying records.
 */

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
    
    /**
     * Drops all tables in the database
     */
    public static void dropAllTables() {
        try {
            statement = conn.createStatement();
            
            // Array of table names to be dropped
            String[] tables = new String[]{"STAFF","CUSTOMER","AUTOPOLICY","HOMEPOLICY","LIFEPOLICY","MEDICALHISTORY","IDTABLE"};
            
            // Loop through the table names
            for(int i = 0; i < 6; i++) {
                ResultSet table = conn.getMetaData().getTables(null, null, tables[i], null);
                boolean tableExists = table.next();
                
                // If the table exists, drop it
                if(tableExists) {
                    statement.executeUpdate("DROP TABLE " + tables[i]);
                }
                
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Creates the STAFF table in the database if it doesn't exist already.
     * Also inserts a default staff member to avoid being locked out of the program.
     */
    public static void createStaffTable() {
        try {
            statement = conn.createStatement();
            String tableName = "STAFF";

            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();

            if (!tableExists) {
                // Define the query to create the STAFF table
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

                // Execute the create table query
                statement.executeUpdate(createTableQuery);
                
                // Insert a default staff member to avoid being locked out of the program
                String insertQuery = "INSERT INTO STAFF VALUES (201111, 'Bob', 'Smith', 1989, 101, '201111@blacktieinsurance.co.nz', 'BobSmith1!', true)";
                statement.executeUpdate(insertQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Creates the CUSTOMER table in the database if it doesn't exist already.
     */
    public static void createCustomerTable() {
        try {
            statement = conn.createStatement();
            String tableName = "CUSTOMER";
            
            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                // Define the query to create the CUSTOMER table
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "ID INT PRIMARY KEY,"+
                        "FIRSTNAME VARCHAR(50)," +
                        "LASTNAME VARCHAR(50)," +
                        "BIRTHYEAR INT," +
                        "PHONENUMBER VARCHAR(50)," +
                        "EMAIL VARCHAR(50)" +
                        ")";
                
                // Execute the create table query
                statement.executeUpdate(createTableQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Creates the AUTOPOLICY table in the database if it doesn't exist already.
     */
    public static void createAutoPolicyTable() {
        try {
            statement = conn.createStatement();
            String tableName = "AUTOPOLICY";
            
            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                // Define the query to create the AUTOPOLICY table
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
                       
                // Execute the create table query
                statement.executeUpdate(createTableQuery);
                
                tables.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Creates the LIFEPOLICY table in the database if it doesn't exist already.
     */
   public static void createLifePolicyTable() {
       try {
           statement = conn.createStatement();
           String tableName = "LIFEPOLICY";

           // Check if the table exists in the database
           ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
           boolean tableExists = tables.next();

           if(!tableExists) {
               // Define the query to create the LIFEPOLICY table
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

               // Execute the create table query
               statement.executeUpdate(createTableQuery);
           }

           tables.close();
           statement.close();
       } catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
   }
    
   /**
    * Creates the MEDICALHISTORY table in the database if it doesn't exist already.
    */
    public static void createMedicalHistoryTable() {
        try {
            statement = conn.createStatement();
            String tableName = "MEDICALHISTORY";
            
            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();

            if(!tableExists) {
                // Define the query to create the MEDICALHISTORY table
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "POLICYID INT," +
                        "MEDICALCONDITION VARCHAR(50)"+
                        ")";
                
                // Execute the create table query
                statement.executeUpdate(createTableQuery);
            }
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
    /**
     * Creates the MEDICALHISTORY table in the database if it doesn't exist already.
     */
    public static void createHomePolicyTable() {
        try {
            statement = conn.createStatement();
            String tableName = "HOMEPOLICY";
            
            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                // Define the query to create the HOMEPOLICY table
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
                        "ROOFMATERIAL VARCHAR(50),"+
                        "CONSTRUCTIONQUALITY VARCHAR(10)"+
                        ")";
                
                // Execute the create table query
                statement.executeUpdate(createTableQuery);
                
                tables.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Creates the ID table in the database if it doesn't exist already.
     */
    public static void createIdTable() {
        try {
            statement = conn.createStatement();
            String tableName = "ID";
            
            // Check if the table exists in the database
            ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
            boolean tableExists = tables.next();
            
            if(!tableExists) {
                // Define the query to create the HOMEPOLICY table
                String createTableQuery = "CREATE TABLE " + tableName + " (" +
                        "CUSTOMER INT,"+
                        "STAFF INT," +
                        "AUTOPOLICY INT," +
                        "LIFEPOLICY INT," +
                        "HOMEPOLICY INT" +
                        ")";
                
                // Execute the create table query
                statement.executeUpdate(createTableQuery);
                
                // Insert initial values into the ID table
                String insertQuery = "INSERT INTO ID VALUES (101110,201111,301110,401110,501110)";
                statement.executeUpdate(insertQuery);
            }
            
            tables.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retrieves a list of staff members from the database.
     * @return An ArrayList containing Staff objects.
     */
    public static ArrayList<Staff> getStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();

        try {
            statement = conn.createStatement();

            // Retrieve staff data from the STAFF table
            String getStaff = "SELECT * FROM STAFF";
            ResultSet rs = statement.executeQuery(getStaff);

            while(rs.next()) {
                // Extract staff information from the result set
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int birthYear = rs.getInt("BIRTHYEAR");
                int extension = rs.getInt("EXTENSION");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                boolean manager = rs.getBoolean("MANAGER");

                // Create a new Staff object and add it to the list
                staffList.add(new Staff(id, firstName, lastName, birthYear, extension, email, password, manager));
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return staffList;
    }
    
    /**
    * Retrieves a list of customers from the database.
    * @return An ArrayList containing Customer objects.
    */
   public static ArrayList<Customer> getCustomerList() {
       ArrayList<Customer> custList = new ArrayList<>();

       try {
           statement = conn.createStatement();

           // Retrieve customer data from the CUSTOMER table
           String getCustomer = "SELECT * FROM CUSTOMER";
           ResultSet rs = statement.executeQuery(getCustomer);

           while(rs.next()) {
               // Extract customer information from the result set
               int id = rs.getInt("ID");
               String firstName = rs.getString("FIRSTNAME");
               String lastName = rs.getString("LASTNAME");
               int birthYear = rs.getInt("BIRTHYEAR");
               String phoneNumber = rs.getString("PHONENUMBER");
               String email = rs.getString("EMAIL");

               // Create a new Customer object and add it to the list
               custList.add(new Customer(id, firstName, lastName, birthYear, phoneNumber, email));
           }

           rs.close();
           statement.close();
       } catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }

       return custList;
   }
    
    
    /**
     * Retrieves a list of policies from the database, including auto, home, and life policies.
     * @return An ArrayList containing Policy objects.
     */
    public static ArrayList<Policy> getPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();

        // Retrieve auto policies and add them to the policyList
        policyList.addAll(getAutoPolicyList());

        // Retrieve home policies and add them to the policyList
        policyList.addAll(getHomePolicyList());

        // Retrieve life policies and add them to the policyList
        policyList.addAll(getLifePolicyList());

        return policyList;
    }

    
    /**
     * Retrieves a list of auto policies from the database.
     * @return An ArrayList containing AutoPolicy objects.
     */
    private static ArrayList<Policy> getAutoPolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();

            // Execute query to retrieve auto policies
            String getAutoPolicy = "SELECT * FROM AUTOPOLICY";
            ResultSet rs = statement.executeQuery(getAutoPolicy);

            while(rs.next()) {
                // Retrieve data from the result set
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
                boolean commercialUse = rs.getBoolean("COMMERCIALUSE");

                // Create an AutoPolicy object and add it to the policyList
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


    /**
     * Retrieves a list of home policies from the database.
     * @return An ArrayList containing HomePolicy objects.
     */
    private static ArrayList<Policy> getHomePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        try {
            statement = conn.createStatement();

            // Execute query to retrieve home policies
            String getHomePolicy = "SELECT * FROM HOMEPOLICY";
            ResultSet rs = statement.executeQuery(getHomePolicy);

            while(rs.next()) {
                // Retrieve data from the result set
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

                // Create a HomePolicy object and add it to the policyList
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


    /**
     * Retrieves a list of life policies from the database.
     * @return An ArrayList containing LifePolicy objects.
     */
    private static ArrayList<Policy> getLifePolicyList() {
        ArrayList<Policy> policyList = new ArrayList<>();
        ArrayList<String> medicalConditions = getMedicalHistoryDatabase();

        try {
            statement = conn.createStatement();

            // Execute query to retrieve life policies
            String getLifePolicyList = "SELECT * FROM LIFEPOLICY";
            ResultSet rs = statement.executeQuery(getLifePolicyList);

            while(rs.next()) {
                // Retrieve data from the result set
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

                // Filter medical conditions based on policy ID
                for (String condition : medicalConditions) {
                    String[] parts = condition.split(":");
                    int conditionPolicyId = Integer.parseInt(parts[0]);
                    if (conditionPolicyId == policyId) {
                        customerConditions.add(parts[1]);
                    }
                }

                // Create a LifePolicy object and add it to the policyList
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

    
    /**
     * Retrieves the medical conditions from the database.
     * @return An ArrayList containing the medical conditions.
     */
    private static ArrayList<String> getMedicalHistoryDatabase() {
        ArrayList<String> medicalConditions = new ArrayList<>();
        try {
            statement = conn.createStatement();

            // Execute query to retrieve medical conditions
            String getMedicalHistoryDatabase = "SELECT * FROM MEDICALHISTORY";
            ResultSet rs = statement.executeQuery(getMedicalHistoryDatabase);

            while(rs.next()) {
                // Retrieve data from the result set
                int policyId = rs.getInt("POLICYID");
                String medicalCondition = rs.getString("MEDICALCONDITION");
                String condition = policyId + ":" + medicalCondition;

                // Add the condition to the medicalConditions list
                medicalConditions.add(condition);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return medicalConditions;
    }

   
    /**
     * Adds a new staff member to the database.
     * @param staff The Staff object representing the staff member to be added.
     */
    public static void addStaff(Staff staff) {
        try {
            statement = conn.createStatement();

            // Construct the INSERT query with the staff member's information
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

            // Execute the INSERT query to add the staff member to the database
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Adds a new customer to the database.
     * @param customer The customer object to be added.
     */
    public static void addCustomer(Customer customer) {
        try {
            statement = conn.createStatement();

            // Construct the INSERT query with the customer's information
            String insertQuery = "INSERT INTO CUSTOMER VALUES (" +
                customer.getId() + ", " +
                "'" + customer.getFirstName() + "', " +
                "'" + customer.getLastName() + "', " +
                customer.getBirthYear() + ", " +
                "'" + customer.getPhoneNumber() + "', " +
                "'" + customer.getEmail() + "'" +
                ")";

            // Execute the INSERT query to add the customer to the database
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Adds a policy to the database.
     * @param policy The policy object to be added.
     */
    public static void addPolicy(Policy policy) {
        if (policy instanceof AutoPolicy) {
            addAutoPolicy((AutoPolicy) policy);
        } else if (policy instanceof HomePolicy) {
            addHomePolicy((HomePolicy) policy);
        } else {
            addLifePolicy((LifePolicy) policy);
        }
    }

    /**
     * Adds an auto policy to the database.
     * @param autoPolicy The auto policy object to be added.
     */
    private static void addAutoPolicy(AutoPolicy autoPolicy) {
        try {
            statement = conn.createStatement();

            // Construct the INSERT query with the auto policy's information
            String insertQuery = "INSERT INTO AUTOPOLICY VALUES (" +
                autoPolicy.getPolicyId() + ", " +
                autoPolicy.getCustomerId() + ", " +
                autoPolicy.getAssetTotal() + ", " +
                autoPolicy.getCoverage() + ", " +
                "'" + autoPolicy.getFrequency() + "', " +
                autoPolicy.getYearlyPremium() + ", " +
                "'" + autoPolicy.getMake() + "', " +
                "'" + autoPolicy.getModel() + "', " +
                autoPolicy.getYear() + ", " +
                "'" + autoPolicy.getCurrentLicense() + "', " +
                "'" + autoPolicy.hasAccidentHistory() + "', " +
                "'" + autoPolicy.isCommercialUse() + "'" +
                ")";

            // Execute the INSERT query to add the auto policy to the database
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Adds a home policy to the database.
     * @param homePolicy The home policy object to be added.
     */
    private static void addHomePolicy(HomePolicy homePolicy) {
        try {
            statement = conn.createStatement();

            // Construct the INSERT query with the home policy's information
            String insertQuery = "INSERT INTO HOMEPOLICY VALUES (" +
                homePolicy.getPolicyId() + ", " +
                homePolicy.getCustomerId() + ", " +
                homePolicy.getAssetTotal() + ", " +
                homePolicy.getCoverage() + ", " +
                "'" + homePolicy.getFrequency() + "', " +
                homePolicy.getYearlyPremium() + ", " +
                "'" + homePolicy.getAddress() + "', " +
                homePolicy.getYearBuilt() + ", " +
                homePolicy.getLevels() + ", " +
                homePolicy.getSquareMeters() + ", " +
                homePolicy.getNoBuildings() + ", " +
                "'" + homePolicy.getWallMaterial() + "', " +
                "'" + homePolicy.getRoofMaterial() + "', " +
                "'" + homePolicy.getConstructionQuality() + "'" +
                ")";

            // Execute the INSERT query to add the home policy to the database
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Adds a life policy to the database.
     * @param lifePolicy The life policy object to be added.
     */
    private static void addLifePolicy(LifePolicy lifePolicy) {
        try {
            statement = conn.createStatement();

            // Construct the INSERT query with the life policy's information
            String insertQuery = "INSERT INTO LIFEPOLICY VALUES (" +
                lifePolicy.getPolicyId() + ", " +
                lifePolicy.getCustomerId() + ", " +
                lifePolicy.getAssetTotal() + ", " +
                lifePolicy.getCoverage() + ", " +
                "'" + lifePolicy.getFrequency() + "', " +
                lifePolicy.getYearlyPremium() + ", " +
                "'" + lifePolicy.getOccupationRisk() + "', " +
                "'" + lifePolicy.getHobbyRisk() + "', " +
                "'" + lifePolicy.isGym() + "', " +
                "'" + lifePolicy.isSmoker() + "'" +
                ")";

            // Execute the INSERT query to add the life policy to the database
            statement.executeUpdate(insertQuery);

            // Insert the medical history conditions associated with the life policy
            String insertConditions = "INSERT INTO MEDICALHISTORY VALUES ";
            ArrayList<LifePolicy.MedicalCondition> medicalConditions = lifePolicy.getMedicalHistory();

            for (int i = 0; i < medicalConditions.size(); i++) {
                LifePolicy.MedicalCondition condition = medicalConditions.get(i);
                insertConditions += "(" + lifePolicy.getPolicyId() + ", '" + condition.toString() + "')";

                if (i < medicalConditions.size() - 1) {
                    insertConditions += ", ";
                }
            }

            statement.executeUpdate(insertConditions);
            statement.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retrieves the next ID value from the ID table in the database.
     * The ID value is incremented by 1 and updated in the ID table.
     * @param text The column name of the ID to retrieve.
     * @return The next ID value.
     */
    public static int getNextId(String text) {
        int id = -1;
        try {
            statement = conn.createStatement();
            
            String query = "SELECT * FROM ID";
            ResultSet rs = statement.executeQuery(query);

            // Retrieve the current ID value from the result set
            if (rs.next()) {
                id = rs.getInt(text);
            }

            // Increment the ID value and update it in the ID table
            String updateQuery = "UPDATE ID SET " + text + " = " + ++id;
            statement.executeUpdate(updateQuery);
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return id;
    }
}
