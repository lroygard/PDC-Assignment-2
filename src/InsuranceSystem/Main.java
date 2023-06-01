package InsuranceSystem;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Staff> test = new ArrayList<>();
        test.add(new Staff(1234, "test","test",2000,102,"test","test",false));
        LoginPage login = new LoginPage(test);
        login.setVisible(true);
    }
}
