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

    private static InsuranceSystem instance;

    /**
     * Initializes the InsuranceSystem by initializing the database and
     * retrieving staff, customers, and policies. Associates policies to
     * customers.
     */
    private InsuranceSystem() {
        //Initialise database to initialise tables
        Database db = Database.getInstance();

        //Get Arraylists from database
        this.staff = Database.getStaffList();
        this.customers = Database.getCustomerList();
        this.policies = Database.getPolicyList();

        //Associate policies to customers
        associatePolicies();
    }

    public static InsuranceSystem getInstance() {
        if (instance == null) {
            instance = new InsuranceSystem();
        }
        return instance;
    }

    /**
     * Associates policies with customers
     */
    private void associatePolicies() {
        for (Customer customer : this.customers) {
            for (Policy policy : this.policies) {
                // Check if customer ID matches policy's customer ID
                if (customer.getId() == policy.getCustomerId()) {
                    // Add the policy to the customer's policy list
                    customer.addPolicy(policy);
                }
            }
        }
    }

    /**
     * Creates a new customer with the given details.
     *
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param birthYear The birth year of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param email The email address of the customer.
     * @return An ArrayList of JLabel objects representing any errors
     * encountered during customer creation.
     */
    public ArrayList<JLabel> createCustomer(String firstName, String lastName, int birthYear, String phoneNumber, String email) {
        ArrayList<JLabel> errors = new ArrayList<>();

        // Check first name and last name
        String newFirstName = Customer.checkName(firstName);
        String newLastName = Customer.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphens", new Font(null, Font.PLAIN, 14), Color.RED, 600, 225, 300, 25));
        }

        // Check phone number
        String newPhoneNumber = Customer.checkPhoneNumber(phoneNumber);
        if (newPhoneNumber == null) {
            errors.add(SystemPage.createLabel("! Phone number must be a valid NZ number", new Font(null, Font.PLAIN, 14), Color.RED, 600, 300, 300, 25));
        }

        // Check email
        String newEmail = Customer.checkEmail(email);
        if (newEmail == null) {
            errors.add(SystemPage.createLabel("! Email must contain '@'", new Font(null, Font.PLAIN, 14), Color.RED, 600, 350, 300, 25));
        }

        // Create customer if no errors
        if (errors.isEmpty()) {
            int id = Database.getNextId("CUSTOMER");
            Customer newCustomer = new Customer(id, newFirstName, newLastName, birthYear, newPhoneNumber, newEmail);
            this.customers.add(newCustomer);
            Database.addCustomer(newCustomer);
            errors.add(SystemPage.createLabel("Customer successfully created", new Font(null, Font.PLAIN, 14), Color.BLUE, 400, 430, 300, 25));
            errors.add(SystemPage.createLabel(newCustomer.getFullName() + "'s id:" + id, new Font(null, Font.PLAIN, 14), Color.BLUE, 400, 455, 300, 25));
        }

        return errors;
    }

    /**
     * Creates a new staff member with the given details.
     *
     * @param firstName The first name of the staff member.
     * @param lastName The last name of the staff member.
     * @param birthYear The birth year of the staff member.
     * @param extension The extension number of the staff member.
     * @param password The password of the staff member.
     * @param manager A boolean indicating if the staff member is a manager.
     * @return An ArrayList of JLabel objects representing any errors
     * encountered during staff creation.
     */
    public ArrayList<JLabel> createStaff(String firstName, String lastName, int birthYear, String extension, String password, boolean manager) {
        ArrayList<JLabel> errors = new ArrayList<>();

        // Check first name and last name
        String newFirstName = Staff.checkName(firstName);
        String newLastName = Staff.checkName(lastName);
        if (newFirstName == null || newLastName == null) {
            errors.add(SystemPage.createLabel("! Name must contain only letters and hyphens", new Font(null, Font.PLAIN, 14), Color.RED, 600, 225, 300, 25));
        }

        // Check extension
        int newExtension;
        try {
            newExtension = Staff.checkExtension(Integer.parseInt(extension));
        } catch (NumberFormatException e) {
            newExtension = -1;
        }
        if (newExtension == -1) {
            errors.add(SystemPage.createLabel("! Extension must be a 3-digit number", new Font(null, Font.PLAIN, 14), Color.RED, 600, 300, 300, 25));
        }

        // Check password
        String newPassword = Staff.checkPassword(password);
        if (newPassword == null) {
            errors.add(SystemPage.createLabel("! Password must meet the following requirements:", new Font(null, Font.PLAIN, 14), Color.RED, 600, 330, 300, 25));
            errors.add(SystemPage.createLabel("! - 8-16 characters", new Font(null, Font.PLAIN, 14), Color.RED, 600, 350, 300, 25));
            errors.add(SystemPage.createLabel("! - One lowercase and one uppercase letter", new Font(null, Font.PLAIN, 14), Color.RED, 600, 370, 300, 25));
            errors.add(SystemPage.createLabel("! - One number and one special character", new Font(null, Font.PLAIN, 14), Color.RED, 600, 390, 300, 25));
        }

        // Create staff member if no errors
        if (errors.isEmpty()) {
            int id = Database.getNextId("STAFF");
            String email = id + "@blacktieinsurance.co.nz";
            Staff newStaff = new Staff(id, newFirstName, newLastName, birthYear, newExtension, email, newPassword, manager);
            this.staff.add(newStaff);
            Database.addStaff(newStaff);
            errors.add(SystemPage.createLabel("Staff member successfully created", new Font(null, Font.PLAIN, 14), Color.BLUE, 400, 480, 300, 25));
            errors.add(SystemPage.createLabel(newStaff.getFullName() + "'s id:" + id, new Font(null, Font.PLAIN, 14), Color.BLUE, 400, 510, 300, 25));
        }

        return errors;
    }

    /**
     * Checks the coverage validity based on the given coverage and asset total.
     *
     * @param coverage The coverage amount as a String.
     * @param assetTotal The total asset value.
     * @return true if the coverage is valid, false otherwise.
     */
    public static boolean checkCoverage(String coverage, double assetTotal) {
        if (assetTotal == 0) {
            return false;
        }

        double newCoverage;
        try {
            newCoverage = Policy.checkCoverage(Double.parseDouble(coverage), assetTotal);

            // Check if the new coverage is within a valid range
            if (newCoverage >= 1000 && newCoverage <= assetTotal) {
                return true;
            }
        } catch (NumberFormatException e) {
            // The coverage is not a valid number
        }

        return false;
    }

    /**
     * Checks the auto and life insurance details for any errors.
     *
     * @param coverage The coverage type for auto and life insurance.
     * @param assetTotal The total asset value for auto and life insurance.
     * @return An ArrayList of JLabel objects representing any errors found.
     */
    public static ArrayList<JLabel> checkAutoAndLife(String coverage, double assetTotal) {
        ArrayList<JLabel> errors = new ArrayList<>();

        // Check coverage and asset total
        boolean checkCoverage = checkCoverage(coverage, assetTotal);
        if (!checkCoverage) {
            if (assetTotal < 1000) {
                // Add error label for asset total less than 1000
                errors.add(SystemPage.createLabel("! Must be a number bigger than 1000", Color.RED, 150, 625, 300, 25));
            } else {
                // Add generic error label
                errors.add(SystemPage.createLabel("!", Color.RED, 520, 275, 300, 25));
            }
        }

        return errors;
    }

    /**
     * Checks the home details for any errors.
     *
     * @param coverage The coverage type of the home.
     * @param assetTotal The total asset value of the home.
     * @param address The address of the home.
     * @param squareMeters The size of the home in square meters.
     * @return An ArrayList of JLabel objects representing any errors found.
     */
    public static ArrayList<JLabel> checkHome(String coverage, double assetTotal, String address, String squareMeters) {
        ArrayList<JLabel> errors = new ArrayList<>();

        // Check coverage and asset total
        boolean checkCoverage = checkCoverage(coverage, assetTotal);
        if (!checkCoverage) {
            if (assetTotal < 1000) {
                // Add error label for asset total less than 1000
                errors.add(SystemPage.createLabel("! Must be a number bigger than 1000", Color.RED, 150, 625, 300, 25));
            } else {
                // Add generic error label
                errors.add(SystemPage.createLabel("!", Color.RED, 520, 275, 300, 25));
            }
        }

        // Check address
        String newAddress = HomePolicy.checkAddress(address);
        if (newAddress == null) {
            // Add error label for invalid address format
            errors.add(SystemPage.createLabel("! Address must include number + street name & type", Color.RED, 150, 225, 300, 25));
        }

        // Check square meters
        int newSquareMeters = 0;
        try {
            newSquareMeters = Integer.valueOf(squareMeters);
        } catch (NumberFormatException e) {
            // Add error label for non-numeric square meters
            errors.add(SystemPage.createLabel("! Must be a number", Color.RED, 150, 375, 200, 25));
        }

        return errors;
    }

    /**
     * Searches for customers by their name.
     *
     * @param name The name to search for.
     * @return An ArrayList of Customer objects that match the given name.
     */
    public ArrayList<Customer> searchCustomerName(String name) {
        ArrayList<Customer> foundCustomer = new ArrayList<>();

        // Convert the name to lowercase for case-insensitive matching
        name = name.toLowerCase();

        // Iterate through each customer
        for (Customer customer : customers) {
            // Get the full name of the customer and convert it to lowercase
            String custName = customer.getFullName().toLowerCase();

            // Check if the customer name contains the given name
            if (custName.contains(name)) {
                // Add the customer to the list of found customers
                foundCustomer.add(customer);
            }
        }

        return foundCustomer;
    }

    /**
     * Searches for customers by their ID.
     *
     * @param id The ID to search for.
     * @return An ArrayList of Customer objects that match the given ID.
     */
    public ArrayList<Customer> searchCustomerId(String id) {
        ArrayList<Customer> foundCustomer = new ArrayList<>();

        // Iterate through each customer
        for (Customer customer : customers) {
            // Get the ID of the customer as a string
            String custId = String.valueOf(customer.getId());

            // Check if the customer ID contains the given ID
            if (custId.contains(id)) {
                // Add the customer to the list of found customers
                foundCustomer.add(customer);
            }
        }

        return foundCustomer;
    }

    /**
     * Searches for staff members by their name.
     *
     * @param name The name to search for.
     * @return An ArrayList of Staff objects that match the given name.
     */
    public ArrayList<Staff> searchStaffName(String name) {
        ArrayList<Staff> foundStaff = new ArrayList<>();

        // Convert the name to lowercase for case-insensitive matching
        name = name.toLowerCase();

        // Iterate through each staff member
        for (Staff staffMember : staff) {
            // Get the full name of the staff member and convert it to lowercase
            String staffName = staffMember.getFullName().toLowerCase();

            // Check if the staff name contains the given name
            if (staffName.contains(name)) {
                // Add the staff member to the list of found staff
                foundStaff.add(staffMember);
            }
        }

        return foundStaff;
    }

    /**
     * Searches for staff members by their ID.
     *
     * @param id The ID to search for.
     * @return An ArrayList of Staff objects that match the given ID.
     */
    public ArrayList<Staff> searchStaffId(String id) {
        ArrayList<Staff> foundStaff = new ArrayList<>();

        // Iterate through each staff member
        for (Staff staffMember : staff) {
            // Get the ID of the staff member as a string
            String staffID = String.valueOf(staffMember.getId());

            // Check if the staff ID contains the given ID
            if (staffID.contains(id)) {
                // Add the staff member to the list of found staff
                foundStaff.add(staffMember);
            }
        }

        return foundStaff;
    }

    /**
     * Checks if the given ID is valid for searching.
     *
     * @param id The ID to be checked.
     * @return true if the ID is valid for searching, false otherwise.
     */
    public static boolean searchCheckId(String id) {
        try {
            int intId = Integer.parseInt(id);

            // Check if the ID is greater than or equal to 100
            if (intId >= 100) {
                return true;
            }
        } catch (NumberFormatException e) {
            // The ID is not a valid integer
        }

        return false;
    }

}
