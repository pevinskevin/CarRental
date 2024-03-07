import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarCRUD {
    private MySqlConnection mySqlConnection;

    public CarCRUD(MySqlConnection mySql) {
        //Instantiates MySqlConnection which allow SQL-queries to be run from class.
        this.mySqlConnection = mySql;
    }
    //Create Car
    public void createCar() {
        //Instantiates Car which is used as argument for createCarInDatabase method.
        Scanner in = new Scanner(System.in);
        System.out.println("\nCREATE Car");
        System.out.println("Enter Car Category: 1. Luxury - 2. Family - 3. Sport:");
        int carCategory = in.nextInt();
        in.nextLine();
        System.out.print("Brand: ");
        String brand = in.nextLine();
        System.out.print("Model: ");
        String model = in.nextLine();
        System.out.println("Type of Fuel: ");
        String typeOfFuel = in.nextLine();
        System.out.println("License plate: ");
        String zipCode = in.nextLine();
        System.out.println("Registration year and month (YYYY-MM): ");
        int registration = in.nextInt();
        in.nextLine();
        System.out.println("Mileage: ");
        int mileage = in.nextInt();
        Car car = new Car(carCategory, brand, model, typeOfFuel, zipCode, registration, mileage);
        createCarInDatabase(car);
    }
    private void createCarInDatabase(Car car) {
        //Takes the Instantiation from createCar as parameter and adds the new customer data to MySQL DB.
        try {
            String query = "INSERT INTO car (Brand, Model, TypeOfFuel, License_Plate, Registration_YYYY_MM, Mileage, Car_CategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = mySqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getTypeOfFuel());
            preparedStatement.setString(4, car.getLicensePlate());
            preparedStatement.setInt(5, car.getRegistrationYYYYMM());
            preparedStatement.setInt(6, car.getMileage());
            preparedStatement.setInt(7, car.getCarCategoryID());

            preparedStatement.executeUpdate();

            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
            e.printStackTrace();

        }
    }
    //Read
    public void printAllCars() {
        //Uses the ID-value from mySQLConnection.getHighestCustomerID to print all users in database.
        int highestID = getHighestCarIdFromDb();
        for (int i = 0; i < highestID; i++) {
            printAllCarsFromDb(i + 1);
        }
    }
    private int getHighestCarIdFromDb() {
        //Find highest ID in Customer Column.
        try {
            PreparedStatement ps = mySqlConnection.getConnection().prepareStatement("SELECT MAX(ID) AS MaxID FROM CAR");
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) { // use 'if', not 'while', because MAX() returns only one row
                return resultSet.getInt("MaxID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; // return -1 or throw an exception if no entry found
    }
    private void printAllCarsFromDb(int customerId){
        //Returns car based on ID as int input.
        try {
            PreparedStatement ps = mySqlConnection.getConnection().prepareStatement("SELECT * FROM CAR WHERE ID = ?;");
            ps.setInt(1, customerId);  // Set value for the parameter
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) { // use 'if', not 'while', because MAX() returns only one row
                System.out.println("Car Category: " + resultSet.getInt("Car_CategoryID") + " -- Car ID: " + resultSet.getInt("ID") + " -- Model: " + resultSet.getString("Model") + " -- Brand: " + resultSet.getString("Brand"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Update
    void updateCar() {
        //Inputs the ID of car that needs editing.
        System.out.println("Please input the ID of the user you wish to edit: ");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        Customer customer = updateCarInDb(userInput);
    }
    private Customer updateCarInDb(int customerId){
        //Allows user to edit specific columns.
        int id = customerId;
        System.out.println("What do you want to change?\n" +
                "1. Brand\n" +
                "2. Model\n" +
                "3. Type of Fuel\n" +
                "4. License plate\n" +
                "5. Registration (YYYYMM)\n" +
                "6. Mileage\n" +
                "7. Return to Main Menu\n");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        switch (num) {
            case 1:
                System.out.println("Please insert the brand name: ");
                String string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Brand = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Name updated to: " + string + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;


            case 2:
                System.out.println("Please insert the car model: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Model = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Address updated to: " + string + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;

            case 3:
                System.out.println("Please insert the type of fuel: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET TypeOfFuel = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Zipcode updated to: " + string + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;

            case 4:
                System.out.println("Please insert the license plate: ");
                int number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET License_plate = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("City updated to: " + number + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;

            case 5:
                System.out.println("Please insert the registration (YYYYMM): ");
                number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Registration_YYYY_MM = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Mobile Phone string updated to: " + number + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;

            case 6:
                System.out.println("Please insert the car mileage: ");
                number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Mileage = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Email updated to: " + number + "\n");
                    updateCarInDb(id);
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getStackTrace());
                }
                break;
            case 9:
                break;
        }
        return null;
    }
    //Delete
    //Currently unable to delete car with contracts in DB.
    void deleteCar() {
        //Takes ID as input which is used as int argument for deleteCarFromDb
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the ID of the car you wish to delete: ");
        int customerID = scanner.nextInt();
        System.out.println("Please confirm that you wish to delete %s's data: Y/N");
        char yesNo = scanner.next().charAt(0);
        char lowerCaseChar = Character.toLowerCase(yesNo);
        String charToString = String.valueOf(lowerCaseChar);
        if (charToString.contains("y")) {
            deleteCarFromDb(customerID);
            System.out.println("Returning to Main Menu. \n");
        } else System.out.println("Deletion cancelled");
    }
    private void deleteCarFromDb(int customerId) {
        //Deletes car with ID = parameter.
        try (PreparedStatement deletePS = mySqlConnection.getConnection().prepareStatement("DELETE FROM car WHERE ID = ?")) {
            deletePS.setInt(1, customerId);
            deletePS.executeUpdate();
            System.out.println("\nCar successfully deleted.\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}