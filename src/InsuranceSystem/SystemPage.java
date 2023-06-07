package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SystemPage extends JFrame implements SystemPageObserver {
    public static final int CURRENTYEAR = 2023;
    
    private HomePage homePage;
    private JPanel currentPanel;
    
    //Components on background
    JLabel logo;
    JButton backButton;
    JLabel customerLabel;
    JLabel customerInfo;
    
    private static SystemPage instance;
    
    private SystemPage(){
        LoginPage lip = new LoginPage(this);
        lip.setVisible(true);
    };
    
    public static SystemPage getInstance() {
        if (instance == null) {
            instance = new SystemPage();
        }
        return instance;
    }
    
    @Override
    public void notifyPasswordCorrect(Staff loggedInStaff) {
        InsuranceSystem.getInstance().currentStaff = loggedInStaff;
        createPage();
    }
    
    private void createPage() {
        homePage = new HomePage();

        createLogo();
        createStaffVisual();
        createBackButton();
        createCustomerVisual();
              
        setTitle("Insurance System Tool");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); 

        showPanel(homePage.getPanel());
        
        setVisible(true);
    }
        
    private void createLogo() {
        logo = new JLabel();
        logo.setBounds(0,10,250,175);
        
        //Get Logo and resive it
        ImageIcon originalIcon = new ImageIcon("./resources/logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        logo.setIcon(resizedIcon);
        add(logo);
    }
    
    private void createBackButton() {
        backButton = createButton("Show HomePage", Color.WHITE, 390, 55, 200, 50);
        add(backButton);
        
        //When back button is pressed, restore home page
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel(homePage.getPanel());
            }
        });
    }
    
    private void createStaffVisual() {
        int x = 700;
        int y = 40;
        int width = 200;
        int height = 15;
        Font font = new Font("Calibri", Font.BOLD, 15);
        Font fontPlain = new Font("Calibri", Font.PLAIN, 15);
        
        JLabel userInfo = createLabel("Logged in as: ", font, x, y+=20, width, height);
        JLabel user = createLabel(InsuranceSystem.getInstance().currentStaff.getFullName(), fontPlain, x+width-100, y, width, height);
        JLabel managerInfo = createLabel("Manager Permissions: "+Boolean.toString(InsuranceSystem.getInstance().currentStaff.isManager()).toUpperCase(), font, x, y+20, width, height);

        add(user);
        add(userInfo);
        add(managerInfo);
    }

    private void createCustomerVisual() {
        int x = 700;
        int y = 115;
        int width = 200;
        int height = 15;
        
        Font font = new Font("Calibri", Font.BOLD, 15);
        Font fontPlain = new Font("Calibri", Font.PLAIN, 15);
        
        customerLabel = createLabel("Current Customer Selected: ", font, x, y, width, height);
        customerInfo = createLabel("", fontPlain, x+2, y+15, width, height);
        
        Customer customer = InsuranceSystem.getInstance().currentCustomer;
        
        updateCustomerVisual(customer);
        
        add(customerLabel);
        add(customerInfo);
    }
            
    public void updateCustomerVisual(Customer customer) {
        if (customer == null) {
            customerInfo.setText("None Selected");
        } else {
            int id = customer.getId();
            String fullName = customer.getFullName();
            
            customerInfo.setText(id + ": " + fullName);
        }
        repaint();
    }        
    
    //TODO own class
    private void viewCustomer() {
        //Create Panel
        JPanel custDetailP = new JPanel(null);   
        custDetailP.setBackground(Color.WHITE);
        //Get current customer
        Customer cust = InsuranceSystem.getInstance().currentCustomer;

        int x = 150;
        int y = 150;
        int width = 150;
        int height = 25;
        
        //String of label names to display
        String[] labelNames = {"ID:", "Name:", "Birth Year:", "Phone Number:", "Email:", "Number of Policies:"};
        Font font = new Font(null, Font.PLAIN, 16);

        //Add each label, add 50 to y and then add the next Label
        for (int i = 0; i < labelNames.length; i++) {
            JLabel label = createLabel(labelNames[i],font,x,y,width,height);
            custDetailP.add(label);
            y += 50;
        }
        
        x = 350;
        
        //Create Textfields for changable values and labels for viewable
        JLabel id = createLabel(String.valueOf(cust.getId()),font,x,150,width,height);
        JLabel birthYear = createLabel(String.valueOf(cust.getBirthYear()), font,x,250,width,height);
        JTextField firstName = createTextField(cust.getFirstName(),x,200,width,height);
        JTextField lastName = createTextField(cust.getLastName(),x+width,200,width,height);
        JTextField phoneNumber = createTextField(cust.getPhoneNumber(),x,300,width,height);
        JTextField email = createTextField(cust.getEmail(),x,350,width,height);
        
        //Save button
        JButton save = createButton("Save Details", 350, y-10, 200, 30);
        
        //Add Attributes to panel
        custDetailP.add(id);
        custDetailP.add(birthYear);
        custDetailP.add(firstName);
        custDetailP.add(lastName);
        custDetailP.add(phoneNumber);
        custDetailP.add(email);
        custDetailP.add(save);
        
        //Set this panel to the current panel
        this.currentPanel = custDetailP;
        add(currentPanel);
    }
    
    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        this.currentPanel = panel;
        add(currentPanel);
        validate();
        repaint();
    }

    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(224, 224, 224));
        return button;
    }
    
    public static JButton createButton(String text, Color colour, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(colour);
        return button;
    }
    
    public static JTextField createTextField(String text, int x, int y, int width, int height) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        return textField;
    }
    
    public static JComboBox createComboBox(int x, int y, int width, int height) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, width, height);
        return comboBox;
    }
    
    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }
    
    public static JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }
    
    public static JLabel createLabel(String text, Font font, Color colour, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(colour);
        label.setBounds(x, y, width, height);
        return label;
    }
    
    public static JLabel createLabel(String text, Color colour, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(colour);
        label.setBounds(x, y, width, height);
        return label;
    }
}

