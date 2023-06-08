package InsuranceSystem;

import java.util.ArrayList;

public class Customer extends Person {

    private String phoneNumber;
    private String email;
    private ArrayList<Policy> policies;

    public Customer() {
    }

    /**
     * Main customer constructor
     *
     * @param id the id of the customer
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param birthYear the birth year of the customer
     * @param phoneNumber the phone number of the customer
     * @param email the email address of the customer
     */
    public Customer(int id, String firstName, String lastName, int birthYear, String phoneNumber, String email) {
        super(id, firstName, lastName, birthYear);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.policies = new ArrayList<>();
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the list of policies associated with the customer.
     *
     * @return the list of policies associated with the customer
     */
    public ArrayList<Policy> getPolicies() {
        return this.policies;
    }

    /**
     * Adds a policy to the list of policies associated with the customer.
     *
     * @param policy the policy to be added
     */
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    /**
     * Checks if the given phone number is valid and formats it properly.
     *
     * @param number the phone number to be checked and formatted
     * @return the properly formatted phone number, or null if the phone number
     * is invalid
     */
    public static String checkPhoneNumber(String number) {
        number = number.replaceAll("[^0-9]", "");
        StringBuilder sb = new StringBuilder();

        if (number.length() == 0) {
            return null;
        }

        // ensures the starting 0 is in the phone number, which also normalizes the length
        if (!(number.charAt(0) == '0')) {
            number = '0' + number;
        }

        if (number.length() < 7 || number.length() > 10) {
            return null;
        }

        // formatting phone number
        sb.append('(');
        for (int i = 0; i < number.length(); i++) {
            sb.append(number.charAt(i));
            if (i == number.length() - 8) {
                sb.append(") ");
            }
            if (i == number.length() - 5) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    /**
     * Checks if the given email address is valid.
     *
     * @param email the email address to be checked
     * @return the lowercase email address if it is valid, null otherwise
     */
    public static String checkEmail(String email) {
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                return email.toLowerCase();
            }
        }

        return null;
    }

    @Override
    public int createId() {
        return Database.getNextId("CUSTOMER");
    }

    @Override
    public String[] getStringArray() {
        String[] array = new String[3];

        array[0] = this.phoneNumber;
        array[1] = this.email;
        array[2] = String.valueOf(this.policies.size());

        return array;
    }
}
