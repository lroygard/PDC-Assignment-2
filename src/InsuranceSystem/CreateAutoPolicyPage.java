package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CreateAutoPolicyPage extends CreatePolicyPage {
    // For repainting
    SystemPage sp;
    
    //Panel
    JPanel createAutoP;
    
    //Components on Panek
    JLabel createAutoPolicy;
    JComboBox make;
    JComboBox model;
    JComboBox year;
    JComboBox currentLicense;
    JComboBox accidentHistory;
    JComboBox commercialUse;
    
    public CreateAutoPolicyPage(SystemPage sp) {
        this.sp = sp;
        createAutoP = createPolicyP;
        createAutoP.setBackground(Color.white);
        
        createAutoPolicy = SystemPage.createLabel("Auto Policy",new Font(null,Font.BOLD,18),425,150,width,height);
        createAutoP.add(createAutoPolicy);
        
        addALabels();
        
        x+=width;
        y = 150;
        
        createMake();
        createModel();
        createYear();
        createCurrentLicense();
        createAccidentHistory();
        createCommercialUse();
        createCalculate();
        createSave();
        
        addListeners();
        addToAPanel();
        setAssetTotal(String.valueOf(calculateAssetTotal()));
    }
   
    public void addToAPanel() {
        createAutoP.add(make);
        createAutoP.add(model);
        createAutoP.add(year);
        createAutoP.add(currentLicense);
        createAutoP.add(accidentHistory);
        createAutoP.add(commercialUse);
        createAutoP.add(calculate);
        createAutoP.add(saveButton);
    }
    
    public void addALabels() {
        String[] labelNames = {"Car Make:", "Car Model:", "Year Made:", "Current License:", "Accident History:", "Commercial Use:"};
        x = 150;
        y = 150;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createAutoP.add(label);
        }
   }    

    public void createMake() {
        make = SystemPage.createComboBox(x, y+=50, width, height);
        
        for (AutoPolicy.CarBrand carBrand : AutoPolicy.CarBrand.values()) {
            make.addItem(carBrand.name());
        }
        
        make.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addModels();
                setAssetTotal(String.valueOf(calculateAssetTotal()));
            }
        });
    }
    
    public void createModel() {
        model = SystemPage.createComboBox(x,y+=50, width, height);
        addModels();
                
        model.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAssetTotal(String.valueOf(calculateAssetTotal()));
            }
        });
    }
    
    public void addModels() {
        String brand = make.getSelectedItem().toString();
        if (brand != null && !brand.isEmpty()) {
            ArrayList<CarModel> models = AutoPolicy.CARMODELS.get(AutoPolicy.CarBrand.valueOf(brand));
            if (models != null) {
                model.removeAllItems();
                for (CarModel carModel: models) {
                    model.addItem(carModel.getName());
                }
            }
        }
    }

    public void createYear() {
        year = SystemPage.createComboBox(x,y+=50,width,height);
        for (int i = SystemPage.CURRENTYEAR; i > 1950; i--) {
            year.addItem(String.valueOf(i));
        }
    }
    
    public void createCurrentLicense(){
        currentLicense = SystemPage.createComboBox(x,y+=50,width,height);
        for (AutoPolicy.LicenseType licenseType: AutoPolicy.LicenseType.values()) {
            currentLicense.addItem(licenseType.name());
        }
    }
    
    public void createAccidentHistory() {
        accidentHistory = SystemPage.createComboBox(x,y+=50,width,height);
        accidentHistory.addItem("True");
        accidentHistory.addItem("False");
    }
    
    public void createCommercialUse() {
        commercialUse = SystemPage.createComboBox(x,y+=50,width,height);
        commercialUse.addItem("True");
        commercialUse.addItem("False");
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
    
    @Override
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
       String brand = make.getSelectedItem().toString();
        ArrayList<CarModel> models = AutoPolicy.CARMODELS.get(AutoPolicy.CarBrand.valueOf(brand));
        for (CarModel car: models) {
            if (car.getName().equals(model.getSelectedItem())) {
                return car.getCostNew() - (SystemPage.CURRENTYEAR - Integer.parseInt(year.getSelectedItem().toString())) * 250;
            }
        }
        return 0;
    }
    
    @Override
    public boolean updatePremium() {
        boolean valid = false;
        
        //Get values to check
        double checkAssetTotal = calculateAssetTotal();
        String checkCoverage = coverage.getText();
        
        //check for errors
        ArrayList<JLabel> errors = InsuranceSystem.checkAutoAndLife(checkCoverage, checkAssetTotal);

        //remove previous errors, if there is any
        for (JLabel label: currentErrorLabels) {
            createAutoP.remove(label);
        }
        currentErrorLabels.removeAll(currentErrorLabels);
        
        //Refresh Asset Total
        double newAssetTotal = calculateAssetTotal();
        if (newAssetTotal != 0) {
            setAssetTotal(String.valueOf(newAssetTotal));
        }
        
        //If there are no errors
        if (errors.isEmpty()) {
            //Get each variable that is needed for the calculation
            double newCoverage = Double.valueOf(coverage.getText());
            String newFrequency = frequency.getSelectedItem().toString();
            String newMake = make.getSelectedItem().toString();
            String newModel = model.getSelectedItem().toString();
            int newYear = Integer.valueOf(year.getSelectedItem().toString());
            String newLicense = currentLicense.getSelectedItem().toString();
            boolean newAccident = Boolean.valueOf(accidentHistory.getSelectedItem().toString());
            boolean newCommercial = Boolean.valueOf(commercialUse.getSelectedItem().toString());
            
            //If there are no errors
            double premium = AutoPolicy.calculatePremium(newCoverage, newFrequency, newMake, newModel, newYear, newLicense, newAccident, newCommercial);
            double premium2dp = Math.round(premium * 100.0)/100.0;
            
            //Add premium and premium per frequency
            setPremium(String.valueOf(premium2dp));
            setPremiumPerFrequency(premium);
            
            //All values passed the test
            valid = true;
        } else {
            //show current errors
            for (JLabel label: errors) {
                createAutoP.add(label);
            }
            currentErrorLabels.addAll(errors);
            
            //Set Premium and Frequency to default
            setPremium("-");
            setPremiumPerFrequency(0);
        }
        
        createAutoP.repaint();
        sp.repaint();
        return valid;
    }
    
    @Override
    protected void createPolicy() {
        //check if inputs are valid & update the premium values
        boolean valid = updatePremium();
        
        if (valid == true) {
            int id = Database.getNextId("AUTOPOLICY");
            int customerId = sp.insSys.currentCustomer.getId();
            
            double newAssetTotal = Integer.valueOf(assetTotal.getText());
            double newCoverage = Double.valueOf(coverage.getText());
            double newYearlyPremium = Double.valueOf(yearlyPremium.getText());
            String newFrequency = frequency.getSelectedItem().toString();
            
            String newMake = make.getSelectedItem().toString();
            String newModel = model.getSelectedItem().toString();
            int newYear = Integer.valueOf(year.getSelectedItem().toString());
            String newLicense = currentLicense.getSelectedItem().toString();
            boolean newAccident = Boolean.valueOf(accidentHistory.getSelectedItem().toString());
            boolean newCommercial = Boolean.valueOf(commercialUse.getSelectedItem().toString());
            
            //Create and add policy to database
            AutoPolicy newPolicy = new AutoPolicy(id, customerId, newAssetTotal, newCoverage, 
                    newYearlyPremium, newFrequency, newMake, newModel, newYear, newLicense, 
                    newAccident, newCommercial);
            Database.addPolicy(newPolicy);
            sp.insSys.currentCustomer.addPolicy(newPolicy);
        }
        else {
            JLabel errorLabel = SystemPage.createLabel("Can't create Policy due to Invalid Inputs", 525, 525, 300, height);
            currentErrorLabels.add(errorLabel);
            createAutoP.add(errorLabel);
        }
    }
}
