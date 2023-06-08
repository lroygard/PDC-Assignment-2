package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class ViewCustomerPage extends ViewPersonPage {

    //Panel components will be added to
    public JPanel viewCustomerP;

    private JLabel viewCustomer;

    /**
     * Constructs a ViewCustomerPage object with the specified customer.
     *
     * @param person the person to be viewed
     */
    public ViewCustomerPage(Person person) {
        super(person);

        viewCustomerP = viewPersonP;
        viewCustomerP.setBackground(Color.white);

        viewCustomer = SystemPage.createLabel("View Customer", new Font(null, Font.BOLD, 18), 425, 150, width, height);
        viewCustomerP.add(viewCustomer);

        addLabels();
        addInfo();
    }

    /**
     * Adds labels to the view customer panel.
     */
    @Override
    protected void addLabels() {
        x = width * 2;

        String[] labelNames = {"Phone No.:", "Email:", "No. Policies:"};

        for (int i = 0; i < labelNames.length; i++) {
            JLabel label = SystemPage.createLabel(labelNames[i], fontBold, x, y, width * 2, height);
            viewCustomerP.add(label);
            y += 50;
        }
    }

    /**
     * Adds information to the view customer panel.
     */
    @Override
    protected void addInfo() {
        String[] information = person.getStringArray();

        x = width * 3;
        y = 350;

        for (int i = 0; i < information.length; i++) {
            JLabel label = SystemPage.createLabel(information[i], font, x, y, width * 2, height);
            viewCustomerP.add(label);
            y += 50;
        }
    }
}
