package InsuranceSystem;

public class CarModel {
    
    private static final int CURRENTYEAR = 2023;
    private final String name;
    private final int risk;
    private double costNew;
    
    public CarModel(String name, int risk, double costNew) {
        this.name = name;
        this.risk = risk;
        this.costNew = costNew;
    }
    
    public CarModel(String name, int risk) {
        this.name = name;
        this.risk = risk;
        this.costNew = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getRisk() {
        return this.risk;
    }
    
    public double getCostNew() {
        return this.costNew;
    }

    public void setCostNew(double costNew) {
        this.costNew = costNew;
    }
}
