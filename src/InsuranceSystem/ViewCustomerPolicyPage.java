package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ViewCustomerPolicyPage {

    //Panel components will be added to
    public JPanel viewCustomerPolicyP;

    //Components to add to panel
    private JLabel disclaimer;
    private JLabel viewPolicy;
    private JButton prev;
    private JButton next;

    private ArrayList<Policy> policies;
    private ArrayList<JComponent> visiblePolicies;
    private int index = 0;

    //Bounds
    private int x;
    private int y;
    private int width = 150;
    private int height = 25;

    /**
     * Constructor for ViewCustomerPolicyPage
     */
    public ViewCustomerPolicyPage() {
        if (InsuranceSystem.getInstance().currentCustomer != null) {
            this.policies = InsuranceSystem.getInstance().currentCustomer.getPolicies();
        }

        visiblePolicies = new ArrayList<>();

        //Create Panel with null layout and set background
        viewCustomerPolicyP = new JPanel(null);
        viewCustomerPolicyP.setBackground(Color.white);

        //Create Title
        viewPolicy = SystemPage.createLabel("View Policies", new Font(null, Font.BOLD, 18), 425, 150, width, height);
        disclaimer = SystemPage.createLabel("Policies cannot be edited. Please delete and create a new Policy.", 300, 500, 400, height);

        //Initialise components
        intialiseButtons();
        createTable();
        addPolicyLabels();

        addToPanel();
    }

    /**
     * Add components to panel
     */
    private void addToPanel() {
        viewCustomerPolicyP.add(viewPolicy);
        viewCustomerPolicyP.add(disclaimer);
    }

    /**
     * Initialise the next and previous buttons
     */
    private void intialiseButtons() {

        //Create and set bounds
        prev = SystemPage.createButton("Prev", 200, 535, width / 2, height);
        next = SystemPage.createButton("Next", 690, 535, width / 2, height);

        //Add action listeners
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalPages = getTotalPages();
                if (index + 1 < totalPages) {
                    index++;
                    changePage();
                }
            }
        });

        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index - 1 >= 0) {
                    index--;
                    changePage();
                }
            }
        });
    }

    /**
     * Create the policy table
     */
    private void createTable() {
        //Headings to be on table
        String[] headings = {"Policy ID", "Premium", "Type", "View Policy", ""};

        x = 200;
        y = 200;

        //Add a label and seperator for each heading
        for (int i = 0; i < headings.length; i++) {
            JLabel label = SystemPage.createLabel(headings[i], x, y, width, height);

            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            separator.setBounds(x - 5, y, 1, 275);
            separator.setForeground(Color.BLACK);

            viewCustomerPolicyP.add(label);
            viewCustomerPolicyP.add(separator);

            x += width;
        }

        //Create 10 rows for policies to be in
        x = 200;
        for (int i = 0; i < 12; i++) {
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setBounds(x - 5, y, width * (headings.length - 1), 1);
            separator.setForeground(Color.BLACK);
            viewCustomerPolicyP.add(separator);
            y += 25;
        }

        y = 225;
    }

    /**
     * Shows the policies in the table
     */
    private void addPolicyLabels() {
        if (this.policies != null) { //If their is a current customer
            if (policies.isEmpty()) { //If the customer has no polcies
                JLabel label = SystemPage.createLabel("No Policies", Color.red, x, y, width, height);
                viewCustomerPolicyP.add(label);
            } else { //If the customer has policies
                if (policies.size() <= 10) { //If the customer has less than 10 policies
                    addPolicies(policies); //Add them all
                } else { //If the customer has more then 10
                    index = 0; //Set the page to 1
                    changePage(); //Create page 1
                    addPageButtons(); //Add the next and prev buttons
                }
            }
        } else { //If their isnt a current customer
            JLabel label = SystemPage.createLabel("No Customer Selected", Color.red, x, y, width, height);
            viewCustomerPolicyP.add(label);
        }
    }

    /**
     * Add policies into the table
     *
     * @param currentPolicies the policies that are to be shown
     */
    private void addPolicies(ArrayList<Policy> currentPolicies) {
        removePreviousPolicies(); //Remove previous policies
        y = 225;

        for (Policy policy : currentPolicies) {
            x = 200;

            //Get Information
            String policyId = String.valueOf(policy.getPolicyId());
            String premium = "$" + policy.getYearlyPremium();
            String type = getType(policy);

            //Turn information into labels
            JLabel idLabel = SystemPage.createLabel(policyId, x, y, width, height);
            JLabel premiumLabel = SystemPage.createLabel(premium, x += width, y, width, height);
            JLabel typeLabel = SystemPage.createLabel(type, x += width, y, width, height);
            JButton viewButton = SystemPage.createButton("View Policy", x + width - 3, y + 2, width - 4, height - 4);

            //Add an action listener if the user wants to view a policy
            viewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Show appropriate policy
                    if (type.equals("Auto")) {
                        ViewAutoPolicyPage vap = new ViewAutoPolicyPage(policy);
                        SystemPage.getInstance().showPanel(vap.viewAutoPolicyP);
                    } else if (type.equals("Life")) {
                        ViewLifePolicyPage vlp = new ViewLifePolicyPage(policy);
                        SystemPage.getInstance().showPanel(vlp.viewLifePolicyp);
                    } else {
                        ViewHomePolicyPage vhp = new ViewHomePolicyPage(policy);
                        SystemPage.getInstance().showPanel(vhp.viewHomePolicyP);
                    }
                }
            });

            visiblePolicies.add(idLabel);
            visiblePolicies.add(premiumLabel);
            visiblePolicies.add(typeLabel);
            visiblePolicies.add(viewButton);

            viewCustomerPolicyP.add(idLabel);
            viewCustomerPolicyP.add(premiumLabel);
            viewCustomerPolicyP.add(typeLabel);
            viewCustomerPolicyP.add(viewButton);

            y += height;
        }

        viewCustomerPolicyP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Takes a policy and returns what type it is in string version
     *
     * @param policy the policy to test
     * @return the string of the type
     */
    private String getType(Policy policy) {
        if (policy instanceof AutoPolicy) {
            return "Auto";
        } else if (policy instanceof HomePolicy) {
            return "Home";
        } else {
            return "Life";
        }
    }

    /**
     * Removes previous policies from the screen
     */
    private void removePreviousPolicies() {
        for (JComponent component : visiblePolicies) {
            viewCustomerPolicyP.remove(component);
        }
    }

    /**
     * Returns the number of pages
     *
     * @return it ha
     */
    private int getTotalPages() {
        return (int) Math.ceil((double) policies.size() / 10);
    }

    /**
     * changes the page
     */
    private void changePage() {
        ArrayList<Policy> visiblePolcies = new ArrayList<>();

        for (int i = 0; i < 10 && (i + (index * 10)) < policies.size(); i++) {
            visiblePolcies.add(policies.get(i + (index * 10)));
        }

        addPolicies(visiblePolcies);
        addPageButtons();
    }

    /**
     * Adds the page buttons to the screen
     */
    private void addPageButtons() {
        viewCustomerPolicyP.add(next);
        visiblePolicies.add(next);
        viewCustomerPolicyP.add(prev);
        visiblePolicies.add(prev);
    }
}
