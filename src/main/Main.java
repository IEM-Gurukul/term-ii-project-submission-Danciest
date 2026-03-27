package main;

import model.*;

public class Main {
    public static void main(String[] args) {

        Vehicle car = new Car("WB01A1234", "Honda City", 2000, 4);
        Vehicle Motorcycle = new Motorcycle("WB02B5678", "Yamaha R15", 800, false);
        Vehicle Truck = new Truck("WB03C9999", "Tata Truck", 5000, 10);

        Customer customer = new Customer("C001", "Renesh", "LIC12345");

        Rental rental = new Rental(car, customer, 3);

        rental.displayRentalDetails();

        rental.returnVehicle();

        System.out.println("\nAfter return:");
        rental.displayRentalDetails();
    }
}