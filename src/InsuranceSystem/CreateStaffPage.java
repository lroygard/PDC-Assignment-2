package InsuranceSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CreateStaffPage extends CreatePersonPage {

    JPanel createStaffP;
    JTextField extension;
    JTextField password;
    JButton save;
    JComboBox manager;
    
    public CreateStaffPage() {
        createStaffP = createPersonP;
        
        extension = createTextField("Enter Extension", x, y, width, height); 
        password = createTextField("Enter Password", x, y + 50, width, height);
        createManager();
        createSave();
        
        addToPanel();
    }
    
    public void addToPanel() {
        createStaffP.add(extension);
        createStaffP.add(password);
        createStaffP.add(manager);
        createStaffP.add(save);
    }
    
    public void createManager() {
        manager = createComboBox(x,y+100,width,height);
        manager.addItem("True");
        manager.addItem("False");
    }
    
    public void createSave() {
        save = createButton("Save Details", x, y+140, width, height);
        final ArrayList<JLabel>[] errorsWrapper = new ArrayList[]{new ArrayList<>()};
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JLabel label: errorsWrapper[0]) {
                    createStaffP.remove(label);
                }
                
                errorsWrapper[0] = insSys.createStaff(firstName.getText(), lastName.getText(), 
                    Integer.parseInt(birthYear.getSelectedItem().toString()), 
                    extension.getText(), password.getText(), 
                    Boolean.parseBoolean(manager.getSelectedItem().toString()));
                
                for (JLabel label: errorsWrapper[0]) {
                    createStaffP.add(label);
                }
                createStaffP.revalidate();
                createStaffP.repaint();
                repaint();
        }});
    }
}
