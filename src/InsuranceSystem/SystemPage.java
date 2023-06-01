package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SystemPage extends JFrame {
    
    private final InsuranceSystem insSys = new InsuranceSystem();
    private final JPanel homePage;
    private JPanel custDetailP;
    
    public SystemPage(Staff currentStaff) {
        homePage = new JPanel(null);
        custDetailP = new JPanel(null);        
        insSys.currentStaff = currentStaff;
        insSys.currentCustomer = new Customer(1111,"Bob","Smith",2000,"(021) 215 0603", "bobsmith@gmail.com");
        
        labels();
        customerButtons();
        staffButtons();
        policyButtons();
        staffVisual();
        add(homePage);
        
        setTitle("Insurance System Tool");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);   
    }    
    
    public void labels() {
        //Create Labels
        JLabel customer = new JLabel("CUSTOMER");
        JLabel staff = new JLabel("STAFF");
        JLabel policy = new JLabel("POLICY");
        JLabel options = new JLabel("OPTIONS");
        JLabel logo = new JLabel();
        homePage.setBackground(Color.WHITE);
        
        //Get Image and resize
        ImageIcon originalIcon = new ImageIcon("./resources/logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        logo.setIcon(resizedIcon);
        add(logo);

        //Set label bounds
        options.setBounds(425,100,200,25);
        customer.setBounds(175,150,200,25);
        policy.setBounds(450,150,200,25);
        staff.setBounds(725,150,200,25);
        logo.setBounds(0,-15,250,175);
        
        //set label fonts
        options.setFont(new Font("Verdana", Font.BOLD, 20));
        customer.setFont(new Font("Verdana", Font.BOLD, 16));
        policy.setFont(new Font("Verdana", Font.BOLD, 16));
        staff.setFont(new Font("Verdana", Font.BOLD, 16));
        
        //add labels to the homepage panel
        homePage.add(options);
        homePage.add(customer);
        homePage.add(staff);
        homePage.add(policy);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(224, 224, 224));
        return button;
    }
    
    private JTextField createTextField(String text, int x, int y, int width, int height) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        return textField;
    }
    
    private JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }
    
    public void customerButtons() {
        int x = 125;
        int y = 200;
        int width = 200;
        int height = 50;
        
        JButton custDetails = createButton("View Customer Details", x, y, width, height);
        JButton custPolicy = createButton("View Customer Policies", x, y+=75, width, height);
        JButton custLookUp = createButton("Look up Customer", x, y+=75, width, height);
        JButton custNew = createButton("Create a New Customer", x, y+75, width, height);
        
        custDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (insSys.currentCustomer != null) {
                    remove(homePage);
                    viewCustomer();
                    validate();
                    repaint();
                } else {
                    // No customer loaded logic
                }
        }});
        
        
        homePage.add(custDetails);
        homePage.add(custPolicy);
        homePage.add(custLookUp);
        homePage.add(custNew);
    }
    
    public void policyButtons() {
        int x = 390;
        int y = 200;
        int width = 200;
        int height = 50;

        JButton polView = createButton("View Customer Policies", x, y, width, height);
        JButton polNewAuto = createButton("Create a new Auto Policy", x, y+=75, width, height);
        JButton polNewHome = createButton("Create a new Home Policy", x, y+=75, width, height);
        JButton polNewLife = createButton("Create a new Life Policy", x, y+75, width, height);

        homePage.add(polView);
        homePage.add(polNewAuto);
        homePage.add(polNewHome);
        homePage.add(polNewLife);
    }
    
    public void staffButtons() {
        int x = 655;
        int y = 200;
        int width = 200;
        int height = 50;
        
        JButton staffDetails = createButton("View/Change your details", x, y, width, height);
        JButton staffLookUp = createButton("Look up Staff Members*", x, y+=75, width, height);
        JButton staffNew = createButton("Create a new Staff Member*", x, y+=75, width, height);
        JButton logOut = createButton("Log Out", x, y+75, width, height);
        
        homePage.add(staffDetails);
        homePage.add(staffLookUp);
        homePage.add(staffNew);
        homePage.add(logOut);
    }
    
    private void staffVisual() {
        JLabel user = new JLabel(insSys.currentStaff.getFullName());
        JLabel userInfo = new JLabel("Logged in as: ");
        JLabel managerInfo = new JLabel("Manager Permissions: "+Boolean.toString(insSys.currentStaff.isManager()));

        userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
        user.setFont(new Font("Calibri", Font.PLAIN, 15));
        managerInfo.setFont(new Font("Calibri", Font.BOLD, 15));

        userInfo.setBounds(750, 35, 100, 15);
        user.setBounds(750, 55, 100, 15);
        managerInfo.setBounds(750, 75, 250, 15);
        
        add(user);
        add(userInfo);
        add(managerInfo);
    }
    
    private void viewCustomer() {
        custDetailP.setBackground(Color.WHITE);
        Customer cust = insSys.currentCustomer;

        int x = 150;
        int y = 150;
        int width = 150;
        int height = 25;
        
        String[] labelNames = {"ID:", "Name:", "Birth Year:", "Phone Number:", "Email:", "Number of Policies:"};
        Font font = new Font(null, Font.PLAIN, 16);

        for (int i = 0; i < labelNames.length; i++) {
            JLabel label = createLabel(labelNames[i],font,x,y,width,height);
            custDetailP.add(label);
            y += 50;
        }
        
        x = 350;
        //Customer info
        JLabel id = createLabel(String.valueOf(cust.getId()),font,x,150,width,height);
        JLabel birthYear = createLabel(String.valueOf(cust.getBirthYear()), font,x,250,width,height);
        JTextField firstName = createTextField(cust.getFirstName(),x,200,width,height);
        JTextField lastName = createTextField(cust.getLastName(),x+width,200,width,height);
        JTextField phoneNumber = createTextField(cust.getPhoneNumber(),x,300,width,height);
        JTextField email = createTextField(cust.getEmail(),x,350,width,height);
        
        JButton save = createButton("Save Details", 350, y-10, 200, 30);
        
        custDetailP.add(id);
        custDetailP.add(birthYear);
        custDetailP.add(firstName);
        custDetailP.add(lastName);
        custDetailP.add(phoneNumber);
        custDetailP.add(email);
        custDetailP.add(save);

        add(custDetailP);
    }
    
    public static void main(String[] args) {
        SystemPage sp = new SystemPage(new Staff(1234,"Lily","Roygard",2004,100,"1234@gmail.com","hello",true));
        sp.setVisible(true);
    }
    
}

