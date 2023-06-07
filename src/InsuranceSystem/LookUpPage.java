package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public abstract class LookUpPage {
    //Panel to add components too
    JPanel lookUpP;
    
    //Components on panel
    JTextField searchId;
    JButton searchButtonId;
    JTextField searchName;
    JButton searchButtonName;
    
    JLabel idHeader;
    JLabel nameHeader;
    
    //Show results components
    JButton nextPage;
    JButton prevPage;
    
    ArrayList<JComponent> resultsComponents;
    int index;
    
    int x = 175;
    int y = 200;
    int width = 200;
    int height = 25;
    int gap = 150;
    
    public LookUpPage() {        
        //Create Panel
        lookUpP = new JPanel(null);
        lookUpP.setBackground(Color.WHITE);
        
        //Initialise arrayLists
        resultsComponents = new ArrayList<>();
        
        //Initialise Components
        createSearchId();
        createSearchName();
        createTable();
        createHeaders();
        createButtons();
        
        addToPanel();
    }
    
    private void addToPanel() {
        lookUpP.add(idHeader);
        lookUpP.add(nameHeader);
        lookUpP.add(searchId);
        lookUpP.add(searchButtonId);
        lookUpP.add(searchName);
        lookUpP.add(searchButtonName);
    }
    
    public void createHeaders() {
        x = 200;
        y = 250;
        Font font = new Font(null, Font.BOLD, 14);
        
        idHeader = SystemPage.createLabel("ID", font, x, y, width, height);
        nameHeader = SystemPage.createLabel("FULL NAME", font, x+gap, y, width, height);
    }
    
    private void createSearchId() {
        searchId = SystemPage.createTextField("Search by ID", x, y, width, height);
        searchButtonId = SystemPage.createButton("Search", x+=width, y, width/2, height-1);
        
        searchButtonId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResultsId();
        }}); 
    }
    
    private void createSearchName() {
        x+=width-35;
        searchName = SystemPage.createTextField("Search by Name", x, y, width, height);
        searchButtonName = SystemPage.createButton("Search", x+=width, y, width/2, height-1);
        
        searchButtonName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResultsName();
        }}); 
    }
    
    private void createTable() {
        x = 200;
        y = 250;
        
        //Add horizontal seperators
        for (int i = 0; i < 12; i++) {
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setBounds(x-5, y, 150 * 4, 1);
            separator.setForeground(Color.BLACK);
            lookUpP.add(separator);
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
            lookUpP.add(separator);
        }
    }
    
    private void createButtons() {    
        nextPage = SystemPage.createButton("Next Page", 690, 535, width/2, height);
        prevPage = SystemPage.createButton("Prev Page", 200, 535, width/2, height);
        
        nextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalPages = getTotalPages();
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
    
    
    public abstract int getTotalPages();
    public abstract void addResultsId();
    public abstract void addResultsName();
    public abstract void removePreviousLabels();
    public abstract void addResultsLabels();
    public abstract void changePage();
}
