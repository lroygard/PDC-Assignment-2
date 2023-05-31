package InsuranceSystem;

import java.util.ArrayList;

public class Customer extends Person {
    
    private String phoneNumber;
    private String email;
    //private String ArrayList<Policy> policies;
    
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    /*public ArrayList<Policy> getPolicies() {
        return this.policies;
    }*/

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /*public void addPolicy(Policy policy) {
        policies.add(policy);
    }*/
    
    public Customer() {};

    public Customer(int id, String firstName, String lastName, int birthYear, String phoneNumber, String email) {
        super(id, firstName, lastName, birthYear);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public int createId() {
        // TODO: createID Customer
        return 0;
    }
}
