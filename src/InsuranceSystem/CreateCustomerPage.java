package InsuranceSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class CreateCustomerPage extends CreatePersonPage {

    public JPanel createCustomerP;

    private JLabel createCustomer;
    private JTextField phoneNumber;
    private JTextField email;
    private JButton save;

    private final ArrayList<JLabel>[] errorsWrapper = new ArrayList[]{new ArrayList<>()};

    public CreateCustomerPage() {
        createCustomerP = super.createPersonP;
        createCustomer = SystemPage.createLabel("Create Customer", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        phoneNumber = SystemPage.createTextField("Enter Phone Number", x + width, 300, width, height);
        email = SystemPage.createTextField("Enter Email", x + width, 350, width, height);
        createSave();
        addCLabels();

        addToCPanel();
    }

    /**
     * Creates the save button and adds the action listener
     */
    private void createSave() {
        save = SystemPage.createButton("Save Details", x + width, 400, width, height);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCustomer();
            }
        });
    }

    /**
     * Attempt to create a customer, and add errors if unsuccessful
     */
    private void createCustomer() {
        //Remove previous labels
        for (JLabel label : errorsWrapper[0]) {
            createCustomerP.remove(label);
        }

        //get next labels, and attempt create customer
        errorsWrapper[0] = InsuranceSystem.getInstance().createCustomer(firstName.getText(), lastName.getText(),
                Integer.parseInt(birthYear.getSelectedItem().toString()), phoneNumber.getText(), email.getText());

        //Add next labels
        for (JLabel label : errorsWrapper[0]) {
            createCustomerP.add(label);
        }

        //repaint
        createCustomerP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Add components to panel
     */
    public void addToCPanel() {
        createCustomerP.add(createCustomer);
        createCustomerP.add(phoneNumber);
        createCustomerP.add(email);
        createCustomerP.add(save);
    }

    /**
     * Create and add customer labels
     */
    public void addCLabels() {
        String[] labelNames = {"Phone Number:", "Email:"};
        y = 250;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createCustomerP.add(label);
        }
    }
}
