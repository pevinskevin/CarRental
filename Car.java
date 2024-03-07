public class Car {
    private int carID;
    private int carCategoryID;
    private String brand;
    private String model;
    private String typeOfFuel;
    private String licensePlate;
    private int registrationYYYYMM;
    private int mileage;

    public Car(int carCategoryID, String brand, String model, String typeOfFuel, String licensePlate, int registrationYYYYMM, int mileage) {
        this.carCategoryID = carCategoryID;
        this.brand = brand;
        this.model = model;
        this.typeOfFuel = typeOfFuel;
        this.licensePlate = licensePlate;
        this.registrationYYYYMM = registrationYYYYMM;
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getRegistrationYYYYMM() {
        return registrationYYYYMM;
    }

    public void setRegistrationYYYYMM(int registrationYYYYMM) {
        this.registrationYYYYMM = registrationYYYYMM;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public int getCarCategoryID() {
        return carCategoryID;
    }
}


