package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.Main.*;

public class ServiceImpl implements Service{
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(),Driver[].class)));
    List<Truck> truckList = new ArrayList<>(trucks);
    List<Driver> driverList = new ArrayList<>(drivers);

    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
    public Truck findTruckById(int truckId) {
        Truck truck = truckList.stream().filter(x -> x.getId() == truckId).findAny().orElse(null);
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
        Driver driverFree = driverList.stream().filter(x -> x.getTruckName().equals("")).findFirst().orElse(null);
        if (driverFree == null) {
            try {
                throw new Exception("We can't find free drivers!!!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return driverFree;
    }

    @Override
    public void changeDriver(int truckId) {
        Truck truck = findTruckById(truckId);
        Driver driver = findDriver();
        if (truck.getState() == State.BASE) {
            if (driver != null) {
                driver.setTruckName(truck.getTruckName());
                if (truck.getDriver() != null) {
                    String oldDriversName = truck.getDriver();
                    drivers.stream().filter(x -> x.getName().equals(oldDriversName)).findAny().get().setTruckName("");
                }
                truck.setDriver(driver.getName());
                System.out.println(truck.getTruckName() + "'s truck is now drive " + driver.getName());
            }else if (truck.getState() == State.REPAIR) {
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
            if (truck.getDriver() != null) {
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
            } else if (randomState == 2) {
                truck.setState(State.ROUTE);
                System.out.println("Truck is on the way");
            }
        }
    }

    @Override
    public void startRepair(int truckId) {

    }

    @Override
    public void changeTruckState() {

    }
}












