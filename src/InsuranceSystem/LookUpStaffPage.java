package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LookUpStaffPage extends LookUpPage {
    //Panel to add components too
    JPanel lookUpStaffP;
    
    //Components on panel
    JLabel lookUpStaff;
    
    //Result View Components
    JLabel extensionHeader;
    JLabel managerHeader;
    
    ArrayList<Staff> results;

    
    public LookUpStaffPage() {
        
        //Create Panel and set Background
        lookUpStaffP = lookUpP;
        lookUpStaffP.setBackground(Color.WHITE);
        
        //Create Title
        lookUpStaff = SystemPage.createLabel("Look Up Staff",new Font(null,Font.BOLD,18),425,150,width,height);        
        
        //initialise results
        results = new ArrayList<>();

        //Initialise Components
        createAdditionalHeaders();
        
        addToPanel();
    }
    
    public void addToPanel() {
        lookUpStaffP.add(lookUpStaff);
        lookUpStaffP.add(extensionHeader);
        lookUpStaffP.add(managerHeader);
    }
        
    public void createAdditionalHeaders() {
        x = 200;
        y = 250;
        Font font = new Font(null, Font.BOLD, 14);

        extensionHeader = SystemPage.createLabel("EXTENSION", font, x+gap*2, y, width, height);
        managerHeader = SystemPage.createLabel("MANAGER", font, x+gap*3, y, width, height);  
    }
    
    @Override
    public void addResultsId() {
        boolean check = InsuranceSystem.searchCheckId(searchId.getText());
        if (check) {
            //Get results and add them
            String id = searchId.getText();
            results = InsuranceSystem.getInstance().searchStaffId(id);
            addResultsLabels();
        } else {
            //Remove prev labels
            removePreviousLabels();
            
            //Show there are no results
            JLabel label = SystemPage.createLabel("Enter a number with 3+ digits", new Font(null, Font.PLAIN, 14), Color.red, 175, 175, 500, height);
            resultsComponents.add(label);
            lookUpStaffP.add(label);
            
            //Repaint to add label
            lookUpStaffP.repaint();
            SystemPage.getInstance().repaint();
        }
    }
    
    @Override
    public void addResultsName() {
        String name = searchName.getText();
        results = InsuranceSystem.getInstance().searchStaffName(name);
        addResultsLabels();
    }
    
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
            lookUpStaffP.add(label);
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
        lookUpStaffP.repaint();
        SystemPage.getInstance().repaint();
    } 
    
    @Override
    public void removePreviousLabels() {
        for (JComponent component :resultsComponents) {
            lookUpStaffP.remove(component);
        }
    }
    
    @Override
    public void changePage() {
        ArrayList<Staff> visibleResults = new ArrayList<>();
                    
        for (int i = 0; i < 10 && (i + (index * 10)) < results.size(); i++) {
            visibleResults.add(results.get(i + (index * 10)));
        }
        
        addResults(visibleResults);
        addPageButtons();
    }
    
    public void addResults(ArrayList<Staff> visibleResults) {
        y = 275;

        //Remove the previous results
        removePreviousLabels();
        
         for (Staff staff: visibleResults) {
            //get information
            int id = staff.getId();
            String fullName = staff.getFullName();
            int extension = staff.getExtension();
            boolean manager = staff.isManager();

            //turn information into labels
            JLabel idLabel = SystemPage.createLabel(String.valueOf(id), x, y, width, height);
            JLabel fullNameLabel = SystemPage.createLabel(fullName, x+gap, y, width, height);
            JLabel extensionLabel = SystemPage.createLabel(String.valueOf(extension), x+gap*2, y, width, height);
            JLabel managerLabel = SystemPage.createLabel(String.valueOf(manager).toUpperCase(), x+gap*3-3, y+2, gap-4, height-4);

            //Add labels to panel and components
            lookUpStaffP.add(idLabel);
            lookUpStaffP.add(fullNameLabel);
            lookUpStaffP.add(extensionLabel);
            lookUpStaffP.add(managerLabel);

            resultsComponents.add(idLabel);
            resultsComponents.add(fullNameLabel);
            resultsComponents.add(extensionLabel);
            resultsComponents.add(managerLabel);

            y += 25;
        }
         
        lookUpStaffP.repaint();
        SystemPage.getInstance().repaint();
    }
    
    public void addPageButtons() {
        lookUpStaffP.add(nextPage);
        resultsComponents.add(nextPage);
        lookUpStaffP.add(prevPage);
        resultsComponents.add(prevPage);
    }
    
    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) results.size() / 10);
    }
}
