package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Main.*;

public class ServiceImpl implements Service {
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTruck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(), Driver[].class)));

    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public Truck findTruckById(int truckId) {
        Truck truck = trucks.stream().filter(x -> x.getId() == truckId).findAny().orElse(null);
        if (truck == null) {
            try {
                throw new Exception("We don't found this truck!!!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return truck;
    }

    public Driver findDriver() {
        Driver driverFree = getDrivers().stream().filter(x -> x.getTruckName().equals("")).findFirst().orElseThrow();
        return driverFree;
    }


    @Override
    public void changeDriver(int truckId) {
        Truck truck = findTruckById(truckId);
        Driver driver = findDriver();
        if (truck.getState() == State.BASE) {
            if (Optional.ofNullable(driver).isPresent()) {
                driver.setTruckName(truck.getTruckName());
                if (!truck.getDriver().equals(" ")) {
                    String oldDriversName = truck.getDriver();
                    getDrivers().stream().filter(x -> x.getName().equals(oldDriversName)).findFirst();
                    truck.setDriver(driver.getName());
                }
                truck.setDriver(driver.getName());
                System.out.println(truck.getTruckName() + "'s now truck drives " + driver.getName());
            } else if (truck.getState() == State.REPAIR) {
                System.out.println("We can't change driver,because driver on Repair!!!");
            }
        } else if (truck.getState() == State.ROUTE) {
            System.out.println("We can't change driver,because driver on Route!!!");
        }
    }

    @Override
    public void startDriving(int truckId) {
        Random random = new Random();
        Truck truck = findTruckById(truckId);
        if (truck.getState() == State.BASE) {
            if (truck.getDriver().equals(" ")) {
                System.out.println("truck is empty");
            } else if (!truck.getDriver().equals(" ")) {
                truck.setState(State.ROUTE);
                System.out.println("Truck successfully on route!!!");
            }
        } else if (truck.getState() == State.ROUTE) {
            System.out.println("We can't start driving,because truck is on the route yet!!!");
        } else if (truck.getState() == State.REPAIR) {
            int randomState = random.nextInt(1, 3);
            if (randomState == 1) {
                truck.setState(State.BASE);
                System.out.println("Truck is on the base");
            } else if ((randomState == 2)) {
                if (!truck.getDriver().equals(" ")) {
                    truck.setState(State.ROUTE);
                    System.out.println("Truck is on the way");
                }
            }
        }
        changeTruckState();
    }

    @Override
    public void startRepair(int truckId) {
        Truck truck = findTruckById(truckId);
        if (truck.getState() == State.BASE) {
            if (truck.getDriver() != null) {
                truck.setState(State.REPAIR);
                System.out.println("Truck successfully on repair!!!");
            }
        } else if (truck.getState() == State.REPAIR) {
            System.out.println("Truck already on the repair");
        } else if (truck.getState() == State.ROUTE) {
            if (truck.getDriver().equals(" ")) {
                truck.setState(State.REPAIR);
                System.out.println("The truck " + truck.getTruckName() + " is successfully on the road ");
            } else {
                System.out.println("The truck is without the driver");
            }
        }
    }

    @Override
    public void changeTruckState() {
        Map<State, List<Truck>> groupByState = getTrucks().stream().collect(Collectors.groupingBy(Truck::getState));
    }
}












