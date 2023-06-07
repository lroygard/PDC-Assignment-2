package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class CreateLifePolicyPage extends CreatePolicyPage {
     // For repainting
    SystemPage sp;

    //Panel
    JPanel createLifeP;
    
    //Components on Panel
    JLabel createLifePolicy;
    JLabel medicalCondition;
    HashMap<String,JCheckBox> medicalConditions;
    JComboBox hobbyRisk; 
    JComboBox occupationRisk;
    JComboBox gym;
    JComboBox smoker;
    
    public CreateLifePolicyPage(SystemPage sp) {
        this.sp = sp;
        createLifeP = createPolicyP;
        createLifeP.setBackground(Color.white);
        
        createLifePolicy = SystemPage.createLabel("Life Policy",new Font(null,Font.BOLD,18),425,150,width,height);
        
        addLLabels();
        
        x+=width;
        y = 150;
        
        createRisk();
        createGym();
        createSmoker();
        createMedicalConditions();
        createCalculate();
        createSave();
        
        addListeners();
        setAssetTotal(String.valueOf(calculateAssetTotal()));
        addToLPanel();
    }
    
    private void addToLPanel() {
        createLifeP.add(hobbyRisk);
        createLifeP.add(occupationRisk);
        createLifeP.add(gym);
        createLifeP.add(smoker);
        createLifeP.add(medicalCondition);
        createLifeP.add(calculate);
        createLifeP.add(saveButton);
    }
           
    private void addLLabels() {
        String[] labelNames = {"Hobby Risk:", "Occupation Risk:", "Goes to gym?:", "Regular Smoker?:"};
        x = 150;
        y = 150;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createLifeP.add(label);
        }
    }
    
    
    private void createRisk() {
        hobbyRisk = SystemPage.createComboBox(x, y+=50, width, height);
        occupationRisk = SystemPage.createComboBox(x, y+=50, width, height);

        for(LifePolicy.Risk r: LifePolicy.Risk.values()) {
            hobbyRisk.addItem(r.toString().replace("_", " "));
            occupationRisk.addItem(r.toString().replace("_", " "));
        }
    }

    private void createGym() {
        gym = SystemPage.createComboBox(x,y+=50,width,height);
        gym.addItem("True");
        gym.addItem("False");
    }
    
    private void createSmoker() {
        smoker = SystemPage.createComboBox(x,y+=50,width,height);
        smoker.addItem("True");
        smoker.addItem("False");
    }
    
    private void createMedicalConditions() {
        x+=50;
        medicalCondition = SystemPage.createLabel("Select Applicable Health Conditions",font, x-150, y+50, 300, height);
        medicalConditions = new HashMap<>();
        y+=50;
        String[] conditions = new String[LifePolicy.MedicalCondition.values().length];
        
        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = LifePolicy.MedicalCondition.values()[i].toString().replace("_", " ");
        }
        
        y += 10;
        for (int i = 0; i < conditions.length; i++) {
            JCheckBox checkBox = new JCheckBox(conditions[i]);
            if (i % 2 == 0) {
                checkBox.setBounds(x-210,y+=25,200,25);
            }
            else {
                checkBox.setBounds(x,y,200,25);
            }
            checkBox.setBackground(Color.white);
            createLifeP.add(checkBox);
            medicalConditions.put(conditions[i],checkBox);
        }
    }
    
    @Override
    protected void createCalculate() {
        calculate = SystemPage.createButton("Calculate Premium", 550, 450, width*2, height);
    
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePremium();
            }
        });
    }
    
    @Override
    protected void createSave() {
        saveButton = SystemPage.createButton("Add Policy to Customer", 550, 490, width*2, height);
    
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
        //pre-set asset total
        return 500000;
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
            createLifeP.remove(label);
        }
        currentErrorLabels.removeAll(currentErrorLabels);
        
        if (errors.isEmpty()) {
            //Get each variable that is needed for the calculation
            double newCoverage = Double.valueOf(coverage.getText());
            String newHobbyRisk = hobbyRisk.getSelectedItem().toString();
            String newOccupationRisk = occupationRisk.getSelectedItem().toString();
            boolean newGym = Boolean.valueOf(gym.getSelectedItem().toString());
            boolean newSmoker = Boolean.valueOf(smoker.getSelectedItem().toString());
            ArrayList<String> selectedConditions = getSelectedConditions();
            
            double premium = LifePolicy.calculatePremium(newCoverage, newSmoker, newGym, 
                    newHobbyRisk, newOccupationRisk, selectedConditions);
            double premium2dp = Math.round(premium * 100.0)/100.0;
            
            //Add premium and premium per frequency
            setPremium(String.valueOf(premium2dp));
            setPremiumPerFrequency(premium);
            
            //All values passed the test
            valid = true;
        } else {
            //show current errors
            for (JLabel label: errors) {
                createLifeP.add(label);
            }
            currentErrorLabels.addAll(errors);

            //Set Premium and Frequency to default
            setPremium("-");
            setPremiumPerFrequency(0);
        }
            
        createLifeP.repaint();
        sp.repaint();
        return valid;
    }
    
    private ArrayList<String> getSelectedConditions() { 
        ArrayList<String> selectedConditions = new ArrayList<>();
        
        for (HashMap.Entry<String, JCheckBox> entry : medicalConditions.entrySet()) {
            String condition = entry.getKey();
            JCheckBox checkBox = entry.getValue();

            if (checkBox.isSelected()) {
                selectedConditions.add(condition);
            }
        }
        
        return selectedConditions;
    }
    
    @Override
    protected void createPolicy() {
        //check if inputs are valid & update the premium values
        boolean valid = updatePremium();
        
        if (valid == true) {
            if (sp.insSys.currentCustomer != null) {
                int id = Database.getNextId("LIFEPOLICY");
                int customerId = sp.insSys.currentCustomer.getId();

                double newAssetTotal = Double.valueOf(assetTotal.getText().replace("$", ""));
                double newCoverage = Double.valueOf(coverage.getText());
                double newYearlyPremium = Double.valueOf(yearlyPremium.getText().replace("$", ""));
                String newFrequency = frequency.getSelectedItem().toString();

                String newHobbyRisk = hobbyRisk.getSelectedItem().toString();
                String newOccupationRisk = occupationRisk.getSelectedItem().toString();
                boolean newGym = Boolean.valueOf(gym.getSelectedItem().toString());
                boolean newSmoker = Boolean.valueOf(smoker.getSelectedItem().toString());
                ArrayList<String> selectedConditions = getSelectedConditions();

                //Create and add policy to database
                LifePolicy newPolicy = new LifePolicy(id, customerId, newAssetTotal, 
                        newCoverage, newYearlyPremium, newFrequency, selectedConditions, 
                        newHobbyRisk, newOccupationRisk, newGym, newSmoker);
                Database.addPolicy(newPolicy);
                sp.insSys.currentCustomer.addPolicy(newPolicy);
                JLabel label = SystemPage.createLabel("Policy Successfully Created", Color.BLUE, 575, 525, 300, height);
                JLabel label2 = SystemPage.createLabel("Life Policy ID:"+id, Color.BLUE, 575, 550, 300, height);
                    currentErrorLabels.add(label);
                    currentErrorLabels.add(label2);
                    createLifeP.add(label);
                    createLifeP.add(label2);
                }
            else {
                JLabel errorLabel = SystemPage.createLabel("Can't create Policy: no current customer", 575, 525, 300, height);
                currentErrorLabels.add(errorLabel);
                createLifeP.add(errorLabel);
            }
        }
        else {
            JLabel errorLabel = SystemPage.createLabel("Can't create Policy due to Invalid Inputs", 575, 525, 300, height);
            currentErrorLabels.add(errorLabel);
            createLifeP.add(errorLabel);
        }
    }
}
