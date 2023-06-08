package InsuranceSystem;

import javax.swing.*;

public class ViewHomePolicyPage extends ViewPolicyPage {

    public JPanel viewHomePolicyP;

    /**
     * Constructs a ViewHomePolicyPage object with the specified policy.
     *
     * @param policy the policy to be viewed
     */
    public ViewHomePolicyPage(Policy policy) {
        super(policy);

        viewHomePolicyP = viewPolicyP;

        addLabels();
        addInfo();
    }

    /**
     * Adds labels to the view home panel
     */
    @Override
    protected void addLabels() {
        x = width * 3 + 75;
        y = 175;

        String[] labelNames = {"Address:", "Year Built:", "Levels:", "Square Meters:", "No. Buildings:", "Wall Material:", "Roof Material:", "Quality:"};

        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], fontBold, x, y, width, height);
            viewHomePolicyP.add(label);
        }
    }

    /**
     * Adds information to the view home panel
     */
    @Override
    protected void addInfo() {
        x = width * 4 + 75;
        y = 175;

        String[] information = policy.getStringArray();

        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i], font, x, y, width, height);
            viewHomePolicyP.add(label);
        }
    }
}
