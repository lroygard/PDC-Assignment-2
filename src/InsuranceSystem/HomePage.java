package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class HomePage {
    
    JPanel homePageP;

    JLabel customer;
    JLabel staff;
    JLabel policy;
    JLabel options;
    
    //Customer option buttons
    JButton custDetails;
    JButton custPolicy;
    JButton custLookUp;
    JButton custNew;
    
    //Staff option buttons
    JButton staffDetails;
    JButton staffLookUp;
    JButton staffNew;
    JButton logOut;
    
    //Policy option buttons
    JButton polView;
    JButton polNewAuto;
    JButton polNewHome;
    JButton polNewLife;
    
    public HomePage() {
        homePageP = new JPanel(null);
        homePageP.setBackground(Color.WHITE);
        
        createHomePageLabels();
        createCustomerOptions();
        createPolicyOptions();
        createStaffOptions();
        
        addToPanel();
    }
    
    public JPanel getPanel() {
        return this.homePageP;
    }
    
    public void createHomePageLabels() {
        //Create Labels
        Font labelFont = new Font("Verdana", Font.BOLD, 16);
        Font optionsFont = new Font("Verdana", Font.BOLD, 20)  ;   
        
        options = SystemPage.createLabel("OPTIONS", optionsFont, 430,100,200,25);
        customer = SystemPage.createLabel("CUSTOMER",labelFont,175,150,200,25);
        policy = SystemPage.createLabel("POLICY",labelFont,450,150,200,25);
        staff = SystemPage.createLabel("STAFF", labelFont,725,150,200,25);
    }
    
        public void createCustomerOptions() {
        int x = 125;
        int y = 200;
        int width = 200;
        int height = 50;
        
        custDetails = SystemPage.createButton("View Customer Details", x, y, width, height);
        custPolicy = SystemPage.createButton("View Customer Policies", x, y+=75, width, height);
        custLookUp = SystemPage.createButton("Look up Customer", x, y+=75, width, height);
        custNew = SystemPage.createButton("Create a New Customer", x, y+75, width, height);
        
        custNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateCustomerPage ccp = new CreateCustomerPage();
                
            }});
    }
        
    public void createPolicyOptions() {
        int x = 390;
        int y = 200;
        int width = 200;
        int height = 50;

        polView = SystemPage.createButton("View Customer Policies", x, y, width, height);
        polNewAuto = SystemPage.createButton("Create a new Auto Policy", x, y+=75, width, height);
        polNewHome = SystemPage.createButton("Create a new Home Policy", x, y+=75, width, height);
        polNewLife = SystemPage.createButton("Create a new Life Policy", x, y+75, width, height);
    }
    
    public void createStaffOptions() {
        int x = 655;
        int y = 200;
        int width = 200;
        int height = 50;
        
        staffDetails = SystemPage.createButton("View/Change your details", x, y, width, height);
        staffLookUp = SystemPage.createButton("Look up Staff Members*", x, y+=75, width, height);
        staffNew = SystemPage.createButton("Create a new Staff Member*", x, y+=75, width, height);
        logOut = SystemPage.createButton("Log Out", x, y+75, width, height);
        
        staffNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
        }}); 
    }
    
    public void addToPanel() {
        homePageP.add(options);
        homePageP.add(customer);
        homePageP.add(staff);
        homePageP.add(policy);
        homePageP.add(custDetails);
        homePageP.add(custPolicy);
        homePageP.add(custLookUp);
        homePageP.add(custNew);
        homePageP.add(staffDetails);
        homePageP.add(staffLookUp);
        homePageP.add(staffNew);
        homePageP.add(logOut);
        homePageP.add(polView);
        homePageP.add(polNewAuto);
        homePageP.add(polNewHome);
        homePageP.add(polNewLife);
    }
}


