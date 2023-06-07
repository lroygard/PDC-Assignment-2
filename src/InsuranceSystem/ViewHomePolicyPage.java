package InsuranceSystem;

import javax.swing.*;

public class ViewHomePolicyPage extends ViewPolicyPage {
    
    JPanel viewHomePolicyP;
    HomePolicy homePolicy;
    
    public ViewHomePolicyPage(Policy policy) {
        super(policy);
        
        viewHomePolicyP = viewPolicyP;

        homePolicy = (HomePolicy) policy;
        addLabels();
        addInfo();
    }

    @Override
    protected void addLabels() {
        x = width*3+75;
        y = 200;
        
        String[] labelNames = {"Address:", "Year Built:", "Levels:", "Square Meters:", "No. Buildings:", "Wall Material:", "Roof Material", "Quality"};
        
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],fontBold,x,y,width,height);
            viewHomePolicyP.add(label);
        }
    }

    @Override
    protected void addInfo() {
        x = width*4+75;
        y = 200;
        
        String address = homePolicy.getAddress();
        String yearBuilt = String.valueOf(homePolicy.getYearBuilt());
        String levels = String.valueOf(homePolicy.getLevels());
        String squareMeters = String.valueOf(homePolicy.getSquareMeters());
        String noBuildings = String.valueOf(homePolicy.getNoBuildings());
        String wallMaterial = homePolicy.getWallMaterial().toString();
        String roofMaterial = homePolicy.getRoofMaterial().toString();
        String quality = homePolicy.getConstructionQuality().toString();
        
        String[] information = {address, yearBuilt, levels, squareMeters, noBuildings, wallMaterial, roofMaterial, quality};
                
        for (int i = 0; i < information.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(information[i],font,x,y,width,height);
            viewHomePolicyP.add(label);
        }        
    }
}
