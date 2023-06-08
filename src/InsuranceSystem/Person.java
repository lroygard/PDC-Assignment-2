package InsuranceSystem;

public abstract class Person {

    private static final int CURRENTYEAR = 2023;
    private final int id;
    private String firstName;
    private String lastName;
    private int birthYear;

    public Person() {
        this.id = -1;
    }

    /**
     * Main Person constructor
     *
     * @param id the id of the person
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param birthYear the birth year of the person
     */
    public Person(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    /**
     * Returns the id of the person.
     *
     * @return the id of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the full name of the person.
     *
     * @return the full name of the person
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the first name of the person.
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the birth year of the person.
     *
     * @return the birth year of the person
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Checks if the given name contains only alphabets and hyphens, and formats
     * it properly.
     *
     * @param name the name to be checked and formatted
     * @return the properly formatted name, or null if the name contains invalid
     * characters
     */
    protected static String checkName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '-')) {
                return null;
            }
        }

        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Returns an array of strings containing the person's details
     *
     * @return an array of strings representing the person's information
     */
    public String[] getPersonStringArray() {
        String[] array = new String[3];

        array[0] = String.valueOf(this.id);
        array[1] = this.firstName + " " + this.lastName;
        array[2] = String.valueOf(SystemPage.CURRENTYEAR - this.birthYear);

        return array;
    }

    //Abstract methods
    public abstract int createId();

    public abstract String[] getStringArray();
}
