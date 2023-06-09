package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class ViewStaffPage extends ViewPersonPage {

    //Panel components will be added to
    public JPanel viewStaffP;
    private JLabel viewStaff;

    /**
     * Constructs a ViewStaffPage object with the specified staff.
     *
     * @param person the person to be viewed
     */
    public ViewStaffPage(Person person) {
        super(person);

        viewStaffP = viewPersonP;
        viewStaffP.setBackground(Color.white);

        //Create Title
        viewStaff = SystemPage.createLabel("View Staff", new Font(null, Font.BOLD, 18), 425, 150, width, height);
        viewStaffP.add(viewStaff);

        addLabels();
        addInfo();
    }

    /**
     * Adds labels to the view staff panel.
     */
    @Override
    protected void addLabels() {
        x = width * 2;

        String[] labelNames = {"Extension:", "Email:", "Password:", "Manager:"};

        for (int i = 0; i < labelNames.length; i++) {
            JLabel label = SystemPage.createLabel(labelNames[i], fontBold, x, y, width * 2, height);
            viewStaffP.add(label);
            y += 50;
        }
    }

    /**
     * Adds information to the view staff panel.
     */
    @Override
    protected void addInfo() {
        String[] information = person.getStringArray();

        x = width * 3;
        y = 350;

        for (int i = 0; i < information.length; i++) {
            JLabel label = SystemPage.createLabel(information[i], font, x, y, width * 2, height);
            viewStaffP.add(label);
            y += 50;
        }
    }
}
