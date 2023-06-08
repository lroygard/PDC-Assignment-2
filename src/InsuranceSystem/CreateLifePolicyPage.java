package InsuranceSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class CreateLifePolicyPage extends CreatePolicyPage {

    //Panel
    public JPanel createLifeP;

    //Components on Panel
    private JLabel createLifePolicy;
    private JLabel medicalCondition;
    private HashMap<String, JCheckBox> medicalConditions;
    private JComboBox hobbyRisk;
    private JComboBox occupationRisk;
    private JComboBox gym;
    private JComboBox smoker;

    public CreateLifePolicyPage() {
        //Initalise and set background colour of panel
        createLifeP = createPolicyP;
        createLifeP.setBackground(Color.white);

        //Create the heading
        createLifePolicy = SystemPage.createLabel("Life Policy", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        //Add the life policy labels
        addLLabels();

        //Edit bounds
        x += width;
        y = 150;

        //Initialise components
        createRisk();
        createGym();
        createSmoker();
        createMedicalConditions();

        //Add the components to the panel
        setAssetTotal(String.valueOf(calculateAssetTotal()));
        addToLPanel();
    }

    /**
     * Adds components to the panel.
     */
    private void addToLPanel() {
        createLifeP.add(createLifePolicy);
        createLifeP.add(hobbyRisk);
        createLifeP.add(occupationRisk);
        createLifeP.add(gym);
        createLifeP.add(smoker);
        createLifeP.add(medicalCondition);
    }

    /**
     * Adds labels to help show the auto policy information.
     */
    private void addLLabels() {
        //Array of labels
        String[] labelNames = {"Hobby Risk:", "Occupation Risk:", "Goes to gym?:", "Regular Smoker?:"};

        //Set Bounds
        x = 150;
        y = 150;

        //Create and add each label to the panel
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createLifeP.add(label);
        }
    }

    /**
     * Create the combo boxes that shows the risks of hobbies and occupation.
     */
    private void createRisk() {
        //Initalise combo boxes
        hobbyRisk = SystemPage.createComboBox(x, y += 50, width, height);
        occupationRisk = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (LifePolicy.Risk r : LifePolicy.Risk.values()) {
            hobbyRisk.addItem(r.toString().replace("_", " "));
            occupationRisk.addItem(r.toString().replace("_", " "));
        }
    }

    /**
     * Create the gym combo box
     */
    private void createGym() {
        //Initalise combo box
        gym = SystemPage.createComboBox(x, y += 50, width, height);

        //Add true and false
        gym.addItem("True");
        gym.addItem("False");
    }

    /**
     * Create the smoker combo box
     */
    private void createSmoker() {
        //Initalise combo box
        smoker = SystemPage.createComboBox(x, y += 50, width, height);

        //Add true and false
        smoker.addItem("True");
        smoker.addItem("False");
    }

    /**
     * Create the medical condition checkboxes
     */
    private void createMedicalConditions() {
        x += 50;

        //Create label
        medicalCondition = SystemPage.createLabel("Select Applicable Health Conditions", font, x - 150, y + 50, 300, height);

        //Intialise hashmap
        medicalConditions = new HashMap<>();

        y += 50;

        //Initialise conditions array
        String[] conditions = new String[LifePolicy.MedicalCondition.values().length];

        //get conditions and convert to string
        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = LifePolicy.MedicalCondition.values()[i].toString().replace("_", " ");
        }

        y += 10;

        //Intialise, position and add check boxes
        for (int i = 0; i < conditions.length; i++) {
            JCheckBox checkBox = new JCheckBox(conditions[i]);

            if (i % 2 == 0) {
                checkBox.setBounds(x - 210, y += 25, 200, 25);
            } else {
                checkBox.setBounds(x, y, 200, 25);
            }

            checkBox.setBackground(Color.white);

            createLifeP.add(checkBox);
            medicalConditions.put(conditions[i], checkBox);
        }
    }

    /**
     * Calculate the total assets of the policy
     *
     * @return the total asset value
     */
    private double calculateAssetTotal() {
        //pre-set asset total
        return 500000;
    }

    /**
     * Remove previous errors
     */
    private void removePreviousErrors() {
        for (JLabel label : currentErrorLabels) {
            createLifeP.remove(label);
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
            createLifeP.add(label);
        }
        currentErrorLabels.addAll(errors);
    }

    @Override
    protected boolean updatePremium() {
        boolean valid = false;

        //Get values to check
        double checkAssetTotal = calculateAssetTotal();
        String checkCoverage = coverage.getText();

        //check for errors
        ArrayList<JLabel> errors = InsuranceSystem.checkAutoAndLife(checkCoverage, checkAssetTotal);

        //remove previous errors, if there is any
        removePreviousErrors();

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

        createLifeP.repaint();
        SystemPage.getInstance().repaint();
        return valid;
    }

    /**
     * Calculates and sets the premium of the policy
     */
    @Override
    protected void calculateAndSetPremium() {
        //Get each variable that is needed for the calculation
        double newCoverage = Double.valueOf(coverage.getText());
        String newHobbyRisk = hobbyRisk.getSelectedItem().toString();
        String newOccupationRisk = occupationRisk.getSelectedItem().toString();
        boolean newGym = Boolean.valueOf(gym.getSelectedItem().toString());
        boolean newSmoker = Boolean.valueOf(smoker.getSelectedItem().toString());
        ArrayList<String> selectedConditions = getSelectedConditions();

        double premium = LifePolicy.calculatePremium(newCoverage, newSmoker, newGym,
                newHobbyRisk, newOccupationRisk, selectedConditions);
        double premium2dp = Math.round(premium * 100.0) / 100.0;

        //Add premium and premium per frequency
        setPremium(String.valueOf(premium2dp));
        setPremiumPerFrequency(premium);
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

    /**
     * Creates a policy if the inputs are valid
     */
    @Override
    protected void createPolicy() {
        //check if inputs are valid & update the premium values
        boolean valid = updatePremium();

        if (valid == true) {
            //If there is a current customer
            if (InsuranceSystem.getInstance().currentCustomer != null) {
                //Create the policy
                createValidPolicy();
            } else {
                createPolicyError("Can't create Policy: no current customer");
            }
        } else {
            createPolicyError("Can't create Policy: Invalid Inputs");
        }

        //Repaint
        createLifeP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Creates a policy
     */
    @Override
    protected void createValidPolicy() {
        //Get variables needed
        int id = Database.getNextId("LIFEPOLICY");
        int customerId = InsuranceSystem.getInstance().currentCustomer.getId();

        double newAssetTotal = Double.valueOf(assetTotal.getText().replace("$", ""));
        double newCoverage = Double.valueOf(coverage.getText());
        double newYearlyPremium = Double.valueOf(yearlyPremium.getText().replace("$", ""));
        String newFrequency = frequency.getSelectedItem().toString();

        String newHobbyRisk = hobbyRisk.getSelectedItem().toString();
        String newOccupationRisk = occupationRisk.getSelectedItem().toString();
        boolean newGym = Boolean.valueOf(gym.getSelectedItem().toString());
        boolean newSmoker = Boolean.valueOf(smoker.getSelectedItem().toString());
        ArrayList<String> selectedConditions = getSelectedConditions();

        //Create the policy
        LifePolicy newPolicy = new LifePolicy(id, customerId, newAssetTotal,
                newCoverage, newYearlyPremium, newFrequency, selectedConditions,
                newHobbyRisk, newOccupationRisk, newGym, newSmoker);

        //Add the policy to the database and insurance system
        Database.addPolicy(newPolicy);
        InsuranceSystem.getInstance().currentCustomer.addPolicy(newPolicy);

        addSuccessLabel("Policy Successfully Created");
        addSuccessLabel("Life Policy ID: " + id);
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
        createLifeP.add(label);
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
        createLifeP.add(errorLabel);
    }
}
