package src.model;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String licensePlate, String model, double baseRentalPrice, int numberOfDoors) {
        super(licensePlate, model, baseRentalPrice);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalPrice() * days * 1.2; // car multiplier
    }

    @Override
    public void displayDetails() {
        System.out.println("Car - Plate: " + getLicensePlate() +
                ", Model: " + getModel() +
                ", Doors: " + numberOfDoors +
                ", Available: " + isAvailable());
    }
}