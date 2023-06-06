package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public abstract class CreatePersonPage {
    JTextField firstName;
    JTextField lastName;    
    JComboBox birthYear;
    JPanel createPersonP;
    final Font font = new Font(null, Font.PLAIN, 16);;
    int x;
    int y;
    int width;
    int height;
    
    public CreatePersonPage() {
        x = 150;
        y = 150;
        width = 150;
        height = 25;
        createPersonP = new JPanel(null);
        createPersonP.setBackground(Color.WHITE);

        createLabels();
                
        firstName = SystemPage.createTextField("Enter First Name", x + width, 200, width, height);
        lastName = SystemPage.createTextField("Enter Last Name", x + width*2, 200, width, height);
        
        createBirthYear();
        addToPanel();
    }
    
    public void addToPanel() {
        createPersonP.add(firstName);
        createPersonP.add(lastName);
        createPersonP.add(birthYear);
    }
    
    public void createLabels() {
        String[] labelNames = {"Name:", "Birth Year:"};
        int addition = 0;
        for (String labelName : labelNames) {
            addition += 50;
            JLabel label = SystemPage.createLabel(labelName, font, x, y+addition, width, height);
            createPersonP.add(label);
        }
    }
    
    public void createBirthYear() {
        birthYear = SystemPage.createComboBox(x+width, 250, width, height);
        for (int i = SystemPage.CURRENTYEAR - 18; i > SystemPage.CURRENTYEAR - 100; i--) {
            birthYear.addItem(String.valueOf(i));
        }
    }
}
