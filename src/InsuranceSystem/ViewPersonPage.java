package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public abstract class ViewPersonPage {
    //Panel components will be added to
    public JPanel viewPersonP;
    
    //Person to use
    Person person;
    
    //Bounds
    protected int x;
    protected int y;
    protected int width = 150;
    protected int height = 25;
    
    Font font = new Font(null, Font.PLAIN, 16);
    Font fontBold = new Font(null, Font.BOLD, 16);
    
    public ViewPersonPage(Person person) {
        this.person = person;
        
        viewPersonP = new JPanel(null);
        viewPersonP.setBackground(Color.white);
        
        addPersonLabels();
        addPersonInfo();
    }
    
    private void addPersonLabels() {
        x = width*2;
        y = 200;
        
        String[] labelNames = {"ID:", "Name:", "Age"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewPersonP.add(label);
        }
    }
    
    private void addPersonInfo() {
        String[] information = person.getPersonStringArray();
        
        x = width*3;
        
        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewPersonP.add(label);
        }
    }
    
    protected abstract void addLabels();
    protected abstract void addInfo();
}
