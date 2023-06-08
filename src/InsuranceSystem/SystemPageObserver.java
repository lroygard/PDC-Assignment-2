package InsuranceSystem;

public interface SystemPageObserver {

    /**
     * Notifies the observer when the password is correct for a logged-in staff
     * member.
     *
     * @param loggedInStaff The staff member who has logged in successfully.
     */
    void notifyPasswordCorrect(Staff loggedInStaff);
}
