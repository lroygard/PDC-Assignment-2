package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage {

    public JPanel homePageP;

    private JLabel customer;
    private JLabel staff;
    private JLabel policy;
    private JLabel options;

    //Customer option buttons
    private JButton custDetails;
    private JButton custPolicy;
    private JButton custLookUp;
    private JButton custNew;

    //Staff option buttons
    private JButton staffDetails;
    private JButton staffLookUp;
    private JButton staffNew;
    private JButton logOut;

    //Policy option buttons
    private JButton polView;
    private JButton polNewAuto;
    private JButton polNewHome;
    private JButton polNewLife;

    private int x;
    private int y;
    private int width = 200;
    private int height = 50;

    public HomePage() {
        homePageP = new JPanel(null);
        homePageP.setBackground(Color.WHITE);

        createHomePageLabels();
        createCustomerOptions();
        createPolicyOptions();
        createStaffOptions();

        addToPanel();
    }

    /**
     * Returns the panel that the components are initialised on.
     *
     * @return the panel
     */
    public JPanel getPanel() {
        return this.homePageP;
    }

    /**
     * Adds titles
     */
    public void createHomePageLabels() {
        //Create Labels
        Font labelFont = new Font("Verdana", Font.BOLD, 16);
        Font optionsFont = new Font("Verdana", Font.BOLD, 20);

        options = SystemPage.createLabel("OPTIONS", optionsFont, 430, 175, 200, 25);
        customer = SystemPage.createLabel("CUSTOMER", labelFont, 175, 225, 200, 25);
        policy = SystemPage.createLabel("POLICY", labelFont, 450, 225, 200, 25);
        staff = SystemPage.createLabel("STAFF", labelFont, 725, 225, 200, 25);
    }

    /**
     * Adds buttons related to customer
     */
    public void createCustomerOptions() {
        x = 125;
        y = 275;

        custDetails = SystemPage.createButton("View Customer Details", x, y, width, height);
        custPolicy = SystemPage.createButton("View Customer Policies", x, y += 75, width, height);
        custLookUp = SystemPage.createButton("Look up Customer", x, y += 75, width, height);
        custNew = SystemPage.createButton("Create a New Customer", x, y + 75, width, height);

        custDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerDetails();
            }
        });

        custNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateCustomerPage ccp = new CreateCustomerPage();
                SystemPage.getInstance().showPanel(ccp.createCustomerP);
            }
        });

        custLookUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LookUpCustomerPage luc = new LookUpCustomerPage();
                SystemPage.getInstance().showPanel(luc.lookUpCustomerP);
            }
        });

        custPolicy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewCustomerPolicyPage vpp = new ViewCustomerPolicyPage();
                SystemPage.getInstance().showPanel(vpp.viewCustomerPolicyP);
            }
        });
    }
    
    /**
     * Process for when customer Details is clicked
     */
    private void customerDetails() {
        if (InsuranceSystem.getInstance().currentCustomer != null) {
            ViewCustomerPage vcp = new ViewCustomerPage(InsuranceSystem.getInstance().currentCustomer);
            SystemPage.getInstance().showPanel(vcp.viewCustomerP);
        } else {
            JOptionPane.showMessageDialog(null, "No Current Customer", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Adds buttons related to policy
     */
    public void createPolicyOptions() {
        x = 390;
        y = 275;

        polView = SystemPage.createButton("View Customer Policies", x, y, width, height);
        polNewAuto = SystemPage.createButton("Create a new Auto Policy", x, y += 75, width, height);
        polNewHome = SystemPage.createButton("Create a new Home Policy", x, y += 75, width, height);
        polNewLife = SystemPage.createButton("Create a new Life Policy", x, y + 75, width, height);

        polView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewCustomerPolicyPage vpp = new ViewCustomerPolicyPage();
                SystemPage.getInstance().showPanel(vpp.viewCustomerPolicyP);
            }
        });

        polNewAuto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateAutoPolicyPage cap = new CreateAutoPolicyPage();
                SystemPage.getInstance().showPanel(cap.createAutoP);
            }
        });

        polNewHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateHomePolicyPage chp = new CreateHomePolicyPage();
                SystemPage.getInstance().showPanel(chp.createHomeP);
            }
        });

        polNewLife.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateLifePolicyPage clp = new CreateLifePolicyPage();
                SystemPage.getInstance().showPanel(clp.createLifeP);
            }
        });
    }

    /**
     * Adds buttons related to staff
     */
    public void createStaffOptions() {
        x = 655;
        y = 275;

        staffDetails = SystemPage.createButton("View your details", x, y, width, height);

        staffDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewStaffPage vsp = new ViewStaffPage(InsuranceSystem.getInstance().currentStaff);
                SystemPage.getInstance().showPanel(vsp.viewStaffP);
            }
        });

        if (InsuranceSystem.getInstance().currentStaff.isManager()) {
            createManagerOptions();
        }

        logOut = SystemPage.createButton("Log Out", x, y + 75, width, height);

        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemPage.getInstance().removeStaffVisual();
                SystemPage.getInstance().removeCustomerVisual();
                SystemPage.getInstance().dispose();
                LoginPage login = new LoginPage(SystemPage.getInstance());
                login.setVisible(true);
            }
        });
    }

    /**
     * Adds buttons that need manager permissions
     */
    public void createManagerOptions() {
        staffLookUp = SystemPage.createButton("Look up Staff Members*", x, y += 75, width, height);
        staffNew = SystemPage.createButton("Create a new Staff Member*", x, y += 75, width, height);

        staffLookUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LookUpStaffPage lus = new LookUpStaffPage();
                SystemPage.getInstance().showPanel(lus.lookUpStaffP);
            }
        });

        staffNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateStaffPage csp = new CreateStaffPage();
                SystemPage.getInstance().showPanel(csp.createStaffP);
            }
        });

        homePageP.add(staffLookUp);
        homePageP.add(staffNew);
    }

    /**
     * Add components to panel
     */
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
        homePageP.add(logOut);
        homePageP.add(polView);
        homePageP.add(polNewAuto);
        homePageP.add(polNewHome);
        homePageP.add(polNewLife);
    }
}
