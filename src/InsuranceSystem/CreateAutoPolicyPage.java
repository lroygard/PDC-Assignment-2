package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CreateAutoPolicyPage extends CreatePolicyPage {

    //Panel
    public JPanel createAutoP;

    //Components on Panel
    private JLabel createAutoPolicy;
    private JComboBox make;
    private JComboBox model;
    private JComboBox year;
    private JComboBox currentLicense;
    private JComboBox accidentHistory;
    private JComboBox commercialUse;

    public CreateAutoPolicyPage() {
        //Initalise and set background colour of panel
        createAutoP = createPolicyP;
        createAutoP.setBackground(Color.white);

        //Create the heading
        createAutoPolicy = SystemPage.createLabel("Auto Policy", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        //Add the auto policy labels
        addALabels();

        //Edit bounds
        x += width;
        y = 150;

        //Initialise components
        createMake();
        createModel();
        createYear();
        createCurrentLicense();
        createAccidentHistory();
        createCommercialUse();

        //Add the components to the panel
        addToAPanel();
    }

    /**
     * Adds components to the panel.
     */
    private void addToAPanel() {
        createAutoP.add(createAutoPolicy);
        createAutoP.add(make);
        createAutoP.add(model);
        createAutoP.add(year);
        createAutoP.add(currentLicense);
        createAutoP.add(accidentHistory);
        createAutoP.add(commercialUse);
    }

    /**
     * Adds labels to help show the auto policy information.
     */
    private void addALabels() {
        //Array of labels
        String[] labelNames = {"Car Initalise:", "Car Model:", "Year Made:", "Current License:", "Accident History:", "Commercial Use:"};

        //Set Bounds
        x = 150;
        y = 150;

        //Create and add each label to the panel
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createAutoP.add(label);
        }
    }

    /**
     * Create the combo box that shows the makes of cars.
     */
    private void createMake() {
        //Initalise combo box
        make = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (AutoPolicy.CarBrand carBrand : AutoPolicy.CarBrand.values()) {
            make.addItem(carBrand.name());
        }

        //Add action listener to combo box
        make.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Add relevant models to the combo box
                addModels();
            }
        });
    }

    /**
     * Create the combo box that shows the models of cars.
     */
    private void createModel() {
        //Initalise combo box
        model = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        addModels();
    }

    /**
     * Adds the valid models into the model combo box depending on the make.
     */
    private void addModels() {
        //Get the current bran
        String brand = make.getSelectedItem().toString();

        //If brand isn't null or empty
        if (brand != null && !brand.isEmpty()) {
            //Get the models associated with brand
            ArrayList<CarModel> models = AutoPolicy.CARMODELS.get(AutoPolicy.CarBrand.valueOf(brand));

            //If models isnt null
            if (models != null) {
                //Remove previous models
                model.removeAllItems();

                //Add valid models
                for (CarModel carModel : models) {
                    model.addItem(carModel.getName());
                }
            }
        }
    }

    /**
     * Create the year combo box
     */
    private void createYear() {
        //Initalise combo box
        year = SystemPage.createComboBox(x, y += 50, width, height);

        //Add years
        for (int i = SystemPage.CURRENTYEAR; i > 1950; i--) {
            year.addItem(String.valueOf(i));
        }
    }

    /**
     * Create the current license combo box
     */
    private void createCurrentLicense() {
        //make combo box
        currentLicense = SystemPage.createComboBox(x, y += 50, width, height);

        //Add license types from enum
        for (AutoPolicy.LicenseType licenseType : AutoPolicy.LicenseType.values()) {
            currentLicense.addItem(licenseType.name());
        }
    }

    /**
     * Create the accident history combo box
     */
    private void createAccidentHistory() {
        //Initalise combo box
        accidentHistory = SystemPage.createComboBox(x, y += 50, width, height);

        //Add true and false
        accidentHistory.addItem("True");
        accidentHistory.addItem("False");
    }

    /**
     * Create the commercial use combo box
     */
    private void createCommercialUse() {
        //Initalise combo box
        commercialUse = SystemPage.createComboBox(x, y += 50, width, height);

        //Add true and false
        commercialUse.addItem("True");
        commercialUse.addItem("False");
    }

    /**
     * Calculate the total assets of the policy
     *
     * @return the total asset value
     */
    public double calculateAssetTotal() {
        String brand = make.getSelectedItem().toString();
        ArrayList<CarModel> models = AutoPolicy.CARMODELS.get(AutoPolicy.CarBrand.valueOf(brand));
        for (CarModel car : models) {
            if (car.getName().equals(model.getSelectedItem())) {
                return car.getCostNew() - (SystemPage.CURRENTYEAR - Integer.parseInt(year.getSelectedItem().toString())) * 250;
            }
        }
        return 0;
    }

    /**
     * Remove previous errors
     */
    private void removePreviousErrors() {
        for (JLabel label : currentErrorLabels) {
            createAutoP.remove(label);
        }
        currentErrorLabels.removeAll(currentErrorLabels);
    }

    /**
     * Show errors
     *
     * @param errors errors to show
     */
    @Override
    protected void showErrors(ArrayList<JLabel> errors) {
        for (JLabel label : errors) {
            createAutoP.add(label);
        }
        currentErrorLabels.addAll(errors);
    }

    /**
     * Update the premium value on the panel
     *
     * @return whether the inputs were valid
     */
    @Override
    public boolean updatePremium() {
        boolean valid = false;

        //Get values to check
        double checkAssetTotal = calculateAssetTotal();
        String checkCoverage = coverage.getText();

        //check for errors
        ArrayList<JLabel> errors = InsuranceSystem.checkAutoAndLife(checkCoverage, checkAssetTotal);

        //remove previous errors, if there is any
        removePreviousErrors();

        //Refresh Asset Total
        double newAssetTotal = calculateAssetTotal();
        if (newAssetTotal != 0) {
            setAssetTotal(String.valueOf(newAssetTotal));
        }

        //If there are no errors
        if (errors.isEmpty()) {
            calculateAndSetPremium();

            //All values passed the test
            valid = true;
        } else {
            //show current errors
            showErrors(errors);

            //Set Premium and Frequency to default
            setPremium("-");
            setPremiumPerFrequency(0);
        }

        createAutoP.repaint();
        SystemPage.getInstance().repaint();
        return valid;
    }

    /**
     * Calculates and sets the premium of the policy
     */
    @Override
    protected void calculateAndSetPremium() {
        double newCoverage = Double.valueOf(coverage.getText());
        String newFrequency = frequency.getSelectedItem().toString();
        String newMake = make.getSelectedItem().toString();
        String newModel = model.getSelectedItem().toString();
        int newYear = Integer.valueOf(year.getSelectedItem().toString());
        String newLicense = currentLicense.getSelectedItem().toString();
        boolean newAccident = Boolean.valueOf(accidentHistory.getSelectedItem().toString());
        boolean newCommercial = Boolean.valueOf(commercialUse.getSelectedItem().toString());

        double premium = AutoPolicy.calculatePremium(newCoverage, newFrequency, newMake, newModel, newYear, newLicense, newAccident, newCommercial);
        double premium2dp = Math.round(premium * 100.0) / 100.0;

        setPremium(String.valueOf(premium2dp));
        setPremiumPerFrequency(premium);
    }

    /**
     * Creates a policy if the inputs are valid
     */
    @Override
    protected void createPolicy() {
        //check if inputs are valid & update the premium values
        boolean valid = updatePremium();

        //If the inputs are valid
        if (valid == true) {
            //If there is a current customer
            if (InsuranceSystem.getInstance().currentCustomer != null) {
                //Create the policy
                createValidPolicy();
            } //Initalise a error label based on what went wrong
            else {
                createPolicyError("Can't create Policy: no current customer");
            }
        } else {
            createPolicyError("Can't create Policy: Invalid Inputs");
        }

        //Repaint
        createAutoP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Creates a policy
     */
    @Override
    protected void createValidPolicy() {
        //Get variables needed
        int id = Database.getNextId("AUTOPOLICY");
        int customerId = InsuranceSystem.getInstance().currentCustomer.getId();

        double newAssetTotal = Double.valueOf(assetTotal.getText().replace("$", ""));
        double newCoverage = Double.valueOf(coverage.getText());
        double newYearlyPremium = Double.valueOf(yearlyPremium.getText().replace("$", ""));
        String newFrequency = frequency.getSelectedItem().toString();

        String newMake = make.getSelectedItem().toString();
        String newModel = model.getSelectedItem().toString();
        int newYear = Integer.valueOf(year.getSelectedItem().toString());
        String newLicense = currentLicense.getSelectedItem().toString();
        boolean newAccident = Boolean.valueOf(accidentHistory.getSelectedItem().toString());
        boolean newCommercial = Boolean.valueOf(commercialUse.getSelectedItem().toString());

        //Create the policy
        AutoPolicy newPolicy = new AutoPolicy(id, customerId, newAssetTotal, newCoverage,
                newYearlyPremium, newFrequency, newMake, newModel, newYear, newLicense,
                newAccident, newCommercial);

        //Add the policy to the database and insurance system
        Database.addPolicy(newPolicy);
        InsuranceSystem.getInstance().currentCustomer.addPolicy(newPolicy);

        addSuccessLabel("Policy Successfully Created");
        addSuccessLabel("Auto Policy ID: " + id);
    }

    /**
     * Add a success Label
     *
     * @param message the message in the label
     */
    @Override
    protected void addSuccessLabel(String message) {
        JLabel label = SystemPage.createLabel(message, Color.BLUE, 525, 525, 300, height);
        currentErrorLabels.add(label);
        createAutoP.add(label);
    }

    /**
     * Add an error message
     *
     * @param errorMessage
     */
    @Override
    protected void createPolicyError(String errorMessage) {
        JLabel errorLabel = SystemPage.createLabel(errorMessage, Color.red, 525, 525, 300, height);
        currentErrorLabels.add(errorLabel);
        createAutoP.add(errorLabel);
    }
}
