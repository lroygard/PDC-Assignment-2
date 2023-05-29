package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class SystemPage extends JFrame {
    
    private final JPanel panel;
    private JLabel customer;
    private JLabel staff;
    private JLabel policy;
    private JLabel options;
    private JLabel logo;
    
    private JButton custDetails;
    private JButton custPolicy;
    private JButton custLookUp;
    private JButton custNew;
    
    private JButton staffDetails;
    private JButton staffLookUp;
    private JButton staffNew;
    
    private JButton polView;
    private JButton polNewAuto;
    private JButton polNewHome;
    private JButton polNewLife;
    
    
    public SystemPage() {
        panel = new JPanel(null);
        
        labels();
        buttons();

        add(panel);
        
        setTitle("Insurance System Tool");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);   
    }    
    
    public void labels() {
        customer = new JLabel("CUSTOMER");
        staff = new JLabel("STAFF");
        policy = new JLabel("POLICY");
        options = new JLabel("OPTIONS");
        logo = new JLabel();
        panel.setBackground(Color.WHITE);
        
        ImageIcon originalIcon = new ImageIcon("./resources/logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(250, 175, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        logo.setIcon(resizedIcon);

        options.setBounds(425,100,200,25);
        customer.setBounds(175,150,200,25);
        policy.setBounds(450,150,200,25);
        staff.setBounds(725,150,200,25);
        logo.setBounds(0,-15,250,175);
        
        options.setFont(new Font("Verdana", Font.BOLD, 20));
        customer.setFont(new Font("Verdana", Font.BOLD, 16));
        policy.setFont(new Font("Verdana", Font.BOLD, 16));
        staff.setFont(new Font("Verdana", Font.BOLD, 16));
        
        panel.add(options);
        panel.add(customer);
        panel.add(staff);
        panel.add(policy);
        panel.add(logo);
    }

    public void buttons() {
        custDetails = new JButton("View Customer Details");
        custPolicy = new JButton("View Customer Policies");
        custLookUp = new JButton("Look up Customer");
        custNew = new JButton("Create a New Customer");

        staffDetails = new JButton("View/Change your details");
        staffLookUp = new JButton("Look up Staff Memebers*");
        staffNew = new JButton("Create a new Staff Member*");

        polView = new JButton("View Customer Policies");
        polNewAuto = new JButton("Create a new Auto Policy");
        polNewHome = new JButton("Create a new Home Policy");
        polNewLife = new JButton("Create a new Life Policy");
        
        custDetails.setBackground(Color.LIGHT_GRAY);
        custPolicy.setBackground(Color.LIGHT_GRAY);
        custLookUp.setBackground(Color.LIGHT_GRAY);
        custNew.setBackground(Color.LIGHT_GRAY);
        staffDetails.setBackground(Color.LIGHT_GRAY);
        
        //TODO - Something to the buttons if the user isnt a manager
        staffLookUp.setBackground(Color.LIGHT_GRAY);
        staffNew.setBackground(Color.LIGHT_GRAY);
        
        polView.setBackground(Color.LIGHT_GRAY);
        polNewAuto.setBackground(Color.LIGHT_GRAY);
        polNewHome.setBackground(Color.LIGHT_GRAY);
        polNewLife.setBackground(Color.LIGHT_GRAY);

        custDetails.setBounds(125, 200, 200, 50);
        custPolicy.setBounds(125, 275, 200, 50);
        custLookUp.setBounds(125, 350, 200, 50);
        custNew.setBounds(125, 425, 200, 50);
        
        staffDetails.setBounds(655, 200, 200, 50);
        staffLookUp.setBounds(655, 275, 200, 50);
        staffNew.setBounds(655, 350, 200, 50);

        polView.setBounds(390, 200, 200, 50);
        polNewAuto.setBounds(390, 275, 200, 50);
        polNewHome.setBounds(390, 350, 200, 50);
        polNewLife.setBounds(390, 425, 200, 50);

        panel.add(custDetails);
        panel.add(custPolicy);
        panel.add(custLookUp);
        panel.add(custNew);
        panel.add(staffDetails);
        panel.add(staffLookUp);
        panel.add(staffNew);
        panel.add(polView);
        panel.add(polNewAuto);
        panel.add(polNewHome);
        panel.add(polNewLife);
    }
}

