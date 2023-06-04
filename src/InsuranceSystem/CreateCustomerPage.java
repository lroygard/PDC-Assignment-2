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
        createCustomer = createLabel("Create Customer",new Font(null,Font.BOLD,18),425,150,width,height);
        
        phoneNumber = createTextField("Enter Phone Number",x,y,width,height);
        email = createTextField("Enter Email",x,y+50,width,height);
        
        addSave();
        
        addToPanel();
    }
    
    public void addSave() {
        save = SystemPage.createButton("Save Details", x, 390, width, height);
        final ArrayList<JLabel>[] errorsWrapper = new ArrayList[]{new ArrayList<>()};

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JLabel label: errorsWrapper[0]) {
                    createCustomerP.remove(label);
                }
                
                errorsWrapper[0] = insSys.createCustomer(firstName.getText(), lastName.getText(), 
                Integer.parseInt(birthYear.getSelectedItem().toString()), phoneNumber.getText(), email.getText());
                
                for (JLabel label: errorsWrapper[0]) {
                    createCustomerP.add(label);
                }
                createCustomerP.revalidate();
                createCustomerP.repaint();
                repaint();
        }});
    }
    
    public void addToPanel() {
        createCustomerP.add(createCustomer);
        createCustomerP.add(save);
    }
    
    public void addLabels() {
        String[] labelNames = {"Phone Number:", "Email:"};
        int addition = 100;
        for (int i = 0; i < labelNames.length; i++) {
            addition += 50;
            JLabel label = createLabel(labelNames[i],font,x,y+addition,width,height);
            createCustomerP.add(label);
        }
    }
}
