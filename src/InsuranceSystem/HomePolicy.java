package InsuranceSystem;

public class HomePolicy extends Policy{
    String address;
    int yearBuilt;
    int levels;
    int squareMeters;
    int noBuildings;
    WallMaterial wallMaterial;
    RoofMaterial roofMaterial;
    Quality constructionQuality;
    
    public HomePolicy() {}

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
    
    public String getAddress() {
        return address;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getLevels() {
        return levels;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public int getNoBuildings() {
        return noBuildings;
    }

    public WallMaterial getWallMaterial() {
        return wallMaterial;
    }

    public RoofMaterial getRoofMaterial() {
        return roofMaterial;
    }

    public Quality getConstructionQuality() {
        return constructionQuality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public void setNoBuildings(int noBuildings) {
        this.noBuildings = noBuildings;
    }

    public void setWallMaterial(WallMaterial wallMaterial) {
        this.wallMaterial = wallMaterial;
    }

    public void setRoofMaterial(RoofMaterial roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    public void setConstructionQuality(Quality constructionQuality) {
        this.constructionQuality = constructionQuality;
    }
    
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
    
    public enum Quality {
        Prestige,
        High,
        Standard,
    }
    
    @Override
    public double calculatePremium() {
        //TODO Home calculate premium
        return -1;
    }

    @Override
    protected int createId() {
        return Database.getNextId("HOMEPOLICY");
    }
}
