package src.model;

public class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int rentalDays;
    private boolean returned;

    public Rental(Vehicle vehicle, Customer customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.returned = false;

        // mark vehicle unavailable when rented
        vehicle.setAvailable(false);
    }

    public double calculateBill() {
        return vehicle.calculateRentalCost(rentalDays);
    }

    public void returnVehicle() {
        this.returned = true;
        vehicle.setAvailable(true);
    }

    public boolean isReturned() {
        return returned;
    }

    public void displayRentalDetails() {
        System.out.println("Rental Details:");
        vehicle.displayDetails();
        customer.displayDetails();
        System.out.println("Days: " + rentalDays +
                ", Bill: " + calculateBill() +
                ", Returned: " + returned);
    }
}