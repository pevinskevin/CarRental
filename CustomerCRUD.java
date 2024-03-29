import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerCRUD {
    private MySqlConnection mySqlConnection;

    public CustomerCRUD(MySqlConnection mySql) {
        //Instantiates MySqlConnection which allow SQL-queries to be run from class.
        this.mySqlConnection = mySql;
    }

    //Create
    public void createCustomer() {
        //Instantiates Customer which is used as argument for createCustomerInDatabase method.
        Scanner in = new Scanner(System.in);
        System.out.println("\nCREATE Customer");
        System.out.print("First name: ");
        String firstName = in.nextLine();
        System.out.print("Last name: ");
        String lastName = in.nextLine();
        String fullname = firstName + " " + lastName;
        System.out.println("Address: ");
        String address = in.nextLine();
        System.out.println("Zip code: ");
        int zipCode = in.nextInt();
        in.nextLine();
        System.out.println("Phone Number: ");
        int phoneNumber = in.nextInt();
        in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.println("Drivers license Number: ");
        int driversLicenseNumber = in.nextInt();
        in.nextLine();
        System.out.print("Drivers license issue date (DD-MM-YYYY): ");
        String dateString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate driverLicenseIssueDate = LocalDate.parse(dateString, formatter);
        Customer customer = new Customer(fullname, address, zipCode, phoneNumber, email, driversLicenseNumber,driverLicenseIssueDate);
        createCustomerInDb(customer);

    }
    private void createCustomerInDb(Customer customer) {
        //Takes the Instantiation from createCustomer as parameter and adds the new customer data to MySQL DB.
        try {
            String query = "INSERT INTO customer (Name, Address, Zipcode, Mobile_phone, Email, License_Number, License_Issue_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = mySqlConnection.getConnection().prepareStatement(query); //!!
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setInt(3, customer.getZipCode());
            preparedStatement.setInt(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getDriversLicenseNumber());
            preparedStatement.setDate(7, Date.valueOf(customer.getDriversLicenseIssueDate()));

            preparedStatement.executeUpdate();

            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION1: " + e.getMessage());
        }
    }
    //Read
    public void printAllCustomersFromDb(){
        //Returns customer based on ID as int input.
        try {
            PreparedStatement ps = mySqlConnection.getConnection().prepareStatement
                    ("Select * From Customer join zipcode_city on customer.zipcode = zipcode_city.zipcode;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) { // Er baseret på: https://youtu.be/KgXq2UBNEhA?si=oti3JOGnJhBqZv3p&t=823
                System.out.println("User ID: " + resultSet.getInt("ID") + " -- Name: " + resultSet.getString("Name")
                        + " -- Address: " + resultSet.getString("Address") + " -- Zipcode: " + resultSet.getInt("Zipcode")
                        + " -- City: " + resultSet.getString("City")
                        + " -- Mobile phone: " + resultSet.getInt("Mobile_phone")+ " -- Email: "
                        + resultSet.getString("Email") + " -- Drivers license Number: "
                        + resultSet.getInt("License_number") + " -- Drivers license Issue Date: "
                        + resultSet.getDate("License_Issue_Date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Update
    public void updateCustomer() {
        //Inputs the ID of user that needs editing.
        System.out.println("Please input the ID of the user you wish to edit: ");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        updateCustomerInDb(userInput);
    }
    private void updateCustomerInDb(int customerId){
        //Allows user to edit specific columns.
        int id = customerId;
        System.out.println("What do you want to change?\n" +
                "1. Name\n" +
                "2. Adress\n" +
                "3. Zipcode\n" +
                "4. Phone number\n" +
                "5. E-mail\n" +
                "6. License number\n" +
                "7. License issue date\n" +
                "8. Return to main menu");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        switch (num) {
            case 1:
                System.out.println("Please insert the new name: ");
                String string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET Name = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Name updated to: " + string + "\n");
                    updateCustomerInDb(id); //sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                } break;


            case 2:
                System.out.println("Please insert the new address: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET Address = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Address updated to: " + string + "\n");
                    updateCustomerInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 3:
                System.out.println("Please insert the new Zipcode: ");
                int number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET Zipcode = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Zipcode updated to: " + number + "\n");
                    updateCustomerInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 4:
                System.out.println("Please insert the new phone number: ");
                number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET Mobile_phone = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Mobile Phone number updated to: " + number + "\n");
                    updateCustomerInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 5:
                System.out.println("Please insert the new email: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET Email = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Email updated to: " + string + "\n");
                    updateCustomerInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 6:
                System.out.println("Please insert the new license number: ");
                number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement(
                        "UPDATE CUSTOMER SET License_number = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("License number updated to: " + number + "\n");
                    updateCustomerInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

                case 7:
                    System.out.println("Please insert the new license issue date (DD-MM-YYYY): ");
                    String dateString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate driverLicenseIssueDate = LocalDate.parse(dateString, formatter);
                    try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CUSTOMER SET License_Issue_Date = ? WHERE ID = ?;")) {
                        updatePs.setDate(1, Date.valueOf(driverLicenseIssueDate));
                        updatePs.setInt(2, id);
                        updatePs.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("EXCEPTION: " + e.getMessage());
                    }
                break;
            case 8:
                break;

        }
    }

    //Delete
    //Currently unable to delete customers with contracts in DB.
    public void deleteCustomer() {
        //Takes ID as input which is used as int argument for deleteCustomerFromDb
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the ID of the customer you wish to delete: ");
        int customerID = scanner.nextInt();
        System.out.println("Please confirm that you wish to delete %s's data: Y/N");
        char yesNo = scanner.next().charAt(0);
        char lowerCaseChar = Character.toLowerCase(yesNo);
        String charToString = String.valueOf(lowerCaseChar);
        if (charToString.contains("y")) {
            deleteCustomerFromDb(customerID);
            System.out.println("Returning to Main Menu. \n");
        } else System.out.println("Deletion cancelled");
    }
    private void deleteCustomerFromDb(int customerId) {
        //Deletes customer (by replacing all of its data with nonsense) with ID = parameter.
        try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("Delete from customer where id = ?;")) {
            updatePs.setInt(1, customerId);
            updatePs.executeUpdate();
            System.out.println("User successfully deleted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

