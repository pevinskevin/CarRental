import java.util.Scanner;

public class Main {
    private MySqlConnection mySqlConnection;
    private CustomerCRUD customerCrud;
    private CarCRUD carCrud;

    public Main() {
        this.mySqlConnection = new MySqlConnection();
        this.customerCrud = new CustomerCRUD(mySqlConnection);
        this.carCrud = new CarCRUD(mySqlConnection);
    }

    public static void main(String[] args) {
        new Main().run();
    }
    private void run() {
        boolean running = true;

        while (running) {
            MenuChoice menuChoice = showMainMenu();
            switch (menuChoice) {
                case CREATE_CUSTOMER -> customerCrud.createCustomer();
                case READ_CUSTOMERS -> customerCrud.printAllCustomers();
                case UPDATE_CUSTOMER -> customerCrud.updateCustomer();
                case DELETE_CUSTOMER -> customerCrud.deleteCustomer();
                case CREATE_CAR -> carCrud.createCar();
                case READ_CARS -> carCrud.printAllCars();
                case UPDATE_CAR -> carCrud.updateCar();
                case DELETE_CAR -> carCrud.deleteCar();
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nMAIN MENU\n" +
                "1. Create customer\n" +
                "2. Show all customers\n" +
                "3. Edit customer\n" +
                "4. Delete customer\n" +
                "5. Create Car\n" +
                "6. Show all cars\n" +
                "7. Update car\n" +
                "8. Delete car\n" +
                "Q. Quit\n");

        char choice = in.nextLine().toLowerCase().charAt(0);
        MenuChoice menuChoice = null;
        switch (choice) {
            case '1' -> menuChoice = MenuChoice.CREATE_CUSTOMER;
            case '2' -> menuChoice = MenuChoice.READ_CUSTOMERS;
            case '3' -> menuChoice = MenuChoice.UPDATE_CUSTOMER;
            case '4' -> menuChoice = MenuChoice.DELETE_CUSTOMER;
            case '5' -> menuChoice = MenuChoice.CREATE_CAR;
            case '6' -> menuChoice = MenuChoice.READ_CARS;
            case '7' -> menuChoice = MenuChoice.UPDATE_CAR;
            case '8' -> menuChoice = MenuChoice.DELETE_CAR;
            case 'q' -> menuChoice = MenuChoice.QUIT;
        }
        return menuChoice;
    }
}

