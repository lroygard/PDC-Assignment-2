package InsuranceSystem;

public class Staff extends Person{
    
    private static final String COMPANYPHONENUMBER = "0800 800 800";
    private int extension;
    private String email;
    private String password;
    private boolean manager;
    
    public Staff() {
        super();
    }

    public Staff(int id, String firstName, String lastName, int birthYear, int extension, String email, String password, boolean manager) {
        super(id, firstName, lastName, birthYear);
        this.extension = extension;
        this.email = email;
        this.password = password;
        this.manager = manager;
    }

    public int getExtension() {
        return extension;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isManager() {
        return manager;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
    
    @Override
    public int createId() {
        //TODO - Create ID Logic (Staff)
        return -1;
    }
}
