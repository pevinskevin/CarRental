import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
        String licensePlate = in.nextLine();
        System.out.println("Registration year and month (YYYY-MM): ");
        String dateString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth licenseIssueDate = YearMonth.parse(dateString, formatter);
        System.out.println("Mileage: ");
        int mileage = in.nextInt();
        Car car = new Car(carCategory, brand, model, typeOfFuel, licensePlate, licenseIssueDate, mileage);
        createCarInDatabase(car);
    }
    private void createCarInDatabase(Car car) {
        //Takes the Instantiation from createCar as parameter and adds the new customer data to MySQL DB.
        try {
            String query = "INSERT INTO car (Brand, Model, TypeOfFuel, License_Plate, Registration_Date, Mileage, Car_CategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = mySqlConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getTypeOfFuel());
            preparedStatement.setString(4, car.getLicensePlate());
            //Man kan ikke bruge YearMonth - den konvertes til en string forneden hvor der tilføjes en dag
            //dvs. outputtet bliver til YYYY-MM-01 -- metoden nedenfor sætter altid dagen som 01.
            //Så uanset hvilken måned og dag du indtaster, så vil outputte ti MySQL altid være YYYY-MM-01.
            LocalDate date = car.getRegistrationDate().atDay(1);
            preparedStatement.setDate(5, Date.valueOf(date));
            preparedStatement.setInt(6, car.getMileage());
            preparedStatement.setInt(7, car.getCarCategoryID());

            preparedStatement.executeUpdate();

            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            e.printStackTrace();

        }
    }
    //Read
     void printAllCarsFromDb(){
        //Returns car based on ID as int input.
        try {
            PreparedStatement ps = mySqlConnection.getConnection().prepareStatement("SELECT * FROM CAR;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) { // use 'if', not 'while', because MAX() returns only one row
                System.out.println("Car Category: " + resultSet.getInt("Car_CategoryID") + " -- Car ID: " + resultSet.getInt("ID") + " -- Model: " + resultSet.getString("Model") + " -- Brand: " + resultSet.getString("Brand")  + " -- Registration Date: " + resultSet.getString("Registration_Date")  + " -- License plate: " + resultSet.getString("License_Plate")  + " -- Mileage: " + resultSet.getString("Mileage"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Update
    void updateCar() {
        //Inputs the ID of car that needs editing.
        System.out.println("Please input the ID of the car you wish to edit: ");
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
                    System.out.println("Car brand updated to: " + string + "\n");
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;


            case 2:
                System.out.println("Please insert the car model: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Model = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Car model updated to: " + string + "\n");
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 3:
                System.out.println("Please insert the type of fuel: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET TypeOfFuel = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Type of fuel updated to: " + string + "\n");
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 4:
                System.out.println("Please insert the license plate: ");
                string = scanner.nextLine();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET License_plate = ? WHERE ID = ?;")) {
                    updatePs.setString(1, string);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("License plae updated to: " + string + "\n");
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 5:
                System.out.println("Please insert the registration (YYYY-MM): ");
                String dateString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                YearMonth regDate = YearMonth.parse(dateString, formatter);
                LocalDate date = regDate.atDay(1);
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Registration_Date = ? WHERE ID = ?;")) {
                    updatePs.setDate(1, Date.valueOf(date));
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }
                break;

            case 6:
                System.out.println("Please insert the car mileage: ");
                int number = scanner.nextInt();
                try (PreparedStatement updatePs = mySqlConnection.getConnection().prepareStatement("UPDATE CAR SET Mileage = ? WHERE ID = ?;")) {
                    updatePs.setInt(1, number);
                    updatePs.setInt(2, customerId);
                    updatePs.executeUpdate();
                    System.out.println("Email updated to: " + number + "\n");
                    updateCarInDb(id);//sender tilbage til edit-menuen
                } catch (SQLException e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
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
