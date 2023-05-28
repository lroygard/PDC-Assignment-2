package InsuranceSystem;

public class InsuranceSystem {
    
    public InsuranceSystem() {
        
    }
    
    public void start() {
        login();
    }
    
    public void login() {
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }
    
}
