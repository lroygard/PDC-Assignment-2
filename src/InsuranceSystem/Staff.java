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
        return Database.getNextId("STAFF");
    }
    
    public int checkExtension(int extension) {
        if (extension > 99 && extension < 1000) {
            return extension;
        } else {
            return -1;
        }
    }
    
    protected String checkPassword(String password) {        
        String specialChars = "!@#$%^&*";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "123456789";

        if (password.length() > 8 && password.length() < 16) {
            if (stringContains(password, specialChars)) {
                if (stringContains(password, upperCase)) {
                    if (stringContains(password, lowerCase)) {
                        if (stringContains(password, number)) {
                            return password;
                        }
                    }
                }
            }
        }
        return null;

    }
    
    private boolean stringContains(String string, String test) {
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < test.length(); j++) {
                if (string.charAt(i) == test.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
