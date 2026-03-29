package util;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String VEHICLE_FILE = "data/vehicles.txt";
    private static final String CUSTOMER_FILE = "data/customers.txt";

    // ---------------- SAVE VEHICLES ----------------

    public static void saveVehicles(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE))) {

            for (Vehicle v : vehicles) {

                if (v instanceof Car) {
                    writer.write("Car," + v.getLicensePlate() + "," + v.getModel() + "," +
                            v.getBaseRentalPrice() + "," + ((Car) v).calculateRentalCost(1)/1.2);
                }

                else if (v instanceof Motorcycle) {
                    writer.write("Motorcycle," + v.getLicensePlate() + "," + v.getModel() + "," +
                            v.getBaseRentalPrice() + ",false");
                }

                else if (v instanceof Truck) {
                    writer.write("Truck," + v.getLicensePlate() + "," + v.getModel() + "," +
                            v.getBaseRentalPrice() + "," + ((Truck) v).calculateRentalCost(1)/1.5);
                }

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    // ---------------- LOAD VEHICLES ----------------

    public static List<Vehicle> loadVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String type = parts[0];

                if (type.equals("Car")) {
                    vehicles.add(new Car(parts[1], parts[2],
                            Double.parseDouble(parts[3]),
                            Integer.parseInt(parts[4])));
                }

                else if (type.equals("Motorcycle")) {
                    vehicles.add(new Motorcycle(parts[1], parts[2],
                            Double.parseDouble(parts[3]),
                            Boolean.parseBoolean(parts[4])));
                }

                else if (type.equals("Truck")) {
                    vehicles.add(new Truck(parts[1], parts[2],
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4])));
                }
            }

        } catch (IOException e) {
            System.out.println("No previous vehicle data found.");
        }

        return vehicles;
    }

    // ---------------- SAVE CUSTOMERS ----------------

    public static void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {

            for (Customer c : customers) {
                writer.write(c.getCustomerId() + "," + c.getName() + "," + c.getLicenseNumber());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // ---------------- LOAD CUSTOMERS ----------------

    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                customers.add(new Customer(parts[0], parts[1], parts[2]));
            }

        } catch (IOException e) {
            System.out.println("No previous customer data found.");
        }

        return customers;
    }
}