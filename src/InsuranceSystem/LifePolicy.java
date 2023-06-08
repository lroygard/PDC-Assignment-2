package InsuranceSystem;

import java.util.ArrayList;

public class LifePolicy extends Policy {

    private ArrayList<MedicalCondition> medicalHistory;
    private Risk hobbyRisk;
    private Risk occupationRisk;
    private boolean gym;
    private boolean smoker;

    /**
     * Constructor for the Life Class
     *
     * @param policyId The ID of the policy.
     * @param customerId The ID of the customer associated with the policy
     * @param assetTotal The total value of the insured assets
     * @param coverage The coverage amount of the policy
     * @param yearlyPremium The yearly premium amount of the policy
     * @param frequency The payment frequency for the policy
     * @param medicalHistory The medical history of the policy holder
     * @param hobbyRisk The hobby risk of the policyholder
     * @param occupationRisk The occupation risk of the policy holder
     * @param gym Whether the policy holder goes to the gym or not
     * @param smoker Whether the policy holder smokes or not
     */
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

    /**
     * Constructor for the Life Class
     *
     * @param policyId The ID of the policy.
     * @param customerId The ID of the customer associated with the policy
     * @param assetTotal The total value of the insured assets
     * @param coverage The coverage amount of the policy
     * @param yearlyPremium The yearly premium amount of the policy
     * @param frequency The payment frequency for the policy
     * @param medicalHistory The medical history of the policy holder
     * @param hobbyRisk The hobby risk of the policyholder
     * @param occupationRisk The occupation risk of the policy holder
     * @param gym Whether the policy holder goes to the gym or not
     * @param smoker Whether the policy holder smokes or not
     */
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

    /**
     * Returns the medical condition array
     *
     * @return the medical condition array
     */
    public ArrayList<MedicalCondition> getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Returns the hobby risk
     *
     * @return the hobby risk
     */
    public Risk getHobbyRisk() {
        return hobbyRisk;
    }

    /**
     * Returns the occupation risk
     *
     * @return the occupation risk
     */
    public Risk getOccupationRisk() {
        return occupationRisk;
    }

    /**
     * Returns whether the policy holder goes to the gym
     *
     * @return whether the policy holder goes to the gym
     */
    public boolean isGym() {
        return gym;
    }

    /**
     * Returns whether the policy holder smokes
     *
     * @return whether the policy holder smokes
     */
    public boolean isSmoker() {
        return smoker;
    }

    /**
     * Enum representing risk
     */
    public static enum Risk {
        High,
        Medium,
        Low
    }

    /**
     * Enum representing medical conditions
     */
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

    /**
     * Calculates the premium of an incomplete home policy
     *
     * @param coverage The coverage amount of the policy.
     * @param isSmoker Whether the policy holder smokes
     * @param isGym Whether the policy holder goes to the gym
     * @param hobbyRisk The hobby risk
     * @param occupationRisk The occupation risk
     * @param medicalConditions The medical conditions
     * @return the calculated premium
     */
    public static double calculatePremium(double coverage, boolean isSmoker, boolean isGym,
            String hobbyRisk, String occupationRisk, ArrayList<String> medicalConditions) {

        LifePolicy dummyLife = new LifePolicy(-1, -1, -1, coverage, -1, "MONTHLY",
                medicalConditions, hobbyRisk, occupationRisk, isGym, isSmoker);

        return dummyLife.calculatePremium();
    }

    /**
     * Turns an string arraylist into a medical condition arraylist
     *
     * @param conditions the string arraylist
     * @return the medical condition arraylist
     */
    public static ArrayList<MedicalCondition> stringArrayToMedicalCondition(ArrayList<String> conditions) {
        ArrayList<MedicalCondition> newConditions = new ArrayList<>();

        for (int i = 0; i < conditions.size(); i++) {
            MedicalCondition condition = MedicalCondition.valueOf(conditions.get(i).replace(" ", "_"));
            newConditions.add(condition);
        }

        return newConditions;
    }

    /**
     * Turns a medicalCondition arraylist into a string arraylist
     *
     * @param conditions the medical condition arraylist
     * @return the string arraylist
     */
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

    /**
     * Calculates the premium of the policy
     *
     * @return the calculated premium
     */
    @Override
    public double calculatePremium() {
        double premium = this.getCoverage() / 4;

        if (this.isSmoker()) {
            premium *= 1.5;
        }

        if (this.isGym()) {
            premium -= 500;
        }

        switch (this.getHobbyRisk()) {
            case High:
                premium *= 1.5;
                break;
            case Medium:
                premium *= 1.3;
                break;
        }

        switch (this.getOccupationRisk()) {
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

    /**
     * returns a new id for life policies
     *
     * @return the new id
     */
    @Override
    protected int createId() {
        return Database.getNextId("LIFEPOLICY");
    }

    /**
     * Gets a string array of the information in the policy
     *
     * @return A string array of information
     */
    @Override
    public String[] getStringArray() {

        String[] array = new String[4 + this.medicalHistory.size()];

        array[0] = this.hobbyRisk.toString();
        array[1] = this.getHobbyRisk().toString();
        array[2] = String.valueOf(this.gym).toUpperCase();
        array[3] = String.valueOf(this.smoker).toUpperCase();

        String[] medicalConditions = LifePolicy.medicalConditionArrayToString(this.medicalHistory);
        for (int i = 4; i < array.length; i++) {
            array[i] = medicalConditions[i - 4];
        }

        return array;
    }
}
