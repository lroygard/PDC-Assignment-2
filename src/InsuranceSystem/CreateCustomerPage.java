package InsuranceSystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class CreateCustomerPage extends CreatePersonPage {
    
    JPanel createCustomerP;
    JLabel createCustomer;
    JTextField phoneNumber;
    JTextField email;
    JButton save; 
    
    public CreateCustomerPage() {
        createCustomerP = super.createPersonP;
        createCustomer = SystemPage.createLabel("Create Customer",new Font(null,Font.BOLD,18),425,150,width,height);
        
        phoneNumber = SystemPage.createTextField("Enter Phone Number",300,300,width,height);
        email = SystemPage.createTextField("Enter Email",300,350,width,height);
        
        createSave();
        addCLabels();
        addToCPanel();
    }
    
    public void createSave() {
        save = SystemPage.createButton("Save Details", x+width, 400, width, height);
        final ArrayList<JLabel>[] errorsWrapper = new ArrayList[]{new ArrayList<>()};

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JLabel label: errorsWrapper[0]) {
                    createCustomerP.remove(label);
                }
                
                errorsWrapper[0] = InsuranceSystem.getInstance().createCustomer(firstName.getText(), lastName.getText(), 
                Integer.parseInt(birthYear.getSelectedItem().toString()), phoneNumber.getText(), email.getText());
                
                for (JLabel label: errorsWrapper[0]) {
                    createCustomerP.add(label);
                }
                createCustomerP.repaint();
                SystemPage.getInstance().repaint();
        }});
    }
    
    public void addToCPanel() {
        createCustomerP.add(createCustomer);
        createCustomerP.add(phoneNumber);
        createCustomerP.add(email);
        createCustomerP.add(save);
    }
    
    public void addCLabels() {
        String[] labelNames = {"Phone Number:", "Email:"};
        y = 250;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createCustomerP.add(label);
        }
    }
    
}
