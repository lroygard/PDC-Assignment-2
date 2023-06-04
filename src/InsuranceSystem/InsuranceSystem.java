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
        this.staff = Database.getStaffList();
        this.customers = Database.getCustomerList();
        this.policies = Database.getPolicyList();

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
        Customer dummyCust = new Customer();
        ArrayList<JLabel> errors = new ArrayList<>();
        
        String newFirstName = dummyCust.checkName(firstName);
        String newLastName = dummyCust.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphons", new Font(null, Font.PLAIN, 14), Color.RED, 625, 200, 300, 25));
        }
        
        String newPhoneNumber = dummyCust.checkPhoneNumber(phoneNumber);
        if (newPhoneNumber == null) {
            errors.add(SystemPage.createLabel("! Phone number must be a valid New Zealand number", new Font(null, Font.PLAIN, 14), Color.RED, 625, 300, 300, 25));
        }
        
        String newEmail = dummyCust.checkEmail(email);
        if (newEmail == null) {
            errors.add(SystemPage.createLabel("! Email must contains '@'", new Font(null, Font.PLAIN, 14), Color.RED, 625, 350, 300, 25));
        }
        
        if (errors.isEmpty()) {
            Customer newCustomer = new Customer(dummyCust.createId(), newFirstName, newLastName, birthYear, newPhoneNumber, newEmail);
            this.customers.add(newCustomer);
            Database.addCustomer(newCustomer);
            errors.add(SystemPage.createLabel("Customer successfully created", new Font(null, Font.PLAIN, 14), Color.GREEN, 500, 390, 300, 25)); 
        }
                
        return errors;
    }    

    public ArrayList<JLabel> createStaff(String firstName, String lastName, int birthYear, String extension, String password, boolean manager) {
        Staff dummyStaff = new Staff();
        ArrayList<JLabel> errors = new ArrayList<>();
        
        String newFirstName = dummyStaff.checkName(firstName);
        String newLastName = dummyStaff.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphons", new Font(null, Font.PLAIN, 14), Color.RED, 625, 200, 300, 25));
        }
        
        int newExtension;
        try {
            newExtension= dummyStaff.checkExtension(Integer.parseInt(extension));

        } catch(NumberFormatException e) {
            newExtension = -1;
        }
        if (newExtension == -1) {
            errors.add(SystemPage.createLabel("! Extension must be a 3 digit number", new Font(null, Font.PLAIN, 14), Color.RED, 625, 300, 300, 25));
        }

        String newPassword = dummyStaff.checkPassword(password);
        if (newPassword == null) {
            errors.add(SystemPage.createLabel("! Password must contain: 8-16 characters,", new Font(null, Font.PLAIN, 14), Color.RED, 625, 330, 300, 25));
            errors.add(SystemPage.createLabel("! One lowercase and one uppercase letter,", new Font(null, Font.PLAIN, 14), Color.RED, 625, 350, 300, 25));
            errors.add(SystemPage.createLabel("! One number, and one special character", new Font(null, Font.PLAIN, 14), Color.RED, 625, 370, 300, 25));
            
        }
        
        if (errors.isEmpty()) {
            int id = dummyStaff.createId();
            String email = id + "@blacktieinsurance.co.nz"; 
            Staff newStaff = new Staff(id, newFirstName, newLastName, birthYear, newExtension, email, newPassword, manager);
            this.staff.add(newStaff);
            Database.addStaff(newStaff);
            errors.add(SystemPage.createLabel("Customer successfully created", new Font(null, Font.PLAIN, 14), Color.GREEN, 500, 390, 300, 25)); 
        }
       
        
        return errors;
    }
    
}
