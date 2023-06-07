package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class ViewCustomerPage extends ViewPersonPage {
        //Panel components will be added to
    public JPanel viewCustomerP;
    
    public ViewCustomerPage(Person person) {
        super(person);
        
        viewCustomerP = viewPersonP;
        viewCustomerP.setBackground(Color.white);
        
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*2;
        y = 200;
        
        String[] labelNames = {"Phone No.:", "Email:", "No. Policies:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewCustomerP.add(label);
        }
    }

    @Override
    protected void addInfo() {
        String[] information = person.getStringArray();

        x = width*3;

        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewCustomerP.add(label);
        }
    }
}
