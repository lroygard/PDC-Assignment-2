package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LookUpCustomerPage {
    //System Page for repainting
    SystemPage sp;
    
    //Panel to add components too
    JPanel lookUpCustomerP;
    
    //Components on panel
    JLabel lookUpCustomer;
    JTextField searchId;
    JButton searchButtonId;
    JTextField searchName;
    JButton searchButtonName;
    
    //Result View Components
    JLabel idHeader;
    JLabel nameHeader;
    JLabel noPoliciesHeader;
    JLabel currentCustomerHeader;
    
    //Show results components
    JButton nextPage;
    JButton prevPage;
    
    ArrayList<Customer> results;
    ArrayList<JComponent> resultsComponents;
    int index;

    int x = 175;
    int y = 200;
    int width = 200;
    int height = 25;
    int gap = 150;
    
    
    public LookUpCustomerPage(SystemPage sp) {
        this.sp = sp;
        
        //Create Panel and set Background
        lookUpCustomerP = new JPanel(null);
        lookUpCustomerP.setBackground(Color.WHITE);
        
        //Create Title
        lookUpCustomer = SystemPage.createLabel("Look Up Customer",new Font(null,Font.BOLD,18),425,150,width,height);        

        //Initialise arrayLists
        resultsComponents = new ArrayList<>();
        results = new ArrayList<>();
        
        //Initialise Components
        createSearchId();
        createSearchName();
        createResultView();
        
        
        //Add components to panel
        addToPanel();
    }
    public void addToPanel() {
        lookUpCustomerP.add(lookUpCustomer);
        lookUpCustomerP.add(searchId);
        lookUpCustomerP.add(searchButtonId);
        lookUpCustomerP.add(searchName);
        lookUpCustomerP.add(searchButtonName);
        lookUpCustomerP.add(idHeader);
        lookUpCustomerP.add(nameHeader);
        lookUpCustomerP.add(noPoliciesHeader);
        lookUpCustomerP.add(currentCustomerHeader);
    }
    
    public void createSearchId() {
        searchId = SystemPage.createTextField("Search by ID", x, y, width, height);
        searchButtonId = SystemPage.createButton("Search", x+=width, y, width/2, height-1);
        
        searchButtonId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResultsId();
        }}); 
    }
    
    public void createSearchName() {
        x+=width-35;
        searchName = SystemPage.createTextField("Search by Name", x, y, width, height);
        searchButtonName = SystemPage.createButton("Search", x+=width, y, width/2, height-1);
        
        searchButtonName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResultsName();
        }}); 
    }
    
    public void createResultView() {
        x = 200;
        y = 250;
        Font font = new Font(null, Font.BOLD, 14);
        
        idHeader = SystemPage.createLabel("ID", font, x, y, width, height);
        nameHeader = SystemPage.createLabel("FULL NAME", font, x+gap, y, width, height);
        noPoliciesHeader = SystemPage.createLabel("NO. POLICIES", font, x+gap*2, y, width, height);
        currentCustomerHeader = SystemPage.createLabel("SET CC", font, x+gap*3, y, width, height);
      
        addSeperators();
        
        nextPage = SystemPage.createButton("Next Page", 690, 535, width/2, height);
        prevPage = SystemPage.createButton("Prev Page", 200, 535, width/2, height);
        
        nextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalPages = (int) Math.ceil((double) results.size() / 10);
                if (index + 1 < totalPages) {
                    index++;
                    changePage();
                }
        }}); 
        
        prevPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index - 1 >= 0) {
                    index--;
                    changePage();
                } 
        }}); 
    }
    
    public void addSeperators() {
        //Add horizontal seperators
        for (int i = 0; i < 12; i++) {
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setBounds(x-5, y, 150 * 4, 1);
            separator.setForeground(Color.BLACK);
            lookUpCustomerP.add(separator);
            y+=25;
        }
        
        x = 50;
        y = 250;
        //Add vertical seperators
        for (int i = 0; i < 5; i++) {
            x+=150;
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            separator.setBounds(x-5, y, 1, 275);
            separator.setForeground(Color.BLACK);
            lookUpCustomerP.add(separator);
        }
    }
    
    
    public void addResultsId() {
        boolean check = InsuranceSystem.searchCheckId(searchId.getText());
        if (check) {
            //Get results and add them
            String id = searchId.getText();
            results = sp.insSys.searchCustomerId(id);
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
            sp.repaint();
        }
    }
    
    public void addResultsName() {
        String name = searchName.getText();
        results = sp.insSys.searchCustomerName(name);
        addResultsLabels();
    }
     
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
            }
            else {
                //If there are more then 10 results, create pages
                index = 0;
                changePage();
                addPageButtons();
            }
        }
        lookUpCustomerP.repaint();
        sp.repaint();
    } 
    
    public void changePage() {
        ArrayList<Customer> visibleResults = new ArrayList<>();
                    
        for (int i = 0; i < 10 && (i + (index * 10)) < results.size(); i++) {
            visibleResults.add(results.get(i + (index * 10)));
        }
        
        addResults(visibleResults);
        addPageButtons();
    }
    
    public void addPageButtons() {
        lookUpCustomerP.add(nextPage);
        resultsComponents.add(nextPage);
        lookUpCustomerP.add(prevPage);
        resultsComponents.add(prevPage);
    }
    
    public void addResults(ArrayList<Customer> visibleResults) {
        y = 275;

        //Remove the previous results
        removePreviousLabels();
        
         for (Customer customer: visibleResults) {
            int id = customer.getId();
            String fullName = customer.getFullName();
            int noPolicies = customer.getPolicies().size();

            JLabel idLabel = SystemPage.createLabel(String.valueOf(id), x, y, width, height);
            JLabel fullNameLabel = SystemPage.createLabel(fullName, x+gap, y, width, height);
            JLabel noPoliciesLabel = SystemPage.createLabel(String.valueOf(noPolicies), x+gap*2, y, width, height);

            JButton currentCustomerButton = SystemPage.createButton("Set Customer", x+gap*3-3, y+2, gap-4, height-4);

            currentCustomerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sp.insSys.currentCustomer = customer;
            }}); 

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
        sp.repaint();
    }
    
    public void removePreviousLabels() {
        for (JComponent component :resultsComponents) {
            lookUpCustomerP.remove(component);
        }
    }
}
