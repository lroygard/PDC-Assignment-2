package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public abstract class CreatePersonPage {

    public JPanel createPersonP;

    protected JTextField firstName;
    protected JTextField lastName;
    protected JComboBox birthYear;

    final Font font = new Font(null, Font.PLAIN, 16);

    //bounds
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public CreatePersonPage() {
        //Intialise bounds
        x = 250;
        y = 150;
        width = 150;
        height = 25;

        //Initialise panel
        createPersonP = new JPanel(null);
        createPersonP.setBackground(Color.WHITE);

        //Initalise components
        createLabels();
        firstName = SystemPage.createTextField("Enter First Name", x + width, 200, width, height);
        lastName = SystemPage.createTextField("Enter Last Name", x + width * 2, 200, width, height);
        createBirthYear();

        //Add components to panel
        addToPanel();
    }

    /**
     * Add components to panel
     */
    public void addToPanel() {
        createPersonP.add(firstName);
        createPersonP.add(lastName);
        createPersonP.add(birthYear);
    }

    /**
     * Create and add person labels
     */
    public void createLabels() {
        String[] labelNames = {"Name:", "Birth Year:"};
        int addition = 0;
        for (String labelName : labelNames) {
            addition += 50;
            JLabel label = SystemPage.createLabel(labelName, font, x, y + addition, width, height);
            createPersonP.add(label);
        }
    }

    /**
     * Create and add to birth year combo box
     */
    public void createBirthYear() {
        birthYear = SystemPage.createComboBox(x + width, 250, width, height);

        for (int i = SystemPage.CURRENTYEAR - 18; i > SystemPage.CURRENTYEAR - 100; i--) {
            birthYear.addItem(String.valueOf(i));
        }
    }
}
