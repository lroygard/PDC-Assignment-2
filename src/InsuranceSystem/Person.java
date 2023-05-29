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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public abstract int createId();
}
