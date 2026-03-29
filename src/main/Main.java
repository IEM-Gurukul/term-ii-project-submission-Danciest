package main;

import model.*;
import service.RentalAgency;
import exception.*;

public class Main {
    public static void main(String[] args) {

        RentalAgency agency = new RentalAgency();

        agency.addVehicle(new Car("WB01A1234", "Honda City", 2000, 4));
        agency.addCustomer(new Customer("C001", "Renesh", "LIC12345"));

        try {
            agency.rentVehicle("WB01A1234", "C001", 3);

            // This will throw exception
            agency.rentVehicle("WB01A1234", "C001", 2);

        } catch (VehicleNotFoundException |
                 CustomerNotFoundException |
                 VehicleUnavailableException e) {

            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            agency.returnVehicle("WB01A1234");
            agency.returnVehicle("WB01A1234"); // will fail

        } catch (RentalException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
} 