package service;

import model.*;

import java.util.ArrayList;
import java.util.List;
import util.FileHandler;
import exception.CustomerNotFoundException;
import exception.RentalException;
import exception.VehicleNotFoundException;
import exception.VehicleUnavailableException;

public class RentalAgency {

    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;

    public RentalAgency() {
    vehicles = FileHandler.loadVehicles();
    customers = FileHandler.loadCustomers();
    rentals = new ArrayList<>();
}

    // ---------------- VEHICLE MANAGEMENT ----------------

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

// ✅ ADD THIS METHOD HERE
public void searchVehicle(String licensePlate) {

    Vehicle v = findVehicle(licensePlate);

    if (v != null) {
        v.displayDetails();
    } else {
        System.out.println("Vehicle not found.");
    }
}
    // ---------------- CUSTOMER MANAGEMENT ----------------

    public void addCustomer(Customer customer) {
    customers.add(customer);
    FileHandler.saveCustomers(customers);
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

    public void rentVehicle(String licensePlate, String customerId, int days)
        throws VehicleNotFoundException, CustomerNotFoundException, VehicleUnavailableException {

    Vehicle vehicle = findVehicle(licensePlate);
    Customer customer = findCustomer(customerId);

    if (vehicle == null) {
        throw new VehicleNotFoundException("Vehicle not found: " + licensePlate);
    }

    if (customer == null) {
        throw new CustomerNotFoundException("Customer not found: " + customerId);
    }

    if (!vehicle.isAvailable()) {
        throw new VehicleUnavailableException("Vehicle already rented: " + licensePlate);
    }

    Rental rental = new Rental(vehicle, customer, days);
    rentals.add(rental);

    System.out.println("Vehicle rented successfully.");
    rental.displayRentalDetails();
}

    // ---------------- RETURN PROCESS ----------------

    public void returnVehicle(String licensePlate) throws RentalException {

    for (Rental r : rentals) {
        if (r.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)
                && !r.isReturned()) {

            r.returnVehicle();

            System.out.println("Vehicle returned successfully.");
            r.displayRentalDetails();
            return;
        }
    }

    throw new RentalException("No active rental found for vehicle: " + licensePlate);
}

    // ---------------- RENTAL HISTORY ----------------

    public void displayAllRentals() {
        for (Rental r : rentals) {
            r.displayRentalDetails();
            System.out.println("----------------------");
        }
    }
}

