package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class RentalAgency {

    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;

    public RentalAgency() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    // ---------------- VEHICLE MANAGEMENT ----------------

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicles) {
            v.displayDetails();
        }
    }

    public Vehicle findVehicle(String licensePlate) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return v;
            }
        }
        return null;
    }

    // ---------------- CUSTOMER MANAGEMENT ----------------

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    public Customer findCustomer(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                return c;
            }
        }
        return null;
    }

    // ---------------- RENTAL PROCESS ----------------

    public void rentVehicle(String licensePlate, String customerId, int days) {

        Vehicle vehicle = findVehicle(licensePlate);
        Customer customer = findCustomer(customerId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is already rented.");
            return;
        }

        Rental rental = new Rental(vehicle, customer, days);
        rentals.add(rental);

        System.out.println("Vehicle rented successfully.");
        rental.displayRentalDetails();
    }

    // ---------------- RETURN PROCESS ----------------

    public void returnVehicle(String licensePlate) {

        for (Rental r : rentals) {
            if (r.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)
                    && !r.isReturned()) {

                r.returnVehicle();

                System.out.println("Vehicle returned successfully.");
                r.displayRentalDetails();
                return;
            }
        }

        System.out.println("No active rental found for this vehicle.");
    }

    // ---------------- RENTAL HISTORY ----------------

    public void displayAllRentals() {
        for (Rental r : rentals) {
            r.displayRentalDetails();
            System.out.println("----------------------");
        }
    }
}