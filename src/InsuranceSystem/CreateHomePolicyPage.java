package InsuranceSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreateHomePolicyPage extends CreatePolicyPage {
    //Panel
    JPanel createHomeP;
    
    //Components on Panel
    JLabel createHomePolicy;
    JTextField address;
    JComboBox yearBuilt;
    JComboBox levels;
    JTextField squareMeters;
    JComboBox noBuildings;
    JComboBox wallMaterial;
    JComboBox roofMaterial;
    JComboBox constructionQuality;
    JTextField lastAppraisal;

    public CreateHomePolicyPage() {
        createHomeP = createPolicyP;
        createHomeP.setBackground(Color.white);
                
        createHomePolicy = SystemPage.createLabel("Home Policy",new Font(null,Font.BOLD,18),425,150,width,height);        
        
        addHLabels();
        
        x+=width;
        y = 150;
        
        createAddress();
        createYearBuilt();
        createLevels();
        createSquareMeters();
        createNoBuildings();
        createWallMaterial();
        createRoofMaterial();
        createConstructionQuality();
        createLastApprasial();
        
        createCalculate();
        createSave();
        
        addListeners();
        addToHPanel();
    }
    
    public void addToHPanel() {
        createHomeP.add(createHomePolicy);
        createHomeP.add(address);
        createHomeP.add(yearBuilt);
        createHomeP.add(levels);
        createHomeP.add(squareMeters);
        createHomeP.add(noBuildings);
        createHomeP.add(wallMaterial);
        createHomeP.add(roofMaterial);
        createHomeP.add(constructionQuality);
        createHomeP.add(lastAppraisal);
        
        createHomeP.add(calculate);
        createHomeP.add(saveButton);
    }

    private void addHLabels() {
        String[] labelNames = {"Address:", "Year Built:", "Levels:", "Square Meters:", "No. Buildings:", "Wall Material:", "Roof Material:", "Build Quality:", "Last Apprasial:"};
        x = 150;
        y = 150;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createHomeP.add(label);
        }
    }

    private void createAddress() {
        address = SystemPage.createTextField("Enter Address", x, y+=50, width, height);
    }
    
    private void createYearBuilt() {
        yearBuilt = SystemPage.createComboBox(x, y+=50, width, height);
        for (int i = SystemPage.CURRENTYEAR; i >= 1800; i--) {
            yearBuilt.addItem(i);
        }
    }

    private void createLevels() {
        levels = SystemPage.createComboBox(x, y+=50, width, height);
        for (int i = 1; i <= 10; i++) {
            levels.addItem(i);
        }
    }

    private void createSquareMeters() {
        squareMeters = SystemPage.createTextField("Enter No. Square Meters", x, y+=50, width, height);
    }

    private void createNoBuildings() {
        noBuildings = SystemPage.createComboBox(x, y+=50, width, height);
        for (int i = 1; i <= 10; i++) {
            noBuildings.addItem(i);
        }    
    }

    private void createWallMaterial() {
        wallMaterial = SystemPage.createComboBox(x, y+=50, width, height);
        for(HomePolicy.WallMaterial wm: HomePolicy.WallMaterial.values()) {
            wallMaterial.addItem(wm.toString().replace("_", " "));
        }
    }

    private void createRoofMaterial() {
        roofMaterial = SystemPage.createComboBox(x, y+=50, width, height);
        for(HomePolicy.RoofMaterial rm: HomePolicy.RoofMaterial.values()) {
            roofMaterial.addItem(rm.toString().replace("_", " "));
        }    
    }

    private void createConstructionQuality() {
        constructionQuality = SystemPage.createComboBox(x, y+=50, width, height);
        for(HomePolicy.Quality q: HomePolicy.Quality.values()) {
            constructionQuality.addItem(q.toString());
        }
    }
    
    private void createLastApprasial() {
        lastAppraisal = SystemPage.createTextField("Enter Value", x, y+=50, width, height);
        
        lastAppraisal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double newAssetTotal = calculateAssetTotal();
                if (newAssetTotal != 0) {
                    setAssetTotal(String.valueOf(newAssetTotal));
                }
            }
        });
    }
    
    @Override
    protected void createCalculate() {
        calculate = SystemPage.createButton("Calculate Premium", 525, 450, width*2-50, height);
    
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePremium();
            }
        });
    }
    
    protected void createSave() {
        saveButton = SystemPage.createButton("Add Policy to Customer", 525, 490, width*2-50, height);
    
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPolicy();
            }
        });
    }
        
    @Override
    public void addListeners() {        
        frequency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePremium();
            }
        });
    }
    
    public double calculateAssetTotal() {
        try {
            double total = Double.valueOf(lastAppraisal.getText());
            double total2dp = Math.round(total * 100.0)/100.0;
            return total2dp;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    @Override
    public boolean updatePremium() {
        boolean valid = false;
        
        //Get address and square meters
        String newAddress = address.getText();
        String checkSquareMeters = squareMeters.getText();
        double checkAssetTotal = calculateAssetTotal();
        String checkCoverage = coverage.getText();
               
        //Check for errors
        ArrayList<JLabel> errors = InsuranceSystem.checkHome(checkCoverage, checkAssetTotal, newAddress, checkSquareMeters);
        
        //remove previous errors, if there is any
        for (JLabel label: currentErrorLabels) {
            createHomeP.remove(label);
        }
        currentErrorLabels.removeAll(currentErrorLabels);
        
        //Refresh Asset Total
        double newAssetTotal = calculateAssetTotal();
        if (newAssetTotal != 0) {
            setAssetTotal(String.valueOf(newAssetTotal));
        }
        
        //If there are no errors
        if (errors.isEmpty()) {
            //Get each variable
            double newCoverage = Double.valueOf(coverage.getText());
            int newYearBuilt = Integer.valueOf(yearBuilt.getSelectedItem().toString());
            int newLevels = Integer.valueOf(levels.getSelectedItem().toString());
            int newSquareMeters = Integer.valueOf(squareMeters.getText());
            int newNoBuildings = Integer.valueOf(noBuildings.getSelectedItem().toString());
            String newWallMaterial = (wallMaterial.getSelectedItem().toString()).replaceAll(" ", "_");
            String newRoofMaterial = (roofMaterial.getSelectedItem().toString()).replaceAll(" ", "_");
            String newConstructionQuality = constructionQuality.getSelectedItem().toString();
            
            //Calculate Premium to 2dp
            double premium = HomePolicy.calculatePremium(newCoverage, newYearBuilt, newLevels, newSquareMeters, newNoBuildings, newWallMaterial, newRoofMaterial, newConstructionQuality);
            double premium2dp = Math.round(premium * 100.0)/100.0;
            
            //Add premium and premium per frequency
            setPremium(String.valueOf(premium2dp));
            setPremiumPerFrequency(premium);
            
            //All values passed the test
            valid = true;
        }
        else {
            //show current errors
            for (JLabel label: errors) {
                createHomeP.add(label);
            }
            currentErrorLabels.addAll(errors);
            
            //Set Premium and Frequency to default
            setPremium("-");
            setPremiumPerFrequency(0);
            
        }
        
        createHomeP.repaint();
        SystemPage.getInstance().repaint();
        
        return valid;
    }
    
    @Override
    protected void createPolicy() {
        //check if inputs are valid & update the premium values
        boolean valid = updatePremium();
        
        if (valid == true) {
            if (InsuranceSystem.getInstance().currentCustomer != null) {
                int id = Database.getNextId("HOMEPOLICY");
                int customerId = InsuranceSystem.getInstance().currentCustomer.getId();

                double newAssetTotal = Double.valueOf(assetTotal.getText().replace("$", ""));
                double newCoverage = Double.valueOf(coverage.getText());
                double newYearlyPremium = Double.valueOf(yearlyPremium.getText().replace("$", ""));
                String newFrequency = frequency.getSelectedItem().toString();

                String newAddress = address.getText();
                int newYearBuilt = Integer.valueOf(yearBuilt.getSelectedItem().toString());
                int newLevels = Integer.valueOf(levels.getSelectedItem().toString());
                int newSquareMeters = Integer.valueOf(squareMeters.getText());
                int newNoBuildings = Integer.valueOf(noBuildings.getSelectedItem().toString());
                String newWallMaterial = (wallMaterial.getSelectedItem().toString()).replaceAll(" ", "_");
                String newRoofMaterial = (roofMaterial.getSelectedItem().toString()).replaceAll(" ", "_");
                String newConstructionQuality = constructionQuality.getSelectedItem().toString();

                HomePolicy newPolicy = new HomePolicy(id, customerId, newAssetTotal, newCoverage,
                        newYearlyPremium, newFrequency, newAddress, newYearBuilt, newLevels, newSquareMeters,
                        newNoBuildings, newWallMaterial, newRoofMaterial, newConstructionQuality);
                Database.addPolicy(newPolicy);
                InsuranceSystem.getInstance().currentCustomer.addPolicy(newPolicy);
                JLabel label = SystemPage.createLabel("Policy Successfully Created", Color.BLUE, 525, 525, 300, height);
                JLabel label2 = SystemPage.createLabel("Home Policy Id: "+id, Color.BLUE, 525, 550, 300, height);
                currentErrorLabels.add(label);
                currentErrorLabels.add(label2);
                createHomeP.add(label);
                createHomeP.add(label2);
            } 
            else {
                JLabel errorLabel = SystemPage.createLabel("Can't create Policy: no current customer", 525, 525, 300, height);
                currentErrorLabels.add(errorLabel);
                createHomeP.add(errorLabel);
            }
        }
        else {
            JLabel errorLabel = SystemPage.createLabel("Can't create Policy due to Invalid Inputs", 525, 525, 300, height);
            currentErrorLabels.add(errorLabel);
            createHomeP.add(errorLabel);
        }
    }
}
