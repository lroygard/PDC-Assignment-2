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
    
    public static double calculatePremium(double coverage, int yearBuilt, int levels, int squareMeters, int noBuildings, String wallMaterial, String roofMaterial, String quality) {
        HomePolicy dummyHP = new HomePolicy(-1, -1, -1, coverage, -1, "MONTHLY", "", yearBuilt, levels, squareMeters, noBuildings, wallMaterial, roofMaterial, quality);
        return dummyHP.calculatePremium();
    }
    
    @Override
    public double calculatePremium() {
        int age = SystemPage.CURRENTYEAR - this.getYearBuilt();
        
        double premium = this.getCoverage()/200;
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

        switch(roofMaterial) {
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
        premium *= ((((double) this.getSquareMeters())/100)+1);
        premium *= (((double) this.getLevels())/10)+1;
        premium += age;
        premium *= 10;
        
        //Base premium rate
        if (premium < 1000) {
            premium = 1000;
        }
        
        return premium;
    }

    @Override
    protected int createId() {
        return Database.getNextId("HOMEPOLICY");
    }
    
    public static String checkAddress(String address) {
        int noSpaces = 0;
        
        if(address.charAt(0) > '0' && address.charAt(0) < '9') {
            for (int i = 0; i < address.length(); i++) {
                if(address.charAt(i) == ' ') {
                    noSpaces++;
                }
            }
        } 
        
        if(noSpaces >= 2) {
            return address;
        } 
        else {
            return null;
        }
    }
    
}
