package src.model;

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String licensePlate, String model, double baseRentalPrice, boolean hasSidecar) {
        super(licensePlate, model, baseRentalPrice);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalPrice() * days; // cheapest
    }

    @Override
    public void displayDetails() {
        System.out.println("Motorcycle - Plate: " + getLicensePlate() +
                ", Model: " + getModel() +
                ", Sidecar: " + hasSidecar +
                ", Available: " + isAvailable());
    }
}