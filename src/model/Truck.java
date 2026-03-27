package src.model;

public class Truck extends Vehicle {
    private double cargoCapacity;

    public Truck(String licensePlate, String model, double baseRentalPrice, double cargoCapacity) {
        super(licensePlate, model, baseRentalPrice);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalPrice() * days * 1.5; // expensive
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck - Plate: " + getLicensePlate() +
                ", Model: " + getModel() +
                ", Capacity: " + cargoCapacity + " tons" +
                ", Available: " + isAvailable());
    }
}