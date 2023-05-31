package InsuranceSystem;

import java.util.ArrayList;

public class LifePolicy extends Policy {
    
    private ArrayList<MedicalCondition> medicalHistory;
    private Risk hobbyRisk;
    private Risk occupationRisk;
    private boolean gym;
    private boolean smoker;

    public LifePolicy(int policyId, int customerId, double assetTotal, double coverage, 
            double yearlyPremium, PaymentFrequency frequency, ArrayList<MedicalCondition> medicalHistory, 
            Risk hobbyRisk, Risk occupationRisk, boolean gym, boolean smoker) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.medicalHistory = medicalHistory;
        this.hobbyRisk = hobbyRisk;
        this.occupationRisk = occupationRisk;
        this.gym = gym;
        this.smoker = smoker;
    }

    public ArrayList<MedicalCondition> getMedicalHistory() {
        return medicalHistory;
    }

    public Risk getHobbyRisk() {
        return hobbyRisk;
    }

    public Risk getOccupationRisk() {
        return occupationRisk;
    }

    public boolean isGym() {
        return gym;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void addMedicalHistory(MedicalCondition medicalCondition) {
        this.medicalHistory.add(medicalCondition);
    }

    public void setHobbyRisk(Risk hobbyRisk) {
        this.hobbyRisk = hobbyRisk;
    }

    public void setOccupationRisk(Risk occupationRisk) {
        this.occupationRisk = occupationRisk;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }
    
    
    public static enum Risk {
        High,
        Medium,
        Low
    }
        
    public static enum MedicalCondition {
        Allergies,
        Anxiety,
        Arthritis,
        Asthma,
        Back_Pain,
        Bipolar_Disorder,
        Cancer,
        Chronic_Fatigue_Syndrome,
        Chronic_Obstructive_Pulmonary_Disease,
        Chrohns_Disease,
        Depression,
        Diabetes,
        Fibromyalgia,
        Gastroesophageal_Reflux_Disease,
        Heart_Disease,
        High_Blood_Pressure,
        High_Cholesterol,
        Irritable_Bowel_Syndrome,
        Migraines,
        Neck_Pain,
        Obesity,
        Osteoporosis,
        Sleep_Apnea,
        Thyroid_Disorders,
        Ulcerative_Colitis,
        Ulcers
    }
    
    @Override
    public double calculatePremium() {
        return -1;
    }

    @Override
    protected int createId() {
        return -1;
    }
}
