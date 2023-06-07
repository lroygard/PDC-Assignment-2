package InsuranceSystem;

import java.util.ArrayList;

public abstract class Policy {
    
    public static final int CURRENTYEAR = 2023;
    private int policyId;
    private final int customerId;
    private double assetTotal;
    private double coverage;
    private double yearlyPremium;
    private PaymentFrequency frequency;
    
    public Policy() {
        this.customerId = -1;
    }

    public Policy(int policyId, int customerId, double assetTotal, double coverage, double yearlyPremium, PaymentFrequency frequency) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.assetTotal = assetTotal;
        this.coverage = coverage;
        this.yearlyPremium = yearlyPremium;
        this.frequency = frequency;
    }
    
    public Policy(int policyId, int customerId, double assetTotal, double coverage, double yearlyPremium, String frequency) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.assetTotal = assetTotal;
        this.coverage = coverage;
        this.yearlyPremium = yearlyPremium;
        this.frequency = PaymentFrequency.valueOf(frequency);
    }

    public int getPolicyId() {
        return policyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAssetTotal() {
        return assetTotal;
    }

    public double getCoverage() {
        return coverage;
    }

    public double getYearlyPremium() {
        return yearlyPremium;
    }

    public PaymentFrequency getFrequency() {
        return frequency;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public void setAssetTotal(double assetTotal) {
        this.assetTotal = assetTotal;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public void setFrequency(PaymentFrequency frequency) {
        this.frequency = frequency;
    }

    public enum PaymentFrequency {
        FORTNIGHTLY,
        MONTHLY,
        QUARTERLY,
        YEARLY,
    }
    
    public abstract double calculatePremium();   
    protected abstract int createId();
    public abstract String[] getStringArray();
    
    public String[] getPolicyStringArray() {
        String[] array = new String[6];

        array[0] = (String.valueOf(this.policyId));
        array[1] = (String.valueOf(this.customerId));
        array[2] = ("$"+String.valueOf(this.assetTotal));
        array[3] = ("$"+String.valueOf(this.coverage));
        array[4] = ("$"+String.valueOf(this.yearlyPremium));
        array[5] = (this.frequency.toString());
        
        return array;
    }
    
    public static double checkCoverage(double coverage, double assetTotal) {
        if (coverage >= 1000 && coverage <= assetTotal) {
            return coverage;
        } 
        
        return -1;
    }
    
    
}
