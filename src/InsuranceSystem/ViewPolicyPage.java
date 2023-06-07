package InsuranceSystem;

import java.awt.*;
import javax.swing.*;

public abstract class ViewPolicyPage {   
    //Panel components will be added to
    public JPanel viewPolicyP;
                
    JLabel viewPolicy;

    //Policy to use
    Policy policy;
    
    //Bounds
    protected int x;
    protected int y;
    protected int width = 150;
    protected int height = 25;
    
    Font font = new Font(null, Font.PLAIN, 16);
    Font fontBold = new Font(null, Font.BOLD, 16);

    public ViewPolicyPage(Policy policy) {
        this.policy = policy;
        
        viewPolicyP = new JPanel(null);
        viewPolicyP.setBackground(Color.white);
        
        viewPolicy = SystemPage.createLabel("View Policy",new Font(null,Font.BOLD,18),425,150,width*2,height);        
        viewPolicyP.add(viewPolicy);
        
        addPolicyLabels();
        addPolicyInfo();
    }

    
    private void addPolicyLabels() {
        x = width+75;
        y = 200;
        
        String[] labelNames = {"Policy ID:", "Customer ID:", "Total Asset $:", "Coverage:", "Premium:", "Payment Frq.:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewPolicyP.add(label);
        }
    }
    
    private void addPolicyInfo() {        
        //Turn into array
        String[] information = policy.getPolicyStringArray();
        
        x = width*2+75;
        y = 200;
        
        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewPolicyP.add(label);
        }
    }
    
    protected abstract void addLabels();
    protected abstract void addInfo();
}
