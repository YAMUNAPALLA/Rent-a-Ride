class Driver {
    private String name;
    private String carModel;
    private double rating;
    private double distanceFromCustomer;

    public Driver(String name, String carModel, double rating, double distanceFromCustomer) {
        this.name = name;
        this.carModel = carModel;
        this.rating = rating;
        this.distanceFromCustomer = distanceFromCustomer;
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public double getRating() {
        return rating;
    }

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }
}

class RideService {
    private double chargePerKm;
    private Driver[] drivers;
    private int driverCount;

    public RideService(double chargePerKm, int maxDrivers) {
        this.chargePerKm = chargePerKm;
        this.drivers = new Driver[maxDrivers];
        this.driverCount = 0;
    }

    public void addDriver(Driver driver) {
        drivers[driverCount] = driver;
        driverCount++;
    }

    public Driver findAvailableDriver(double distance, String carRequested) {
        Driver closestDriver = null;

        for (int i = 0; i < driverCount; i++) {
            Driver driver = drivers[i];
            if (driver.getCarModel().equals(carRequested) && driver.getRating() >= 4) {
                if (closestDriver == null || driver.getDistanceFromCustomer() < closestDriver.getDistanceFromCustomer()) {
                    closestDriver = driver;
                }
            }
        }

        return closestDriver;
    }

    public double calculateCharge(double distance) {
        return distance * chargePerKm;
    }
}

public class Main {
    public static void main(String[] args) {
        int maxDrivers = 5;
        RideService rideService = new RideService(8, maxDrivers);

        // Adding drivers
        rideService.addDriver(new Driver("A", "Sedan", 4, 500));
        rideService.addDriver(new Driver("B", "Hatchback", 4.3, 1000));
        rideService.addDriver(new Driver("C", "5-Seater", 4.8, 200));
        rideService.addDriver(new Driver("D", "Sedan", 4.1, 700));
        rideService.addDriver(new Driver("E", "Hatchback", 4.7, 430));

        // Sample input
        double customerDistance = 43;
        String carRequested = "Sedan";

        // Find available driver
        Driver selectedDriver = rideService.findAvailableDriver(customerDistance, carRequested);

        if (selectedDriver == null) {
            System.out.println("No available driver found for the requested car model.");
        } else {
            System.out.println("Driver " + selectedDriver.getName() + " will get you to the destination.");
            double totalCharge = rideService.calculateCharge(customerDistance);
            System.out.println("Your charge will be Rs " + totalCharge + ".");
        }
    }
}
