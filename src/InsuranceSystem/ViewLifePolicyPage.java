package InsuranceSystem;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLifePolicyPage extends ViewPolicyPage{
    
    public JPanel viewLifePolicyp;
    
    public ViewLifePolicyPage(Policy policy) {
        super(policy);
        
        viewLifePolicyp = viewPolicyP;
                
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*3+50;
        y = 175;
        
        String[] labelNames = {"Hobby Risk:", "Occupation Risk:", "Gym:", "Smoker:", "Conditions:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewLifePolicyp.add(label);
        }
    }

    @Override
    protected void addInfo() {
        x = width*4+50;
        y = 175;
        
        String[] information = policy.getStringArray();
        
        for (int i = 0; i < information.length; i++) {
            if (i <= 4) {
                y += 50;
            } else {
                y += height;
            }
            
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width*2,height);
            
            viewLifePolicyp.add(label);
            
            //if (i > )
        }
    }
            
    
}
