import java.time.YearMonth;

public class Car {
    private int carID;
    private int carCategoryID;
    private String brand;
    private String model;
    private String typeOfFuel;
    private String licensePlate;
    private YearMonth registrationDate;
    private int mileage;

    public Car(int carCategoryID, String brand, String model, String typeOfFuel, String licensePlate, YearMonth registrationDate, int mileage) {
        this.carCategoryID = carCategoryID;
        this.brand = brand;
        this.model = model;
        this.typeOfFuel = typeOfFuel;
        this.licensePlate = licensePlate;
        this.registrationDate = registrationDate;
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

    public YearMonth getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistration(YearMonth registrationDate) {
        this.registrationDate = registrationDate;
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


