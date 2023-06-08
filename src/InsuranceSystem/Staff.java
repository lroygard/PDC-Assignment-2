package InsuranceSystem;

public class Staff extends Person {

    private static final String COMPANYPHONENUMBER = "0800 800 800";
    private int extension;
    private String email;
    private String password;
    private boolean manager;

    public Staff() {
        super();
    }

    /**
     * Main Staff constructor
     *
     * @param id the id of the staff
     * @param firstName the first name of the staff
     * @param lastName the last name of the staff
     * @param birthYear the birth year of the staff
     * @param extension the extension number of the staff
     * @param email the email address of the staff
     * @param password the password of the staff
     * @param manager the manager status of the staff
     */
    public Staff(int id, String firstName, String lastName, int birthYear, int extension, String email, String password, boolean manager) {
        super(id, firstName, lastName, birthYear);
        this.extension = extension;
        this.email = email;
        this.password = password;
        this.manager = manager;
    }

    /**
     * Returns the extension number of the staff.
     *
     * @return the extension number of the staff
     */
    public int getExtension() {
        return extension;
    }

    /**
     * Returns the email address of the staff.
     *
     * @return the email address of the staff
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password of the staff.
     *
     * @return the password of the staff
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the manager status of the staff.
     *
     * @return true if the staff is a manager, false otherwise
     */
    public boolean isManager() {
        return manager;
    }

    /**
     * Creates a new staff id
     *
     * @return
     */
    @Override
    public int createId() {
        return Database.getNextId("STAFF");
    }

    /**
     * Checks if the given extension number is valid.
     *
     * @param extension the extension number to be checked
     * @return the extension number if it is valid, -1 otherwise
     */
    public static int checkExtension(int extension) {
        if (extension > 99 && extension < 1000) {
            return extension;
        } else {
            return -1;
        }
    }

    /**
     * Checks if the given password meets requirements
     *
     * @param password the password to be checked
     * @return the password if it meets the requirements, -1 otherwise
     */
    protected static String checkPassword(String password) {
        boolean hasSpecialChar = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;

        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                hasUpperCase = true;
            }
            if (c >= 'a' && c <= 'z') {
                hasLowerCase = true;
            }
            if (c >= '0' && c <= '9') {
                hasNumber = true;
            }
            if ("!@#$%^&*".contains(Character.toString(c))) {
                hasSpecialChar = true;
            }
        }

        if (password.length() >= 8 && password.length() <= 16
                && hasSpecialChar && hasUpperCase && hasLowerCase && hasNumber) {
            return password;
        }

        return null;
    }

    /**
     * Array of staff details
     *
     * @return the array
     */
    @Override
    public String[] getStringArray() {
        String[] array = new String[4];

        array[0] = String.valueOf(this.extension);
        array[1] = this.email;
        array[2] = this.password;
        array[3] = String.valueOf(this.manager);

        return array;
    }
}
