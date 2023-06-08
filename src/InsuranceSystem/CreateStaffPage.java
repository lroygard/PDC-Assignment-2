package InsuranceSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreateStaffPage extends CreatePersonPage {

    public JPanel createStaffP;

    private JLabel createStaff;
    private JTextField extension;
    private JTextField password;
    private JButton save;
    private JComboBox manager;

    public CreateStaffPage() {
        createStaffP = super.createPersonP;
        createStaff = SystemPage.createLabel("Create Staff", new Font(null, Font.BOLD, 18), 425, 150, width, height);

        extension = SystemPage.createTextField("Enter Extension", x + width, 300, width, height);
        password = SystemPage.createTextField("Enter Password", x + width, 350, width, height);
        createSave();
        addSLabels();
        createManager();

        addToSPanel();
    }

    /**
     * Create and add to manager combo box
     */
    private void createManager() {
        manager = SystemPage.createComboBox(x + width, 400, width, height);

        manager.addItem("True");
        manager.addItem("False");
    }

    /**
     * Creates the save button and adds the action listener
     */
    public void createSave() {
        save = SystemPage.createButton("Save Details", x + width, 450, width, height);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Attempt to create a staff, and add errors if unsuccessful
     */
    public void createStaff() {
        ArrayList<JLabel> errors = new ArrayList<>();

        //Remove previous labels
        for (JLabel label : errors) {
            createStaffP.remove(label);
        }

        //get next labels, and attempt create customer
        errors = InsuranceSystem.getInstance().createStaff(firstName.getText(), lastName.getText(),
                Integer.parseInt(birthYear.getSelectedItem().toString()),
                extension.getText(), password.getText(),
                Boolean.parseBoolean(manager.getSelectedItem().toString()));

        //Add next labels
        for (JLabel label : errors) {
            createStaffP.add(label);
        }

        //repaint
        createStaffP.repaint();
        SystemPage.getInstance().repaint();
    }

    /**
     * Add components to panel
     */
    public void addToSPanel() {
        createStaffP.add(createStaff);
        createStaffP.add(extension);
        createStaffP.add(password);
        createStaffP.add(manager);
        createStaffP.add(save);
    }

    /**
     * Create and add staff labels
     */
    public void addSLabels() {
        String[] labelNames = {"Extension:", "Password:", "Manager:"};
        y = 250;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i], font, x, y, width, height);
            createStaffP.add(label);
        }
    }
}
