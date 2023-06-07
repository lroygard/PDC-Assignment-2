package InsuranceSystem;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPage extends JFrame {
    private final JPanel panel;
    private ArrayList<Staff> staff; 
    private SystemPageObserver observer;

    public LoginPage(SystemPageObserver observer) {
        this.observer = observer;

        panel = new JPanel(null);
        JLabel usernameLabel = new JLabel("ID:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel wrongPassword = new JLabel("Incorrect ID or Password. Please Try Again.");
        JTextField usernameTextField = new JTextField("201111");
        JPasswordField passwordField = new JPasswordField("BobSmith1!");
        JButton loginButton = new JButton("Login");
        staff = Database.getStaffList();

        wrongPassword.setForeground(Color.red);
        wrongPassword.setBounds(20, 95, 400, 50);        
        
        //Log In Logic
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(usernameTextField.getText());
                    String password = new String(passwordField.getPassword());
                    boolean correct = false;
                    Staff loggedInStaff = null;
                    
                    for (Staff thisStaff: staff) {
                        if(ID == thisStaff.getId()) {
                            if (thisStaff.getPassword().equals(password)) {
                                correct = true;
                                loggedInStaff = thisStaff;
                                break;
                            }
                        } 
                    }
                    
                    if (correct) {
                        observer.notifyPasswordCorrect(loggedInStaff);
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

        //Set Bounds of Labels
        usernameLabel.setBounds(20, 20, 80, 25);
        passwordLabel.setBounds(20, 50, 80, 25);
        usernameTextField.setBounds(100, 20, 160, 25);
        passwordField.setBounds(100, 50, 160, 25);
        loginButton.setBounds(100, 80, 80, 25);

        //Add Labels to panel
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(usernameTextField);
        panel.add(passwordField);
        panel.add(loginButton);

        //add panel to frame
        add(panel);

        setTitle("Login Page");
        setSize(300, 175);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}