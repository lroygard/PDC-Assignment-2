package InsuranceSystem;

public class InsuranceSystem {
    
    public InsuranceSystem() {
        
    }
    
    public void start() {
        //login();
        showSystem();
    }
    
    public void login() {
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }
    
    public void showSystem() {
        SystemPage system = new SystemPage();
        system.setVisible(true);
    }
}
