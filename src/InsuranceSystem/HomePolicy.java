package InsuranceSystem;

public class HomePolicy extends Policy {

    private String address;
    private int yearBuilt;
    private int levels;
    private int squareMeters;
    private int noBuildings;
    private WallMaterial wallMaterial;
    private RoofMaterial roofMaterial;
    private Quality constructionQuality;

    /**
     * Default Constructor
     */
    public HomePolicy() {
    }

    /**
     * Constructor for the Home class.
     *
     * @param policyId The ID of the policy.
     * @param customerId The ID of the customer associated with the policy
     * @param assetTotal The total value of the insured assets
     * @param coverage The coverage amount of the policy
     * @param yearlyPremium The yearly premium amount of the policy
     * @param frequency The payment frequency for the policy
     * @param address The address of the home insured by the policy
     * @param yearBuilt The year the house was built
     * @param levels The number of levels of the house
     * @param squareMeters The number of squaremeters of the house
     * @param noBuildings The number of buildings insured in the policy
     * @param wallMaterial The material the walls are made of
     * @param roofMaterial The material the roof(s) are made of
     * @param constructionQuality The construction quality of the house
     */
    public HomePolicy(int policyId, int customerId, double assetTotal, double coverage,
            double yearlyPremium, PaymentFrequency frequency, String address, int yearBuilt,
            int levels, int squareMeters, int noBuildings, WallMaterial wallMaterial,
            RoofMaterial roofMaterial, Quality constructionQuality) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.address = address;
        this.yearBuilt = yearBuilt;
        this.levels = levels;
        this.squareMeters = squareMeters;
        this.noBuildings = noBuildings;
        this.wallMaterial = wallMaterial;
        this.roofMaterial = roofMaterial;
        this.constructionQuality = constructionQuality;
    }

    /**
     * Constructor for the Home class.
     *
     * @param policyId The ID of the policy.
     * @param customerId The ID of the customer associated with the policy
     * @param assetTotal The total value of the insured assets
     * @param coverage The coverage amount of the policy
     * @param yearlyPremium The yearly premium amount of the policy
     * @param frequency The payment frequency for the policy
     * @param address The address of the home insured by the policy
     * @param yearBuilt The year the house was built
     * @param levels The number of levels of the house
     * @param squareMeters The number of squaremeters of the house
     * @param noBuildings The number of buildings insured in the policy
     * @param wallMaterial The material the walls are made of
     * @param roofMaterial The material the roof(s) are made of
     * @param constructionQuality The construction quality of the house
     */
    public HomePolicy(int policyId, int customerId, double assetTotal, double coverage,
            double yearlyPremium, String frequency, String address, int yearBuilt,
            int levels, int squareMeters, int noBuildings, String wallMaterial,
            String roofMaterial, String constructionQuality) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.address = address;
        this.yearBuilt = yearBuilt;
        this.levels = levels;
        this.squareMeters = squareMeters;
        this.noBuildings = noBuildings;
        this.wallMaterial = WallMaterial.valueOf(wallMaterial);
        this.roofMaterial = RoofMaterial.valueOf(roofMaterial);
        this.constructionQuality = Quality.valueOf(constructionQuality);
    }

    /**
     * Returns the address of the house
     *
     * @return the adress of the house
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the year built of the house
     *
     * @return the year built of the house
     */
    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Returns the number of levels in the house
     *
     * @return the number of levels in the house
     */
    public int getLevels() {
        return levels;
    }

    /**
     * Returns the number of square meters of the house
     *
     * @return the number of square meters of the house
     */
    public int getSquareMeters() {
        return squareMeters;
    }

    /**
     * Returns the number of buildings on the property
     *
     * @return the number of buildings on the property
     */
    public int getNoBuildings() {
        return noBuildings;
    }

    /**
     * Returns the material of the walls
     *
     * @return the material of the walls
     */
    public WallMaterial getWallMaterial() {
        return wallMaterial;
    }

    /**
     * Returns the material of the roof(s)
     *
     * @return the material of the roof(s)
     */
    public RoofMaterial getRoofMaterial() {
        return roofMaterial;
    }

    /**
     * Returns the construction quality
     *
     * @return the construction quality
     */
    public Quality getConstructionQuality() {
        return constructionQuality;
    }

    /**
     * Gets a string array of the information in the policy
     *
     * @return A string array of information
     */
    @Override
    public String[] getStringArray() {
        String[] array = new String[8];

        //Add values into array
        array[0] = this.address;
        array[1] = String.valueOf(this.yearBuilt);
        array[2] = String.valueOf(this.squareMeters);
        array[3] = String.valueOf(this.noBuildings);
        array[4] = String.valueOf(this.squareMeters);
        array[5] = this.wallMaterial.toString().replace("_", " ");
        array[6] = this.roofMaterial.toString().replace("_", " ");
        array[7] = this.constructionQuality.toString();

        return array;
    }

    /**
     * Enum representing wall materials
     */
    public enum WallMaterial {
        Brick_Veneer,
        Double_Brick,
        Mud_Brick,
        Rockcote_EPS,
        Sheet_Cladding,
        Solid_Brickwork,
        Stonework_Solid,
        Stonework_Vaneer,
        Stucco,
        Weatherboard_Plank_Cladding,
        Other,
    }

    /**
     * Enum representing roof materials
     */
    public enum RoofMaterial {
        Flat_Fibre_Cement,
        Flat_Membrane,
        Flat_Metal_Covering,
        Pitched_Concrete_Tiles,
        Pitched_Fibre_Cement_Covering,
        Pitched_Metal_Covering,
        Pitched_Slate,
        Pitched_Terracotta_Tiles,
        Pitched_Timber_Shingles,
        Other,
    }

    /**
     * Enum representing construction quality
     */
    public enum Quality {
        Prestige,
        High,
        Standard,
    }

    /**
     * Calculates the premium of an incomplete home policy
     *
     * @param coverage The coverage amount of the policy.
     * @param yearBuilt The year the home was built
     * @param levels The levels of the home
     * @param squareMeters The square meters of the property
     * @param noBuildings The number of buildings
     * @param wallMaterial The material of the walls
     * @param roofMaterial The material of the roof
     * @param quality The quality of the build
     * @return the calculated premium
     */
    public static double calculatePremium(double coverage, int yearBuilt, int levels, int squareMeters, int noBuildings, String wallMaterial, String roofMaterial, String quality) {
        HomePolicy dummyHP = new HomePolicy(-1, -1, -1, coverage, -1, "MONTHLY", "", yearBuilt, levels, squareMeters, noBuildings, wallMaterial, roofMaterial, quality);
        return dummyHP.calculatePremium();
    }

    /**
     * Calculates the premium of the policy
     *
     * @return the calculated premium
     */
    @Override
    public double calculatePremium() {
        int age = SystemPage.CURRENTYEAR - this.getYearBuilt();

        double premium = this.getCoverage() / 200;
        switch (this.getWallMaterial()) {
            case Brick_Veneer:
            case Double_Brick:
            case Solid_Brickwork:
            case Stonework_Solid:
            case Stonework_Vaneer:
            case Weatherboard_Plank_Cladding:
                premium *= 1.2;
                break;
            case Mud_Brick:
            case Stucco:
                premium *= 1.5;
                break;
            case Rockcote_EPS:
            case Sheet_Cladding:
                premium *= 1.8;
                break;
            case Other:
                premium *= 2;
                break;
        }

        switch (roofMaterial) {
            case Flat_Fibre_Cement:
                break;
            case Pitched_Concrete_Tiles:
            case Pitched_Fibre_Cement_Covering:
            case Pitched_Metal_Covering:
            case Pitched_Terracotta_Tiles:
            case Pitched_Timber_Shingles:
                premium *= 1.2;
                break;
            case Flat_Membrane:
            case Flat_Metal_Covering:
            case Pitched_Slate:
                premium *= 1.5;
                break;
            case Other:
                premium *= 2;
        }

        switch (this.getConstructionQuality()) {
            case Prestige:
                premium *= 1.5;
                break;
            case High:
                premium *= 1.3;
                break;
            case Standard:
                premium *= 1.1;
                break;
        }

        premium += 1000 * (this.getNoBuildings() - 1);
        premium *= ((((double) this.getSquareMeters()) / 100) + 1);
        premium *= (((double) this.getLevels()) / 10) + 1;
        premium += age;
        premium *= 10;

        //Base premium rate
        if (premium < 1000) {
            premium = 1000;
        }

        return premium;
    }

    /**
     * returns a new id for home policies
     *
     * @return the new id
     */
    @Override
    protected int createId() {
        return Database.getNextId("HOMEPOLICY");
    }

    /**
     * Checks the validity of an address
     *
     * @param address the address to check
     * @return the address if it is valid or null if it isn't
     */
    public static String checkAddress(String address) {
        int noSpaces = 0;

        if (address.charAt(0) > '0' && address.charAt(0) < '9') {
            for (int i = 0; i < address.length(); i++) {
                if (address.charAt(i) == ' ') {
                    noSpaces++;
                }
            }
        }

        if (noSpaces >= 2) {
            return address;
        } else {
            return null;
        }
    }

}
