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

    public Person(int id, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public abstract int createId();
}
