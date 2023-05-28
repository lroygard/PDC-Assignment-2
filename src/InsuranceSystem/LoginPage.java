package InsuranceSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JPanel panel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        panel = new JPanel(null);
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                //TODO: sign in logic
            }
        });

        usernameLabel.setBounds(20, 20, 80, 25);
        passwordLabel.setBounds(20, 50, 80, 25);
        usernameTextField.setBounds(100, 20, 160, 25);
        passwordField.setBounds(100, 50, 160, 25);
        loginButton.setBounds(100, 80, 80, 25);

        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(usernameTextField);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        setTitle("Login Page");
        setSize(300, 175);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}