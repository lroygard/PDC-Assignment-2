package InsuranceSystem;

import java.awt.Color;
import javax.swing.*;

public class ViewStaffPage extends ViewPersonPage {
    //Panel components will be added to
    public JPanel viewStaffP;
    
    public ViewStaffPage(Person person) {
        super(person);
        
        viewStaffP = viewPersonP;
        viewStaffP.setBackground(Color.white);
        
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*2;
        y = 200;
        
        String[] labelNames = {"Extension:", "Email:", "Manager:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewStaffP.add(label);
        }
    }

    @Override
    protected void addInfo() {
        String[] information = person.getStringArray();

        x = width*3;

        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewStaffP.add(label);
        }
    }
}
