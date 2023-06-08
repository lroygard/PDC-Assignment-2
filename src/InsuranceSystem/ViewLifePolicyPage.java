package InsuranceSystem;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLifePolicyPage extends ViewPolicyPage {

    public JPanel viewLifePolicyp;

    /**
     * Constructs a ViewLifePolicyPage object with the specified policy.
     *
     * @param policy the policy to be viewed
     */
    public ViewLifePolicyPage(Policy policy) {
        super(policy);

        viewLifePolicyp = viewPolicyP;

        addLabels();
        addInfo();
    }

    /**
     * Adds labels to the view life panel
     */
    @Override
    protected void addLabels() {
        x = width * 3 + 50;
        y = 175;

        String[] labelNames = {"Hobby Risk:", "Occupation Risk:", "Gym:", "Smoker:", "Conditions:"};

        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], fontBold, x, y, width+50, height);
            viewLifePolicyp.add(label);
        }
    }

    /**
     * Adds information to the view life panel
     */
    @Override
    protected void addInfo() {
        x = width * 4 + 50;
        y = 175;

        String[] information = policy.getStringArray();

        for (int i = 0; i < information.length; i++) {
            if (i < 5) { // Before medical conditions add 50 to y
                y += 50;
            } else if (i == 4 && information.length > 12) { //When it gets to the medical conditions, and there are too many to fit on the page
                x = width * 3 + 50;
                y += height + 50;
            } else if (i == 12) {
                x = width * 3 + 50;
                y = 450;
            } else {
                y += height;
            }

            JLabel label = SystemPage.createLabel(information[i], font, x, y, width * 2, height);

            viewLifePolicyp.add(label);
        }
    }

}
