package InsuranceSystem;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPage extends JFrame {
    private JPanel panel;
    
    private ArrayList<Staff> staff = Database.getStaffList();
    
    private SystemPageObserver observer;
    
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel wrongPassword;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage(SystemPageObserver observer) {
        this.observer = observer;

        panel = new JPanel(null);

        setTitle("Login Page");
        setSize(300, 175);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initialiseComponents();
        addToPanel();
        add(panel);

    }
    
    private void initialiseComponents() {
        initaliseLabels();
        intialiseUserAndPassFields();
        intialiseLogInButton();
 
    }
    
    private void initaliseLabels() {
        usernameLabel = SystemPage.createLabel("ID:", 20, 20, 80, 25);
        passwordLabel = SystemPage.createLabel("Password:", 20, 50, 80, 25);
        wrongPassword = SystemPage.createLabel("Incorrect ID or Password. Please Try Again.", Color.red, 20, 95, 400, 50);
    }
    
    private void intialiseUserAndPassFields() {
        usernameTextField = SystemPage.createTextField("201111", 100, 20, 160, 25);
        passwordField = new JPasswordField("BobSmith1!");
        passwordField.setBounds(100, 50, 160, 25);
    }
    
    private void intialiseLogInButton() {
        loginButton = SystemPage.createButton("Log In", 100, 80, 80, 25);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(usernameTextField.getText());
                    String password = new String(passwordField.getPassword());
                                 
                    Staff staff = checkDetails(id, password);
                    if (staff != null) {
                        observer.notifyPasswordCorrect(staff);
                        dispose();
                    } else {
                        panel.add(wrongPassword);
                        panel.repaint();
                    }
                } catch (NumberFormatException ex) {
                    panel.add(wrongPassword);
                    panel.repaint();
                }
            }
        });
    }
    
    private Staff checkDetails(int id, String password) {
        for (Staff thisStaff: staff) {
            if(id == thisStaff.getId()) {
                if (thisStaff.getPassword().equals(password)) {
                    return thisStaff;
                }
            } 
        }
        return null;
    }
    
    private void addToPanel() {
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(usernameTextField);
        panel.add(passwordField);
        panel.add(loginButton);
    }
}