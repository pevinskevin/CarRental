import java.time.LocalDate;

public class Customer {
    private int customerID;
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private int phoneNumber;
    private String email;
    private int driversLicenseNumber;
    private LocalDate driversLicenseIssueDate;

    public Customer(String name, String address, int zipCode, int phoneNumber, String email, int driversLicenseNumber, LocalDate driversLicenseIssueDate) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.driversLicenseNumber = driversLicenseNumber;
        this.driversLicenseIssueDate = driversLicenseIssueDate;
    }

    public Customer(int customerID, String name, String address, int zipCode, int phoneNumber, String email, int driversLicenseNumber, LocalDate driversLicenseIssueDate) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.driversLicenseNumber = driversLicenseNumber;
        this.driversLicenseIssueDate = driversLicenseIssueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDriversLicenseNumber(int driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public void setDriversLicenseIssueDate(LocalDate driversLicenseIssueDate) {
        this.driversLicenseIssueDate = driversLicenseIssueDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public LocalDate getDriversLicenseIssueDate() {
        return driversLicenseIssueDate;
    }
}
