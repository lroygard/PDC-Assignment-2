package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LookUpCustomerPage extends LookUpPage {

    //Panel to add components too
    public JPanel lookUpCustomerP;

    //Components on panel
    private JLabel lookUpCustomer;

    private ArrayList<Customer> results;

    //Result View Components
    private JLabel noPoliciesHeader;
    private JLabel currentCustomerHeader;

    public LookUpCustomerPage() {
        //Create Panel and set Background
        lookUpCustomerP = lookUpP;
        lookUpCustomerP.setBackground(Color.WHITE);

        //Create Title
        lookUpCustomer = SystemPage.createLabel("Look Up Customer", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        //Initialise Components
        createAdditionalHeaders();

        //initialise results
        results = new ArrayList<>();

        //Add components to panel
        addToPanel();
    }

    /**
     * Add components to panel
     */
    public void addToPanel() {
        lookUpCustomerP.add(lookUpCustomer);
        lookUpCustomerP.add(noPoliciesHeader);
        lookUpCustomerP.add(currentCustomerHeader);
    }

    /**
     * Creates the headers for the table.
     */
    public void createAdditionalHeaders() {
        x = 200;
        y = 250;
        Font font = new Font(null, Font.BOLD, 14);

        noPoliciesHeader = SystemPage.createLabel("NO. POLICIES", font, x + gap * 2, y, width, height);
        currentCustomerHeader = SystemPage.createLabel("SET CC", font, x + gap * 3, y, width, height);
    }

    /**
     * Add results when searching by id
     */
    @Override
    public void addResultsId() {
        boolean check = InsuranceSystem.searchCheckId(searchId.getText());
        if (check) {
            //Get results and add them
            String id = searchId.getText();
            results = InsuranceSystem.getInstance().searchCustomerId(id);
            addResultsLabels();
        } else {
            //Remove prev labels
            removePreviousLabels();

            //Show there are no results
            JLabel label = SystemPage.createLabel("Enter a number with 3+ digits", new Font(null, Font.PLAIN, 14), Color.red, 175, 175, 500, height);
            resultsComponents.add(label);
            lookUpCustomerP.add(label);

            //Repaint to add label
            lookUpCustomerP.repaint();
            SystemPage.getInstance().repaint();
        }
    }

    /**
     * Add results when searching by name
     */
    @Override
    public void addResultsName() {
        String name = searchName.getText();
        results = InsuranceSystem.getInstance().searchCustomerName(name);
        addResultsLabels();
    }

    /**
     * Add the labels from the results array
     */
    @Override
    public void addResultsLabels() {
        x = 200;
        y = 275;

        //If there are no results
        if (results.isEmpty()) {
            removePreviousLabels();
            //Show there are no results
            JLabel label = SystemPage.createLabel("No Results Found", new Font(null, Font.PLAIN, 14), Color.red, x, y, 500, height);
            resultsComponents.add(label);
            lookUpCustomerP.add(label);
        } else { //If there are results
            if (results.size() <= 10) {
                //If there are 10 or less results, show them
                addResults(results);
            } else {
                //If there are more then 10 results, create pages
                index = 0;
                changePage();
                addPageButtons();
            }
        }
        lookUpCustomerP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Removes the previous labels
     */
    @Override
    public void removePreviousLabels() {
        for (JComponent component : resultsComponents) {
            lookUpCustomerP.remove(component);
        }
    }

    /**
     * Changes the page depending on the index
     */
    @Override
    public void changePage() {
        ArrayList<Customer> visibleResults = new ArrayList<>();

        for (int i = 0; i < 10 && (i + (index * 10)) < results.size(); i++) {
            visibleResults.add(results.get(i + (index * 10)));
        }

        addResults(visibleResults);
        addPageButtons();
    }

    /**
     * Add results to screen from the arraylist given
     *
     * @param visibleResults the arraylist of results to be shown
     */
    public void addResults(ArrayList<Customer> visibleResults) {
        y = 275;

        //Remove the previous results
        removePreviousLabels();

        for (Customer customer : visibleResults) {
            //get information
            int id = customer.getId();
            String fullName = customer.getFullName();
            int noPolicies = customer.getPolicies().size();

            //turn information into labels
            JLabel idLabel = SystemPage.createLabel(String.valueOf(id), x, y, width, height);
            JLabel fullNameLabel = SystemPage.createLabel(fullName, x + gap, y, width, height);
            JLabel noPoliciesLabel = SystemPage.createLabel(String.valueOf(noPolicies), x + gap * 2, y, width, height);

            //create button to set customer
            JButton currentCustomerButton = SystemPage.createButton("Set Customer", x + gap * 3 - 3, y + 2, gap - 4, height - 4);

            currentCustomerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    InsuranceSystem.getInstance().currentCustomer = customer;
                    SystemPage.getInstance().updateCustomerVisual(customer);
                }
            });

            //Add labels to panel and components
            lookUpCustomerP.add(idLabel);
            lookUpCustomerP.add(fullNameLabel);
            lookUpCustomerP.add(noPoliciesLabel);
            lookUpCustomerP.add(currentCustomerButton);

            resultsComponents.add(idLabel);
            resultsComponents.add(fullNameLabel);
            resultsComponents.add(noPoliciesLabel);
            resultsComponents.add(currentCustomerButton);

            y += 25;
        }

        lookUpCustomerP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Adds the page buttons to the screen
     */
    public void addPageButtons() {
        lookUpCustomerP.add(nextPage);
        resultsComponents.add(nextPage);
        lookUpCustomerP.add(prevPage);
        resultsComponents.add(prevPage);
    }

    /**
     * Calculates the total number of pages
     *
     * @return the total number of pages
     */
    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) results.size() / 10);
    }
}
