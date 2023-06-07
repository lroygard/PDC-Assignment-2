package InsuranceSystem;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;

public class InsuranceSystem {
    
    ArrayList<Customer> customers;
    ArrayList<Staff> staff;
    ArrayList<Policy> policies;
    Staff currentStaff;
    Customer currentCustomer;
    
    
    public InsuranceSystem() {
        //Initialise database to initialise tables
        Database db = new Database();

        //Get Arraylists from database
        this.staff = Database.getStaffList();
        this.customers = Database.getCustomerList();
        this.policies = Database.getPolicyList();

        //Associate policies to customers
        associatePolicies();
    }

    private void associatePolicies() {
        for(Customer customer:this.customers) {
            for(Policy policy:this.policies) {
                if (customer.getId() == policy.getCustomerId()) {
                    customer.addPolicy(policy);
                }
            }
        }
    }
    
    public ArrayList<JLabel> createCustomer(String firstName, String lastName, int birthYear, String phoneNumber, String email) {
        ArrayList<JLabel> errors = new ArrayList<>();
        
        String newFirstName = Customer.checkName(firstName);
        String newLastName = Customer.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphons", new Font(null, Font.PLAIN, 14), Color.RED, 625, 200, 300, 25));
        }
        
        String newPhoneNumber = Customer.checkPhoneNumber(phoneNumber);
        if (newPhoneNumber == null) {
            errors.add(SystemPage.createLabel("! Phone number must be a valid New Zealand number", new Font(null, Font.PLAIN, 14), Color.RED, 625, 300, 300, 25));
        }
        
        String newEmail = Customer.checkEmail(email);
        if (newEmail == null) {
            errors.add(SystemPage.createLabel("! Email must contains '@'", new Font(null, Font.PLAIN, 14), Color.RED, 625, 350, 300, 25));
        }
        
        if (errors.isEmpty()) {
            int id = Database.getNextId("CUSTOMER");
            System.out.println(id);
            Customer newCustomer = new Customer(id, newFirstName, newLastName, birthYear, newPhoneNumber, newEmail);
            this.customers.add(newCustomer);
            Database.addCustomer(newCustomer);
            errors.add(SystemPage.createLabel("Customer successfully created", new Font(null, Font.PLAIN, 14), Color.BLUE, 500, 390, 300, 25)); 
            errors.add(SystemPage.createLabel(newCustomer.getFullName()+ "'s id:" + id, new Font(null, Font.PLAIN, 14), Color.BLUE, 500, 465, 300, 25)); 
        }
                
        return errors;
    }    

    public ArrayList<JLabel> createStaff(String firstName, String lastName, int birthYear, String extension, String password, boolean manager) {
        ArrayList<JLabel> errors = new ArrayList<>();
        
        String newFirstName = Staff.checkName(firstName);
        String newLastName = Staff.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphons", new Font(null, Font.PLAIN, 14), Color.RED, 625, 200, 300, 25));
        }
        
        int newExtension;
        try {
            newExtension= Staff.checkExtension(Integer.parseInt(extension));

        } catch(NumberFormatException e) {
            newExtension = -1;
        }
        if (newExtension == -1) {
            errors.add(SystemPage.createLabel("! Extension must be a 3 digit number", new Font(null, Font.PLAIN, 14), Color.RED, 625, 300, 300, 25));
        }

        String newPassword = Staff.checkPassword(password);
        if (newPassword == null) {
            errors.add(SystemPage.createLabel("! Password must contain: 8-16 characters,", new Font(null, Font.PLAIN, 14), Color.RED, 625, 330, 300, 25));
            errors.add(SystemPage.createLabel("! One lowercase and one uppercase letter,", new Font(null, Font.PLAIN, 14), Color.RED, 625, 350, 300, 25));
            errors.add(SystemPage.createLabel("! One number, and one special character", new Font(null, Font.PLAIN, 14), Color.RED, 625, 370, 300, 25));
            
        }
        
        if (errors.isEmpty()) {
            int id = Database.getNextId("STAFF");
            String email = id + "@blacktieinsurance.co.nz"; 
            Staff newStaff = new Staff(id, newFirstName, newLastName, birthYear, newExtension, email, newPassword, manager);
            this.staff.add(newStaff);
            Database.addStaff(newStaff);
            errors.add(SystemPage.createLabel("Staff member successfully created", new Font(null, Font.PLAIN, 14), Color.BLUE, 500, 440, 300, 25)); 
            errors.add(SystemPage.createLabel(newStaff.getFullName()+ "'s id:" + id, new Font(null, Font.PLAIN, 14), Color.BLUE, 500, 465, 300, 25)); 
        }
        
        return errors;
    }
    
    public static boolean checkCoverage(String coverage, double assetTotal) {
        if(assetTotal == 0) {
            return false;
        }
        
        double newCoverage;
        try {
            newCoverage = Policy.checkCoverage(Double.parseDouble(coverage), assetTotal);
            if (newCoverage >= 1000 && newCoverage <= assetTotal) {
                return true;
            }
        } catch(NumberFormatException e) {
            
        }
        return false;
    }
    
    public static ArrayList<JLabel> checkAutoAndLife(String coverage, double assetTotal) {
        ArrayList<JLabel> errors = new ArrayList<>();
        
        boolean checkCoverage = checkCoverage(coverage, assetTotal);
        if (!checkCoverage) {
            if (assetTotal < 1000) {
                errors.add(SystemPage.createLabel("! Must be a number bigger than 1000", Color.RED, 150, 625, 300, 25));
            }
            else {
                errors.add(SystemPage.createLabel("!", Color.RED, 520, 275, 300, 25));
            }
        }
        
        return errors;
    }
    
    public static ArrayList<JLabel> checkHome(String coverage, double assetTotal, String address, String squareMeters) {
        ArrayList<JLabel> errors = new ArrayList<>();
        
        boolean checkCoverage = checkCoverage(coverage, assetTotal);
        if (!checkCoverage) {
            if (assetTotal < 1000) {
                errors.add(SystemPage.createLabel("! Must be a number bigger than 1000", Color.RED, 150, 625, 300, 25));
            }
            else {
                errors.add(SystemPage.createLabel("!", Color.RED, 520, 275, 300, 25));
            }
        }
        
        String newAddress = HomePolicy.checkAddress(address);
        if (newAddress == null) {
            errors.add(SystemPage.createLabel("! Address must include number + street name & type", Color.RED, 150, 225, 300, 25));
        }
        
        int newSquareMeters = 0;
        try {
            newSquareMeters = Integer.valueOf(squareMeters);
        } catch (NumberFormatException e) {
            errors.add(SystemPage.createLabel("! Must be a number", Color.RED, 150, 375, 200, 25));
        }
        
        return errors;
    }
    
    public ArrayList<Customer> searchCustomerName(String name) {
        ArrayList<Customer> foundCustomer = new ArrayList<>();
        name = name.toLowerCase();
        for (Customer customer: customers) {
            String custName = customer.getFullName().toLowerCase();
            if (custName.contains(name)) {
                foundCustomer.add(customer);
            }
        }
        
        return foundCustomer;
    }
    
    public ArrayList<Customer> searchCustomerId(String id) {
        ArrayList<Customer> foundCustomer = new ArrayList<>();
        
        for (Customer customer: customers) {
            String custId = String.valueOf(customer.getId());
            if (custId.contains(id)) {
                foundCustomer.add(customer);
            }
        }
        
        return foundCustomer;
    }
    
    public ArrayList<Staff> searchStaffName(String name) {
        ArrayList<Staff> foundStaff = new ArrayList<>();
        name = name.toLowerCase();
        for (Staff staffMember: staff) {
            String staffName = staffMember.getFullName().toLowerCase();
            if (staffName.contains(name)) {
                foundStaff.add(staffMember);
            }
        }
        
        return foundStaff;
    }
    
    public ArrayList<Staff> searchStaffId(String id) {
        ArrayList<Staff> foundStaff = new ArrayList<>();
        
        for (Staff staffMember: staff) {
            String staffID = String.valueOf(staffMember.getId());
            if (staffID.contains(id)) {
                foundStaff.add(staffMember);
            }
        }
        
        return foundStaff;
    }
    
    public static boolean searchCheckId(String id) {
        try {
            int intId = Integer.parseInt(id);
            if (intId >= 100) {
                return true;
            }
        } catch (NumberFormatException e) {
        
        }
        
        return false;
    }
    
    
}
