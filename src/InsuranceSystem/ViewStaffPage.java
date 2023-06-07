package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class ViewStaffPage extends ViewPersonPage {
    //Panel components will be added to
    public JPanel viewStaffP;
    private JLabel viewStaff;
    public ViewStaffPage(Person person) {
        super(person);
        
        viewStaffP = viewPersonP;
        viewStaffP.setBackground(Color.white);
        
        //Create Title
        viewStaff = SystemPage.createLabel("View Staff",new Font(null,Font.BOLD,18),425,150,width,height);        
        viewStaffP.add(viewStaff);
        
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*2;
        
        String[] labelNames = {"Extension:", "Email:", "Manager:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width*2,height);
            viewStaffP.add(label);
        }
    }

    @Override
    protected void addInfo() {
        String[] information = person.getStringArray();

        x = width*3;
        y = 350;
        
        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width*2,height);
            viewStaffP.add(label);
        }
    }
}
