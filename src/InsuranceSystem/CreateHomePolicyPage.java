package InsuranceSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreateHomePolicyPage extends CreatePolicyPage {

    //Panel
    public JPanel createHomeP;

    //Components on Panel
    private JLabel createHomePolicy;
    private JTextField address;
    private JComboBox yearBuilt;
    private JComboBox levels;
    private JTextField squareMeters;
    private JComboBox noBuildings;
    private JComboBox wallMaterial;
    private JComboBox roofMaterial;
    private JComboBox constructionQuality;
    private JTextField lastAppraisal;

    public CreateHomePolicyPage() {
        //Initalise and set background colour of panel
        createHomeP = createPolicyP;
        createHomeP.setBackground(Color.white);

        //Create the heading
        createHomePolicy = SystemPage.createLabel("Home Policy", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        //Add the home policy labels
        addHLabels();

        //Edit bounds
        x += width;
        y = 150;

        //Initialise components
        createAddress();
        createYearBuilt();
        createLevels();
        createSquareMeters();
        createNoBuildings();
        createWallMaterial();
        createRoofMaterial();
        createConstructionQuality();
        createLastApprasial();

        //Add the components to the panel
        addToHPanel();
    }

    /**
     * Adds components to the panel.
     */
    private void addToHPanel() {
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
    }

    /**
     * Adds labels to help show the home policy information.
     */
    private void addHLabels() {
        //Array of labels
        String[] labelNames = {"Address:", "Year Built:", "Levels:", "Square Meters:", "No. Buildings:", "Wall Material:", "Roof Material:", "Build Quality:", "Last Apprasial:"};

        //Set Bounds
        x = 150;
        y = 150;

        //Create and add each label to the panek
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createHomeP.add(label);
        }
    }

    /**
     * Create the text field for the address.
     */
    private void createAddress() {
        address = SystemPage.createTextField("Enter Address", x, y += 50, width, height);
    }

    /**
     * Add the combo box that shows the year built.
     */
    private void createYearBuilt() {
        //Intialise combo box
        yearBuilt = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (int i = SystemPage.CURRENTYEAR; i >= 1800; i--) {
            yearBuilt.addItem(i);
        }
    }

    /**
     * Add the combo box that shows the amount of levels.
     */
    private void createLevels() {
        //Intialise combo box
        levels = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (int i = 1; i <= 10; i++) {
            levels.addItem(i);
        }
    }

    /**
     * Create the text field for the number of square meters.
     */
    private void createSquareMeters() {
        squareMeters = SystemPage.createTextField("Enter No. Square Meters", x, y += 50, width, height);
    }

    /**
     * Add the combo box that shows the amount of buildings.
     */
    private void createNoBuildings() {
        //Intialise combo box
        noBuildings = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (int i = 1; i <= 10; i++) {
            noBuildings.addItem(i);
        }
    }

    /**
     * Add the combo box that shows the wall material.
     */
    private void createWallMaterial() {
        //Intialise combo box
        wallMaterial = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (HomePolicy.WallMaterial wm : HomePolicy.WallMaterial.values()) {
            wallMaterial.addItem(wm.toString().replace("_", " "));
        }
    }

    /**
     * Add the combo box that shows the roof material.
     */
    private void createRoofMaterial() {
        //Intialise combo box
        roofMaterial = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (HomePolicy.RoofMaterial rm : HomePolicy.RoofMaterial.values()) {
            roofMaterial.addItem(rm.toString().replace("_", " "));
        }
    }

    /**
     * Add the combo box that shows the construction quality.
     */
    private void createConstructionQuality() {
        //Intialise combo box
        constructionQuality = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (HomePolicy.Quality q : HomePolicy.Quality.values()) {
            constructionQuality.addItem(q.toString());
        }
    }

    /**
     * Adds the text field to input the last appraisal
     */
    private void createLastApprasial() {
        //Initalise text field
        lastAppraisal = SystemPage.createTextField("Enter Value", x, y += 50, width, height);

        //Add action listener
        lastAppraisal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double newAssetTotal = calculateAssetTotal();
                if (newAssetTotal != 0) {
                    setAssetTotal(String.valueOf(newAssetTotal));
                }
            }
        });
    }

    /**
     * Calculate the total assets of the policy
     *
     * @return the total asset value
     */
    private double calculateAssetTotal() {
        try {
            double total = Double.valueOf(lastAppraisal.getText());
            double total2dp = Math.round(total * 100.0) / 100.0;
            return total2dp;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Remove previous errors
     */
    private void removePreviousErrors() {
        for (JLabel label : currentErrorLabels) {
            createHomeP.remove(label);
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
            createHomeP.add(label);
        }
        currentErrorLabels.addAll(errors);
    }

    @Override
    protected boolean updatePremium() {
        boolean valid = false;

        //Get address and square meters
        String newAddress = address.getText();
        String checkSquareMeters = squareMeters.getText();
        double checkAssetTotal = calculateAssetTotal();
        String checkCoverage = coverage.getText();

        //Check for errors
        ArrayList<JLabel> errors = InsuranceSystem.checkHome(checkCoverage, checkAssetTotal, newAddress, checkSquareMeters);

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

        createHomeP.repaint();
        SystemPage.getInstance().repaint();
        return valid;
    }

    /**
     * Calculates and sets the premium of the policy
     */
    @Override
    protected void calculateAndSetPremium() {
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
        double premium2dp = Math.round(premium * 100.0) / 100.0;

        //Add premium and premium per frequency
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

        if (valid == true) {
            if (InsuranceSystem.getInstance().currentCustomer != null) {

            } else {
                JLabel errorLabel = SystemPage.createLabel("Can't create Policy: no current customer", 525, 525, 300, height);
                currentErrorLabels.add(errorLabel);
                createHomeP.add(errorLabel);
            }
        } else {
            JLabel errorLabel = SystemPage.createLabel("Can't create Policy due to Invalid Inputs", 525, 525, 300, height);
            currentErrorLabels.add(errorLabel);
            createHomeP.add(errorLabel);
        }
    }

    /**
     * Creates a policy
     */
    @Override
    protected void createValidPolicy() {
        //Get variables needed
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

        //Create the policy
        HomePolicy newPolicy = new HomePolicy(id, customerId, newAssetTotal, newCoverage,
                newYearlyPremium, newFrequency, newAddress, newYearBuilt, newLevels, newSquareMeters,
                newNoBuildings, newWallMaterial, newRoofMaterial, newConstructionQuality);

        //Adds the policy to the databse and isurance system
        Database.addPolicy(newPolicy);
        InsuranceSystem.getInstance().currentCustomer.addPolicy(newPolicy);

        addSuccessLabel("Policy Successfully Created");
        addSuccessLabel("Home Policy ID: " + id);
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
        createHomeP.add(label);
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
        createHomeP.add(errorLabel);
    }
}
