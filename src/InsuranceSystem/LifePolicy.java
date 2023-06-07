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
    
    public LifePolicy(int policyId, int customerId, double assetTotal, double coverage, 
            double yearlyPremium, String frequency, ArrayList<String> medicalHistory, 
            String hobbyRisk, String occupationRisk, boolean gym, boolean smoker) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.medicalHistory = stringArrayToMedicalCondition(medicalHistory);
        this.hobbyRisk = Risk.valueOf(hobbyRisk);
        this.occupationRisk = Risk.valueOf(occupationRisk);
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
    
    public static enum Risk {
        High, 
        Medium,
        Low
    }
        
    public static enum MedicalCondition {
        Severe_Allergies,
        Arthritis,
        Asthma,
        Chronic_Fatigue_Syndrome,
        Chrohns_Disease,
        Diabetes,
        Heart_Disease,
        High_Blood_Pressure,
        High_Cholesterol,
        Irritable_Bowel_Syndrome,
        Migraines,
        Mental_Health_Disorder,
        Obesity,
        Osteoporosis,
        Sleep_Apnea,
        Other_Serious_Health_Disorder
    }
    
    public static double calculatePremium(double coverage, boolean isSmoker, boolean isGym, 
            String hobbyRisk, String occupationRisk, ArrayList<String> medicalConditions) {
            
        LifePolicy dummyLife = new LifePolicy(-1,-1, -1, coverage, -1, "MONTHLY", 
                medicalConditions, hobbyRisk, occupationRisk, isGym, isSmoker);
    
        return dummyLife.calculatePremium();
    }
    
    public static ArrayList<MedicalCondition> stringArrayToMedicalCondition(ArrayList<String> conditions) {
        ArrayList<MedicalCondition> newConditions = new ArrayList<>();
        
        for (int i = 0; i < conditions.size(); i++) {
            MedicalCondition condition = MedicalCondition.valueOf(conditions.get(i).replace(" ", "_"));
            newConditions.add(condition);
        }
        
        return newConditions;
    }
    
    public static String[] medicalConditionArrayToString(ArrayList<MedicalCondition> conditions) {
        String[] array;
        
        if (conditions.isEmpty()) {
            array = new String[]{"None"};
        } else {
            array = new String[conditions.size()];

            for (int i = 0; i < conditions.size(); i++) {
                String condition = conditions.get(i).toString().replace("_", " ");
                array[i] = condition;
            }
        }
        
        return array;
    }
    
    @Override
    public double calculatePremium() {
        double premium = this.getCoverage()/4;
        
        if (this.isSmoker()) {
            premium *= 1.5;
        }
        
        if (this.isGym()) {
            premium -= 500;
        }
        
        switch(this.getHobbyRisk()) {
            case High:
                premium *= 1.5;
                break;
            case Medium:
                premium *= 1.3;
                break;
        }
        
        switch(this.getOccupationRisk()) {
            case High:
                premium *= 1.5;
                break;
            case Medium:
                premium *= 1.3;
                break;
        }
        
        for (MedicalCondition condition : this.getMedicalHistory()) {
            switch (condition) {
                case Heart_Disease:
                    premium *= 2;
                    break;
                case High_Blood_Pressure:
                case Diabetes:
                case Sleep_Apnea:
                case Severe_Allergies:
                    premium *= 1.5;
                    break;
                case High_Cholesterol:
                case Asthma:
                case Obesity:
                case Other_Serious_Health_Disorder:
                    premium *= 1.3;
                    break;
                case Migraines:
                case Irritable_Bowel_Syndrome:
                case Osteoporosis:
                case Mental_Health_Disorder:
                    premium *= 1.1;
                    break;
            }
            
            return premium;
        }
        
        //Base premium rate
        if (premium < 1000) {
            premium = 1000;
        }
        
        return premium;
    }

    @Override
    protected int createId() {
        return Database.getNextId("LIFEPOLICY");
    }
    
    @Override
    public String[] getStringArray() {
        
        String[] array = new String[4+this.medicalHistory.size()];

        array[0] = this.hobbyRisk.toString();
        array[1] = this.getHobbyRisk().toString();
        array[2] = String.valueOf(this.gym).toUpperCase();
        array[3] = String.valueOf(this.smoker).toUpperCase();

        String[] medicalConditions = LifePolicy.medicalConditionArrayToString(this.medicalHistory);
        for (int i = 4; i < array.length; i++) {
            array[i] = medicalConditions[i-4];
        }

        return array;
    }
}
