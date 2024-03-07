import java.time.LocalDate;

public class Customer {
    private int customerID;
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private int phoneNumber;
    private String email;
    private int licenseNumber;
    private LocalDate licenseIssueDate;

    public Customer(String name, String address, int zipCode, int phoneNumber, String email, int licenseNumber, LocalDate licenseIssueDate) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseIssueDate = licenseIssueDate;
    }

    public Customer(int customerID, String name, String address, int zipCode, int phoneNumber, String email, int licenseNumber, LocalDate licenseIssueDate) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseIssueDate = licenseIssueDate;
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

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setLicenseIssueDate(LocalDate licenseIssueDate) {
        this.licenseIssueDate = licenseIssueDate;
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

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public LocalDate getLicenseIssueDate() {
        return licenseIssueDate;
    }
}
