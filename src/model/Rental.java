package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {

    private Vehicle vehicle;
    private Customer customer;

    private int plannedDays;
    private LocalDate startDate;
    private LocalDate returnDate;

    private boolean returned;

    public Rental(Vehicle vehicle, Customer customer, int plannedDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.plannedDays = plannedDays;

        this.startDate = LocalDate.now();
        this.returned = false;

        vehicle.setAvailable(false);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isReturned() {
        return returned;
    }

    // ---------------- BILLING ----------------

    public double calculateBill() {

        long actualDays;

        if (returned) {
            actualDays = ChronoUnit.DAYS.between(startDate, returnDate);
        } else {
            actualDays = plannedDays;
        }

        double baseCost = vehicle.calculateRentalCost((int) actualDays);

        // Late fee
        if (actualDays > plannedDays) {
            long extraDays = actualDays - plannedDays;

            double lateFee = extraDays * vehicle.getBaseRentalPrice() * 1.8;

            return baseCost + lateFee;
        }

        return baseCost;
    }

    // ---------------- RETURN ----------------

    public void returnVehicle() {
        this.returned = true;
        this.returnDate = LocalDate.now();
        vehicle.setAvailable(true);
    }

    // ---------------- DISPLAY ----------------

    public void displayRentalDetails() {
        System.out.println("Rental Details:");
        vehicle.displayDetails();
        customer.displayDetails();

        System.out.println("Planned Days: " + plannedDays);
        System.out.println("Returned: " + returned);

        if (returned) {
            long actualDays = ChronoUnit.DAYS.between(startDate, returnDate);
            System.out.println("Actual Days: " + actualDays);
        }

        System.out.println("Total Bill: " + calculateBill());
    }
}