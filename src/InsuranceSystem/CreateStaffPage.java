package InsuranceSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreateStaffPage extends CreatePersonPage {
    
    SystemPage sp;
    JPanel createStaffP;
    JLabel createStaff;
    JTextField extension;
    JTextField password;
    JButton save;
    JComboBox manager;
    
    public CreateStaffPage(SystemPage sp) {
        this.sp = sp;
        createStaffP = super.createPersonP;
        createStaff = SystemPage.createLabel("Create Staff",new Font(null,Font.BOLD,18),425,150,width,height);
        
        extension = SystemPage.createTextField("Enter Extension", x+width, 300, width, height); 
        password = SystemPage.createTextField("Enter Password", x+width, 350, width, height);
        
        createSave();
        addSLabels();
        createManager();
        addToSPanel();
    }
    
    public void createManager() {
        manager = SystemPage.createComboBox(x+width,400,width,height);
        manager.addItem("True");
        manager.addItem("False");
    }
    
    public void createSave() {
        save = SystemPage.createButton("Save Details", x+width, 450, width, height);
        final ArrayList<JLabel>[] errorsWrapper = new ArrayList[]{new ArrayList<>()};
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JLabel label: errorsWrapper[0]) {
                    createStaffP.remove(label);
                }
                
                errorsWrapper[0] = sp.insSys.createStaff(firstName.getText(), lastName.getText(), 
                    Integer.parseInt(birthYear.getSelectedItem().toString()), 
                    extension.getText(), password.getText(), 
                    Boolean.parseBoolean(manager.getSelectedItem().toString()));
                
                for (JLabel label: errorsWrapper[0]) {
                    createStaffP.add(label);
                }
                
                createStaffP.repaint();
                sp.repaint();
        }});
    }
    
    public void addToSPanel() {
        createStaffP.add(extension);
        createStaffP.add(password);
        createStaffP.add(manager);
        createStaffP.add(save);
    }
    
    public void addSLabels() {
        String[] labelNames = {"Extension:", "Password:", "Manager:"};
        y = 250;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createStaffP.add(label);
        }
    }
}
