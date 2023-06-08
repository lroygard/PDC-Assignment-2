package InsuranceSystem;

public abstract class Policy {

    public static final int CURRENTYEAR = 2023;
    private int policyId;
    private final int customerId;
    private double assetTotal;
    private double coverage;
    private double yearlyPremium;
    private PaymentFrequency frequency;

    /**
     * Default constructor
     */
    public Policy() {
        this.customerId = -1;
    }

    /**
     * Main constructor
     *
     * @param customerId the id of the customer associated with this policy
     * @param policyId the id of the policy
     * @param yearlyPremium the premium of the policy
     * @param coverage the coverage of the policy
     * @param assetTotal the total value of assets in the policy
     * @param frequency the frequency the policy is payed
     */
    public Policy(int policyId, int customerId, double assetTotal, double coverage, double yearlyPremium, PaymentFrequency frequency) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.assetTotal = assetTotal;
        this.coverage = coverage;
        this.yearlyPremium = yearlyPremium;
        this.frequency = frequency;
    }

    /**
     * String constructor
     *
     * @param customerId the id of the customer associated with this policy
     * @param policyId the id of the policy
     * @param yearlyPremium the premium of the policy
     * @param coverage the coverage of the policy
     * @param assetTotal the total value of assets in the policy
     * @param frequency the frequency the policy is payed
     */
    public Policy(int policyId, int customerId, double assetTotal, double coverage, double yearlyPremium, String frequency) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.assetTotal = assetTotal;
        this.coverage = coverage;
        this.yearlyPremium = yearlyPremium;
        this.frequency = PaymentFrequency.valueOf(frequency);
    }

    /**
     * Retrieves the policy id of this policy
     *
     * @return
     */
    public int getPolicyId() {
        return policyId;
    }

    /**
     * Retrieves the customer id associated with this policy
     *
     * @return the customer id associated with this policy
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Retrieves the value of the asset of this policy
     *
     * @return the value of the asset associated with this policy
     */
    public double getAssetTotal() {
        return assetTotal;
    }

    /**
     * Retrieves the coverage of this policy
     *
     * @return the coverage associated with this policy
     */
    public double getCoverage() {
        return coverage;
    }

    /**
     * Retrieves the premium of this policy
     *
     * @return the premium of this policy
     */
    public double getYearlyPremium() {
        return yearlyPremium;
    }

    /**
     * Retrieves the payment frequency of this policy
     *
     * @return the payment frequency of this policy
     */
    public PaymentFrequency getFrequency() {
        return frequency;
    }

    /**
     * list of valid payment frequencies
     */
    public enum PaymentFrequency {
        FORTNIGHTLY,
        MONTHLY,
        QUARTERLY,
        YEARLY,
    }

    /**
     * Gets a string array of the information in the policy
     *
     * @return A string array of information
     */
    public String[] getPolicyStringArray() {
        String[] array = new String[6];

        array[0] = (String.valueOf(this.policyId));
        array[1] = (String.valueOf(this.customerId));
        array[2] = ("$" + String.valueOf(this.assetTotal));
        array[3] = ("$" + String.valueOf(this.coverage));
        array[4] = ("$" + String.valueOf(this.yearlyPremium));
        array[5] = (this.frequency.toString());

        return array;
    }

    /**
     * Check the coverage is valid
     *
     * @param coverage the coverage requested
     * @param assetTotal the
     * @return the coverage
     */
    public static double checkCoverage(double coverage, double assetTotal) {
        if (coverage >= 1000 && coverage <= assetTotal) {
            return coverage;
        }

        return -1;
    }

    //Abstract methods
    public abstract double calculatePremium();

    protected abstract int createId();

    public abstract String[] getStringArray();
}
