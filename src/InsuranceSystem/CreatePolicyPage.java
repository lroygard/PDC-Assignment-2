package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public abstract class CreatePolicyPage {

    protected JPanel createPolicyP;
    protected Font font = new Font(null, Font.PLAIN, 16);

    protected JLabel assetTotal;
    protected JTextField coverage;
    protected JLabel yearlyPremium;
    protected JComboBox frequency;
    protected JLabel ppf;
    protected JButton calculate;
    protected JButton saveButton;

    //List of current error labels
    protected ArrayList<JLabel> currentErrorLabels;

    static final JLabel coverageTip = SystemPage.createLabel("* Must be between 1000 and Asset Total *", 525, 275, 300, 25);

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public CreatePolicyPage() {
        //Initalise and set background colour of panel
        createPolicyP = new JPanel(null);
        createPolicyP.setBackground(Color.white);

        //Initalise error labels
        currentErrorLabels = new ArrayList<>();

        //Initalise bounds
        x = 150;
        y = 150;
        width = 150;
        height = 25;

        //Add policy labels
        addPLabels();

        y = 200;

        //Initalise components
        assetTotal = SystemPage.createLabel("$-", font, x += width, y, width, height);
        coverage = SystemPage.createTextField("Enter Coverage", x, y += 50, width, height);
        yearlyPremium = SystemPage.createLabel("$-", font, x, y += 50, width, height);
        createFrequency();
        createCalculate();
        createSave();
        addListeners();
        ppf = SystemPage.createLabel("$-", font, x, y += 50, width, height);

        //Add component to panel
        addToPanel();
    }

    /**
     * Sets the asset total text.
     *
     * @param text the text to set it to
     */
    public void setAssetTotal(String text) {
        assetTotal.setText("$" + text);
    }

    /**
     * Sets the premium text.
     *
     * @param text the text to set it to
     */
    public void setPremium(String text) {
        yearlyPremium.setText("$" + text);
    }

    /**
     * Sets the premium per frequency text
     *
     * @param premium the premium to use for the calculation
     */
    public void setPremiumPerFrequency(double premium) {
        if (premium != 0) {
            //get frequency
            String selectedFrequency = frequency.getSelectedItem().toString();

            //Initalise ppq
            double payment;

            //divide by how many times you would pay through the year 
            if (selectedFrequency.equals("FORTNIGHTLY")) {
                payment = premium / 26.0;
            } else if (selectedFrequency.equals("MONTHLY")) {
                payment = premium / 12.0;
            } else if (selectedFrequency.equals("QUARTERLY")) {
                payment = premium / 4.0;
            } else {
                payment = premium;
            }

            //Round
            double payment2dp = Math.round(payment * 100.0) / 100.0;

            //Set text
            ppf.setText("$" + String.valueOf(payment2dp));
        } else {
            //If the premium is 0, the ppf cannot be calculated
            ppf.setText("$-");
        }
    }

    /**
     * Adds labels to help show the policy information.
     */
    public void addPLabels() {
        //Array of labels
        String[] labelNames = {"Total Asset Worth:", "Coverage:", "Yearly Premium:", "Frequency:", "Payment per Freq.:"};

        //Set Bounds
        x += width * 2 + 50;
        y = 150;

        //Create and add each label to the panel
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createPolicyP.add(label);
        }
    }

    /**
     * Adds components to the panel.
     */
    private void addToPanel() {
        createPolicyP.add(assetTotal);
        createPolicyP.add(coverage);
        createPolicyP.add(yearlyPremium);
        createPolicyP.add(frequency);
        createPolicyP.add(ppf);
        createPolicyP.add(coverageTip);
        createPolicyP.add(calculate);
        createPolicyP.add(saveButton);
    }

    /**
     * Creates the frequency combo box
     */
    private void createFrequency() {
        //Initalise combo box
        frequency = SystemPage.createComboBox(x, y += 50, width, height);

        //Add to combo box
        for (Policy.PaymentFrequency freq : Policy.PaymentFrequency.values()) {
            frequency.addItem(freq.toString());
        }
    }

    /**
     * Adds a listener to frequency so it will update
     */
    protected void addListeners() {
        frequency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePremium();
            }
        });
    }

    /**
     * Create the button for calculating the premium
     */
    protected void createCalculate() {
        //Initalise button
        calculate = SystemPage.createButton("Calculate Premium", 525, 450, width * 2 - 50, height);

        //When pressed, update premium
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePremium();
            }
        });
    }

    /**
     * Create the button for saving the policy
     */
    protected void createSave() {
        //Initialise button
        saveButton = SystemPage.createButton("Add Policy to Customer", 525, 490, width * 2 - 50, height);

        //When pressed, create the policy
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPolicy();
            }
        });
    }

    //Abstract methods
    protected abstract boolean updatePremium();

    protected abstract void showErrors(ArrayList<JLabel> errors);

    protected abstract void calculateAndSetPremium();

    protected abstract void createPolicy();

    protected abstract void createValidPolicy();

    protected abstract void createPolicyError(String errorMessage);

    protected abstract void addSuccessLabel(String message);
}
