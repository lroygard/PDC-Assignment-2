package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public class ViewCustomerPage extends ViewPersonPage {
    //Panel components will be added to
    public JPanel viewCustomerP;
    
    private JLabel viewCustomer;
    
    public ViewCustomerPage(Person person) {
        super(person);
        
        viewCustomerP = viewPersonP;
        viewCustomerP.setBackground(Color.white);
        
        viewCustomer = SystemPage.createLabel("View Staff",new Font(null,Font.BOLD,18),425,150,width,height);        
        viewCustomerP.add(viewCustomer);

        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*2;
        
        String[] labelNames = {"Phone No.:", "Email:", "No. Policies:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width*2,height);
            viewCustomerP.add(label);
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
            viewCustomerP.add(label);
        }
    }
}