package main;

import model.*;
import service.RentalAgency;
import exception.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        RentalAgency agency = new RentalAgency();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== VEHICLE RENTAL SYSTEM =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Add Customer");
            System.out.println("3. View Vehicles");
            System.out.println("4. Rent Vehicle");
            System.out.println("5. Return Vehicle");
            System.out.println("6. View Rentals");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            System.out.println("7. Search Vehicle");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    addVehicleUI(scanner, agency);
                    break;

                case 2:
                    addCustomerUI(scanner, agency);
                    break;

                case 3:
                    agency.displayAllVehicles();
                    break;

                case 4:
                    rentVehicleUI(scanner, agency);
                    break;

                case 5:
                    returnVehicleUI(scanner, agency);
                    break;

                case 6:
                    agency.displayAllRentals();
                    break;
                case 7:
                    searchVehicleUI(scanner, agency);
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // ---------------- ADD VEHICLE ----------------

    private static void addVehicleUI(Scanner scanner, RentalAgency agency) {

        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter License Plate: ");
        String plate = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Base Price: ");
        double price = scanner.nextDouble();

        switch (type) {

            case 1:
                System.out.print("Number of doors: ");
                int doors = scanner.nextInt();
                agency.addVehicle(new Car(plate, model, price, doors));
                break;

            case 2:
                System.out.print("Has sidecar (true/false): ");
                boolean sidecar = scanner.nextBoolean();
                agency.addVehicle(new Motorcycle(plate, model, price, sidecar));
                break;

            case 3:
                System.out.print("Cargo capacity (tons): ");
                double capacity = scanner.nextDouble();
                agency.addVehicle(new Truck(plate, model, price, capacity));
                break;

            default:
                System.out.println("Invalid vehicle type.");
        }
    }

    // ---------------- ADD CUSTOMER ----------------

    private static void addCustomerUI(Scanner scanner, RentalAgency agency) {

        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter License Number: ");
        String license = scanner.nextLine();

        agency.addCustomer(new Customer(id, name, license));
    }

    // ---------------- RENT VEHICLE ----------------

    private static void rentVehicleUI(Scanner scanner, RentalAgency agency) {

        System.out.print("Enter License Plate: ");
        String plate = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter Rental Days: ");
        int days = scanner.nextInt();
        scanner.nextLine();

        try {
            agency.rentVehicle(plate, customerId, days);

        } catch (VehicleNotFoundException |
                 CustomerNotFoundException |
                 VehicleUnavailableException e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // ---------------- RETURN VEHICLE ----------------

    private static void returnVehicleUI(Scanner scanner, RentalAgency agency) {

        System.out.print("Enter License Plate: ");
        String plate = scanner.nextLine();

        try {
            agency.returnVehicle(plate);

        } catch (RentalException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

//search vehicle
private static void searchVehicleUI(Scanner scanner, RentalAgency agency) {

    System.out.print("Enter License Plate: ");
    String plate = scanner.nextLine();

    agency.searchVehicle(plate);
    }   
}