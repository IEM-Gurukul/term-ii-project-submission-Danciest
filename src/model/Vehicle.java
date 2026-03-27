package src.model;

public abstract class Vehicle {
    private String licensePlate;
    private String model;
    private double baseRentalPrice;
    private boolean isAvailable;

    public Vehicle(String licensePlate, String model, double baseRentalPrice) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.baseRentalPrice = baseRentalPrice;
        this.isAvailable = true; // default available
    }

    // Abstract methods
    public abstract double calculateRentalCost(int days);
    public abstract void displayDetails();

    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter (controlled access)
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}