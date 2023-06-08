package InsuranceSystem;

public class CarModel {

    private static final int CURRENTYEAR = 2023;
    private final String name;
    private final int risk;
    private double costNew;

    /**
     * Constructor
     *
     * @param name the name of the model
     * @param risk the risk factor of the model
     * @param costNew the cost of the model to buy new
     */
    public CarModel(String name, int risk, double costNew) {
        this.name = name;
        this.risk = risk;
        this.costNew = costNew;
    }

    /**
     * Constructor
     *
     * @param name the name of the model
     * @param risk the risk factor of the model
     */
    public CarModel(String name, int risk) {
        this.name = name;
        this.risk = risk;
        this.costNew = 20000;
    }

    /**
     * Gets the name of the model
     *
     * @return the name of the mode;
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the risk associated with the model
     *
     * @return the risk of the model
     */
    public int getRisk() {
        return this.risk;
    }

    /**
     * Gets the cost to buy the model new
     *
     * @return the cost to buy the model new
     */
    public double getCostNew() {
        return this.costNew;
    }
}
