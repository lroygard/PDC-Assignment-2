package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SystemPage extends JFrame {
    public static final int CURRENTYEAR = 2023;
    public final InsuranceSystem insSys = new InsuranceSystem();
    
    private HomePage homePage = new HomePage();
    private JPanel currentPanel;
    
    //Components on background
    JLabel logo;
    JButton backButton;
      
    public SystemPage(Staff staff) {
        //setLayout(null);

        insSys.currentStaff = staff;
        insSys.currentCustomer = new Customer(1111,"Bob","Smith",2000,"(021) 215 0603", "bobsmith@gmail.com");

        createLogo();
        createStaffVisual();
        createBackButton();
              
        setTitle("Insurance System Tool");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); 

        showPanel(homePage.getPanel());
    }
        
    public void createLogo() {
        logo = new JLabel();
        logo.setBounds(0,-15,250,175);
        
        //Get Logo and resive it
        ImageIcon originalIcon = new ImageIcon("./resources/logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        logo.setIcon(resizedIcon);
        add(logo);
    }
    
    public void createBackButton() {
        backButton = createButton("Show HomePage", Color.WHITE, 390, 30, 200, 50);
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
        int y = 35;
        int width = 200;
        int height = 15;
        Font font = new Font("Calibri", Font.BOLD, 15);
        
        JLabel userInfo = createLabel("Logged in as: ", font, x, y+=20, width, height);
        JLabel user = createLabel(insSys.currentStaff.getFullName(), font, x, y+=20, width, height);
        JLabel managerInfo = createLabel("Manager Permissions: "+Boolean.toString(insSys.currentStaff.isManager()), font, x, y+=20, width, height);

        add(user);
        add(userInfo);
        add(managerInfo);
    }

    //TODO own class
    private void viewCustomer() {
        //Create Panel
        JPanel custDetailP = new JPanel(null);   
        custDetailP.setBackground(Color.WHITE);
        //Get current customer
        Customer cust = insSys.currentCustomer;

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
    
    //TODO own class
    public void createAutoPolicy() {
        JPanel createAutoPolicyP = new JPanel(null);
        Font font = new Font(null, Font.PLAIN, 16);
        
        int x = 150;
        int y = 250;
        int width = 150;
        int height = 25;
        
        JLabel createAutoPolicy = createLabel("Auto Policy",new Font(null,Font.BOLD,18),425,150,width,height);
        createAutoPolicy.add(createAutoPolicy);
        
                               //300        350          400          450                500                  550
        String[] labelNames = {"Car Make:", "Car Model", "Year Made", "Current License", "Accident History:", "Commercial Use"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = createLabel(labelNames[i], font, x, y, width, height);
            createAutoPolicyP.add(label);
        }
        
        x += width;
                
        JComboBox make = createComboBox(x, y+=50, width, height);
        for (AutoPolicy.CarBrand carBrand : AutoPolicy.CarBrand.values()) {
            make.addItem(carBrand.name());
        }
        
        JComboBox model = createComboBox(x,y+=50, width, height);
        
        make.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String brand = make.getSelectedItem().toString();
                if (brand != null && !brand.isEmpty()) {
                    ArrayList<CarModel> models = AutoPolicy.CARMODELS.get(make);
                    model.removeAll();
                    for (CarModel carModel: models) {
                        model.addItem(carModel.getName());
                    }
                }
            }
        });
        
        JComboBox year = createComboBox(x,y+=50,width,height);
        for (int i = CURRENTYEAR - 18; i > CURRENTYEAR - 100; i--) {
            year.addItem(String.valueOf(i));
        }
        
        JComboBox currentLicense = createComboBox(x,y+=50,width,height);
        for (AutoPolicy.LicenseType licenseType: AutoPolicy.LicenseType.values()) {
            currentLicense.addItem(licenseType.name());
        }
        
        JComboBox accidentHistory = createComboBox(x,y+=50,width,height);
        accidentHistory.addItem("True");
        accidentHistory.addItem("false");
        
        
        JComboBox commercialUse = createComboBox(x,y+=50,width,height);
        commercialUse.addItem("True");
        commercialUse.addItem("False");
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
}

