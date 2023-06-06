package InsuranceSystem;

import java.util.ArrayList;

public class Customer extends Person {
    
    private String phoneNumber;
    private String email;
    private ArrayList<Policy> policies;
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public ArrayList<Policy> getPolicies() {
        return this.policies;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
    
    public Customer() {};

    public Customer(int id, String firstName, String lastName, int birthYear, String phoneNumber, String email) {
        super(id, firstName, lastName, birthYear);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.policies = new ArrayList<>();
    }

    public static String checkPhoneNumber(String number) {
        number = number.replaceAll("[^0-9]", "");
        StringBuilder sb = new StringBuilder();

        if (number.length() == 0) {
            return null;
        }

        //ensures the starting 0 is in the phone number, which also normalises the length
        if (!(number.charAt(0) == '0')) {
            number = '0' + number;
        }

        if (number.length() < 7 || number.length() > 10) {
            return null;
        }
        
        //formatting phone number
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
}
