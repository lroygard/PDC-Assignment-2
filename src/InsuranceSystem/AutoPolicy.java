package InsuranceSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class AutoPolicy extends Policy {

    public static final HashMap<CarBrand, ArrayList<CarModel>> CARMODELS = CreateCarModels();
    private CarBrand make;
    private CarModel model;
    private int year;
    private LicenseType currentLicense;
    private boolean accidentHistory;
    private boolean commercialUse;

    public AutoPolicy() {}

    public AutoPolicy(int policyId, int customerId, double assetTotal, double coverage, 
            double yearlyPremium, PaymentFrequency frequency, CarBrand make, CarModel model, 
            int year, LicenseType currentLicense, boolean accidentHistory, boolean commercialUse) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.make = make;
        this.model = model;
        this.year = year;
        this.currentLicense = currentLicense;
        this.accidentHistory = accidentHistory;
        this.commercialUse = commercialUse;
    }
    
    public AutoPolicy(int policyId, int customerId, double assetTotal, double coverage, 
            double yearlyPremium, String frequency, String make, String model, 
            int year, String currentLicense, boolean accidentHistory, boolean commercialUse) {
        super(policyId, customerId, assetTotal, coverage, yearlyPremium, frequency);
        this.make = CarBrand.valueOf(make);
        this.model = stringToCarModel(model, this.make);
        this.year = year;
        this.currentLicense = LicenseType.valueOf(currentLicense);
        this.accidentHistory = accidentHistory;
        this.commercialUse = commercialUse;
    }
    
    public CarModel stringToCarModel(String modelName, CarBrand make) {
        ArrayList<CarModel> carModels = CARMODELS.get(make);
        for (CarModel model : carModels) {
            if (model.getName().equalsIgnoreCase(modelName)) {
                return model;
            }
        }
        return null; 
    }

    public CarBrand getMake() {
        return make;
    }

    public CarModel getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public LicenseType getCurrentLicense() {
        return currentLicense;
    }

    public boolean hasAccidentHistory() {
        return accidentHistory;
    }

    public boolean isCommercialUse() {
        return commercialUse;
    }

    public void setMake(CarBrand make) {
        this.make = make;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCurrentLicense(LicenseType currentLicense) {
        this.currentLicense = currentLicense;
    }

    public void setAccidentHistory(boolean accidentHistory) {
        this.accidentHistory = accidentHistory;
    }

    public void setCommercialUse(boolean commercialUse) {
        this.commercialUse = commercialUse;
    }
    
    public static enum LicenseType {
        Learners,
        Restricted,
        Full,
    }
    
    public static enum CarBrand {
        Audi,
        Bmw,
        Ford,
        Holden,
        Honda,
        Hyundai,
        Jeep,
        Kia,
        Nissan,
        Mazda,
        Mercedes,
        Mitsubishi,
        Peugoet,
        Porsche,
        Subaru,
        Suzuki,
        Toyota,
        Volkswagen,
        Volvo,
        Other
    }

    @Override
    public double calculatePremium() {
        int age = Policy.CURRENTYEAR - this.getYear();
        double premium = this.getCoverage()/4;
        
        premium *= ((this.getModel().getRisk()/10)+1);

        if (age <= 5) {
            premium *= 1.05;
        } else if (age <= 10) {
            premium *= 1.1;
        } else {
            premium *= 1.15;
        }

        if (this.getCurrentLicense() == LicenseType.Learners) {
            premium *= 1.1;
        } else if (this.getCurrentLicense() == LicenseType.Restricted) {
            premium *= 1.05;
        }

        if (this.hasAccidentHistory()) {
            premium *= 1.2;
        }

        if (this.isCommercialUse()) {
            premium *= 1.1;
        }

        //Base premium rate
        if (premium < 1000) {
            premium = 1000;
        }
        
        return premium;
    }
    
    public static double calculatePremium(double coverage, String frequency, String make, String model, int year, String currentLicense, boolean accidentHistory, boolean commercialUse) {
        AutoPolicy dummyAP = new AutoPolicy(-1, -1, -1, coverage, -1, frequency, make, model, year, currentLicense, accidentHistory, commercialUse);
        return dummyAP.calculatePremium();
    }


    @Override
    protected int createId() {
        return Database.getNextId("AUTOPOLICY");
    }

    
    public static HashMap<CarBrand, ArrayList<CarModel>> CreateCarModels() {
        HashMap<CarBrand, ArrayList<CarModel>> newCarModels = new HashMap<>();
        
        ArrayList<CarModel> toyota = new ArrayList<>();
        toyota.add(new CarModel("Corolla", 4, 25000));
        toyota.add(new CarModel("Camry", 2, 30000));
        toyota.add(new CarModel("Hilux", 3, 40000));
        toyota.add(new CarModel("RAV4", 2, 35000));
        toyota.add(new CarModel("Other", 3, 20000));
        newCarModels.put(CarBrand.Toyota, toyota);
        
        ArrayList<CarModel> ford = new ArrayList<>();
        ford.add(new CarModel("Focus", 6, 22000));
        ford.add(new CarModel("Fiesta", 4, 18000));
        ford.add(new CarModel("Ranger", 8, 45000));
        ford.add(new CarModel("Escape", 3, 28000));
        ford.add(new CarModel("Other", 4, 20000));
        newCarModels.put(CarBrand.Ford, ford);
        
         ArrayList<CarModel> holden = new ArrayList<>();
        holden.add(new CarModel("Commodore", 6, 28000));
        holden.add(new CarModel("Astra", 4, 22000));
        holden.add(new CarModel("Colorado", 8, 40000));
        holden.add(new CarModel("Trax", 3, 25000));
        holden.add(new CarModel("Other", 5, 18000));
        newCarModels.put(CarBrand.Holden, holden);

        ArrayList<CarModel> mazda = new ArrayList<>();
        mazda.add(new CarModel("Mazda3", 5, 25000));
        mazda.add(new CarModel("CX-5", 2, 35000));
        mazda.add(new CarModel("Mazda2", 3, 20000));
        mazda.add(new CarModel("BT-50", 8, 40000));
        mazda.add(new CarModel("Other", 3, 30000));
        newCarModels.put(CarBrand.Mazda, mazda);

        ArrayList<CarModel> mitsubishi = new ArrayList<>();
        mitsubishi.add(new CarModel("ASX", 3, 28000));
        mitsubishi.add(new CarModel("Outlander", 4, 35000));
        mitsubishi.add(new CarModel("Triton", 9, 45000));
        mitsubishi.add(new CarModel("Pajero Sport", 6, 55000));
        mitsubishi.add(new CarModel("Other", 4, 30000));
        newCarModels.put(CarBrand.Mitsubishi, mitsubishi);

        ArrayList<CarModel> nissan = new ArrayList<>();
        nissan.add(new CarModel("Navara", 9, 50000));
        nissan.add(new CarModel("Qashqai", 2, 30000));
        nissan.add(new CarModel("X-Trail", 3, 35000));
        nissan.add(new CarModel("Leaf", 4, 40000));
        nissan.add(new CarModel("Other", 3, 25000));
        newCarModels.put(CarBrand.Nissan, nissan);

        ArrayList<CarModel> subaru = new ArrayList<>();
        subaru.add(new CarModel("Impreza", 4, 22000));
        subaru.add(new CarModel("Outback", 3, 35000));
        subaru.add(new CarModel("Forester", 3, 30000));
        subaru.add(new CarModel("XV", 4, 28000));
        subaru.add(new CarModel("Other", 3, 25000));
        newCarModels.put(CarBrand.Subaru, subaru);

        ArrayList<CarModel> hyundai = new ArrayList<>();
        hyundai.add(new CarModel("i30", 5, 25000));
        hyundai.add(new CarModel("Tucson", 3, 35000));
        hyundai.add(new CarModel("Kona", 4, 30000));
        hyundai.add(new CarModel("Santa Fe", 6, 45000));
        hyundai.add(new CarModel("Other", 3, 20000));
        newCarModels.put(CarBrand.Hyundai, hyundai);

        ArrayList<CarModel> kia = new ArrayList<>();
        kia.add(new CarModel("Sportage", 3, 32000));
        kia.add(new CarModel("Rio", 4, 20000));
        kia.add(new CarModel("Seltos", 5, 28000));
        kia.add(new CarModel("Cerato", 3, 22000));
        kia.add(new CarModel("Other", 3, 25000));
        newCarModels.put(CarBrand.Kia, kia);
        
        ArrayList<CarModel> volkswagen = new ArrayList<>();
        volkswagen.add(new CarModel("Golf", 5, 25000));
        volkswagen.add(new CarModel("Polo", 4, 18000));
        volkswagen.add(new CarModel("Tiguan", 3, 35000));
        volkswagen.add(new CarModel("Amarok", 8, 45000));
        volkswagen.add(new CarModel("Other", 4, 20000));
        newCarModels.put(CarBrand.Volkswagen, volkswagen);

        ArrayList<CarModel> suzuki = new ArrayList<>();
        suzuki.add(new CarModel("Swift", 3, 15000));
        suzuki.add(new CarModel("Vitara", 4, 22000));
        suzuki.add(new CarModel("S-Cross", 4, 25000));
        suzuki.add(new CarModel("Jimny", 7, 30000));
        suzuki.add(new CarModel("Other", 3, 18000));
        newCarModels.put(CarBrand.Suzuki, suzuki);

        ArrayList<CarModel> bmw = new ArrayList<>();
        bmw.add(new CarModel("3 Series", 6, 45000));
        bmw.add(new CarModel("X5", 8, 60000));
        bmw.add(new CarModel("1 Series", 4, 30000));
        bmw.add(new CarModel("X3", 5, 40000));
        bmw.add(new CarModel("Other", 4, 35000));
        newCarModels.put(CarBrand.Bmw, bmw);

        ArrayList<CarModel> mercedes = new ArrayList<>();
        mercedes.add(new CarModel("C-Class", 5, 45000));
        mercedes.add(new CarModel("E-Class", 7, 55000));
        mercedes.add(new CarModel("GLC", 4, 50000));
        mercedes.add(new CarModel("A-Class", 3, 30000));
        mercedes.add(new CarModel("Other", 4, 35000));
        newCarModels.put(CarBrand.Mercedes, mercedes);
        
        ArrayList<CarModel> audi = new ArrayList<>();
        audi.add(new CarModel("A3", 5, 30000));
        audi.add(new CarModel("Q5", 6, 50000));
        audi.add(new CarModel("A4", 5, 40000));
        audi.add(new CarModel("Q3", 4, 35000));
        audi.add(new CarModel("Other", 4, 40000));
        newCarModels.put(CarBrand.Audi, audi);
        
        ArrayList<CarModel> honda = new ArrayList<>();
        honda.add(new CarModel("Civic", 4, 20000));
        honda.add(new CarModel("HR-V", 3, 50000));
        honda.add(new CarModel("Accord", 4, 40000));
        honda.add(new CarModel("CR-V", 2, 35000));
        honda.add(new CarModel("Other", 3, 45000));
        newCarModels.put(CarBrand.Honda, honda);
        
        ArrayList<CarModel> peugeot = new ArrayList<>();
        peugeot.add(new CarModel("208", 3, 22000));
        peugeot.add(new CarModel("3008", 5, 40000));
        peugeot.add(new CarModel("508", 6, 50000));
        peugeot.add(new CarModel("2008", 4, 30000));
        peugeot.add(new CarModel("Other", 4, 35000));
        newCarModels.put(CarBrand.Peugoet, peugeot);
        
        ArrayList<CarModel> volvo = new ArrayList<>();
        volvo.add(new CarModel("XC90", 8, 70000));
        volvo.add(new CarModel("XC60", 5, 50000));
        volvo.add(new CarModel("XC40", 4, 40000));
        volvo.add(new CarModel("S60", 4, 45000));
        volvo.add(new CarModel("Other", 4, 55000));
        newCarModels.put(CarBrand.Volvo, volvo);

        ArrayList<CarModel> jeep = new ArrayList<>();
        jeep.add(new CarModel("Wrangler", 9, 50000));
        jeep.add(new CarModel("Grand Cherokee", 8, 60000));
        jeep.add(new CarModel("Compass", 4, 35000));
        jeep.add(new CarModel("Renegade", 5, 30000));
        jeep.add(new CarModel("Other", 5, 30000));
        newCarModels.put(CarBrand.Jeep, jeep);
        
        ArrayList<CarModel> porsche = new ArrayList<>();
        porsche.add(new CarModel("911", 10, 120000));
        porsche.add(new CarModel("Cayenne", 9, 90000));
        porsche.add(new CarModel("Panamera", 8, 110000));
        porsche.add(new CarModel("Macan", 7, 80000));
        porsche.add(new CarModel("Other", 7, 75000));
        newCarModels.put(CarBrand.Porsche, porsche);

        ArrayList<CarModel> other = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            other.add(new CarModel("Risk Factor: "+Integer.toString(i), i));
        }
        newCarModels.put(CarBrand.Other, other);
        
        return newCarModels;
    }
    
}
