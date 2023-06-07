package InsuranceSystem;

import javax.swing.*;

public class ViewAutoPolicyPage extends ViewPolicyPage {
    
    JPanel viewAutoPolicyP;
    AutoPolicy autoPolicy;
    
    public ViewAutoPolicyPage(Policy policy) {
        super(policy);
        
        viewAutoPolicyP = viewPolicyP;

        autoPolicy = (AutoPolicy) policy;
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*3+75;
        y = 200;
        
        String[] labelNames = {"Make:", "Model:", "Year:", "License:", "Accident History:", "Commercial Use:"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewAutoPolicyP.add(label);
        }
    }

    @Override
    protected void addInfo() {
        x = width*4+75;
        y = 200;
        
        String make = autoPolicy.getMake().toString();
        String model = autoPolicy.getModel().getName();
        String year = String.valueOf(autoPolicy.getYear());
        String currentLicense = autoPolicy.getCurrentLicense().toString();
        String accidentHistory = String.valueOf(autoPolicy.hasAccidentHistory()).toUpperCase();
        String commercialUse = String.valueOf(autoPolicy.isCommercialUse()).toUpperCase();
        
        String[] information = {make, model, year, currentLicense, accidentHistory, commercialUse};
                
        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewAutoPolicyP.add(label);
        }        
    }
}
