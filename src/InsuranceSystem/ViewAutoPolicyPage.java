package InsuranceSystem;

import javax.swing.*;

public class ViewAutoPolicyPage extends ViewPolicyPage {

    public JPanel viewAutoPolicyP;

    /**
     * Constructs a ViewAutoPolicyPage object with the specified policy.
     *
     * @param policy the policy to be viewed
     */
    public ViewAutoPolicyPage(Policy policy) {
        super(policy);

        viewAutoPolicyP = viewPolicyP;

        addLabels();
        addInfo();
    }

    /**
     * Adds labels to the view auto panel
     */
    @Override
    protected void addLabels() {
        x = width * 3 + 50;
        y = 175;

        String[] labelNames = {"Make:", "Model:", "Year:", "License:", "Accident History:", "Commercial Use:"};

        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], fontBold, x, y, width, height);
            viewAutoPolicyP.add(label);
        }
    }

    /**
     * Adds information to the view auto panel
     */
    @Override
    protected void addInfo() {
        x = width * 4 + 50;
        y = 175;

        String[] information = policy.getStringArray();

        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i], font, x, y, width, height);
            viewAutoPolicyP.add(label);
        }
    }
}
