package InsuranceSystem;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public abstract class CreatePolicyPage {
    JPanel createPolicyP;
    Font font = new Font(null, Font.PLAIN, 16);
    
    JLabel assetTotal;
    JTextField coverage;
    JLabel yearlyPremium;
    JComboBox frequency;
    JLabel ppf;
    JButton calculate;
    JButton saveButton;
    
    //List of current error labels
    ArrayList<JLabel> currentErrorLabels;
    
    static final JLabel coverageTip = SystemPage.createLabel("* Must be between 1000 and Asset Total *", 525, 275, 300, 25);

    int x;
    int y;
    int width;
    int height;
    
    public CreatePolicyPage() {
        createPolicyP = new JPanel(null);
        createPolicyP.setBackground(Color.white);
        
        currentErrorLabels = new ArrayList<>();

        x = 150;
        y = 150;
        width = 150;
        height = 25;
        
        addPLabels();

        y = 200;
        assetTotal = SystemPage.createLabel("$-", font, x+=width, y, width, height);
        coverage = SystemPage.createTextField("Enter Coverage", x, y+=50, width, height);
        yearlyPremium = SystemPage.createLabel("$-", font, x, y+=50, width, height);
        createFrequency();
        ppf = SystemPage.createLabel("$-", font, x, y+=50, width, height);
        
        addToPanel();
    }

    public void setAssetTotal(String text) {
        assetTotal.setText("$"+text);
    }
    
    public void setPremium(String text) {
        yearlyPremium.setText("$"+text);
    }
    
    public void setPremiumPerFrequency(double premium) {
        if (premium != 0) {
            String selectedFrequency = frequency.getSelectedItem().toString();
            double payment;
            if (selectedFrequency.equals("FORTNIGHTLY")) {
                payment = premium/26.0;
            } 
            else if (selectedFrequency.equals("MONTHLY")) {
                payment = premium/12.0;
            }
            else if (selectedFrequency.equals("QUARTERLY")) {
                payment = premium/4.0;
            }
            else {
                payment = premium;
            }

            double payment2dp = Math.round(payment * 100.0)/100.0;

            ppf.setText("$"+String.valueOf(payment2dp));
        } else {
            ppf.setText("$-");
        }
    }
    
    public void addPLabels() {
        String[] labelNames = {"Total Asset Worth:", "Coverage:", "Yearly Premium:", "Frequency:", "Payment per Freq.:"};
        x += width*2 + 50;
        y = 150;
        for (int i = 0; i < labelNames.length; i++) {
            y += 50;
            JLabel label = SystemPage.createLabel(labelNames[i],font,x,y,width,height);
            createPolicyP.add(label);
        }
   }    

    private void addToPanel() {
        createPolicyP.add(assetTotal);
        createPolicyP.add(coverage);
        createPolicyP.add(yearlyPremium);
        createPolicyP.add(frequency);
        createPolicyP.add(ppf);
        createPolicyP.add(coverageTip);
    }

    private void createFrequency() {
        frequency = SystemPage.createComboBox(x, y+=50, width, height);
        for(Policy.PaymentFrequency freq : Policy.PaymentFrequency.values()) {
            frequency.addItem(freq.toString());
        }
    }

    public abstract void addListeners();
    protected abstract void createCalculate();
    protected abstract boolean updatePremium();
    protected abstract void createPolicy();
    protected abstract void createSave();
}
