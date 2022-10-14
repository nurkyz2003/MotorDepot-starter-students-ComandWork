package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Driver findDriver(){
//        Driver driver = getDriverList().stream().filter(x->x.getIdDiver() == id).findFirst().orElse(null);
        Driver driverFree = null;
        try {
            driverFree = getDrivers().stream().filter(x -> x.getTruckName().equals("")).findFirst().orElseThrow();
            if (driverFree == null) {
                try {
                    throw new Exception("We can't find free drivers!!!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return driverFree;
    }



    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void changeDriver(int truckId) throws Exception {
        Truck truck = findTruckById(truckId);
        Driver driver = findDriver();
        if (truck.getState() == State.BASE) {
            if (driver != null) {
                driver.setTruckName(truck.getTruckName());
                if (truck.getDriver() != null) {
                    String oldDriversName = truck.getDriver();
                    getDrivers().stream().filter(x -> x.getName().equals(oldDriversName)).findFirst();
                    truck.setDriver(driver.getName());
                }
                truck.setDriver(driver.getName());
                System.out.println(truck.getTruckName() + "'s truck is now drive " + driver.getName());
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
      Truck truck = findTruckById(truckId);
      if(truck.getState() == State.BASE){
          if(truck.getDriver() != null){
              truck.setState(State.REPAIR);
              System.out.println("Truck successfully on repair!!!");
          }
      }
      else if (truck.getState() == State.REPAIR){
          System.out.println("Truck already on the repair");
      }
      else if (truck.getState() == State.ROUTE){
          if(truck.getDriver() != null){
              truck.setState(State.REPAIR);
              System.out.println("The truck "+ truck.getTruckName() + " is successfully on the road ");
          }else {
              System.out.println("The truck is without the driver");
          }
      } {

      }
    }

    @Override
    public void changeTruckState() {
        System.out.println("Enter the truck id");
        int id = scannerN.nextInt();
        Truck truck = findTruckById(id);
        System.out.println("""
                Which state do you wanna give, choose one of them:
                1) Base2) Route
                3)Repair
                """);
        int state = scannerN.nextInt();
        if (state == 1) {
            if (truck.getState() != State.BASE)
                truck.setState(State.BASE);
        } else if (truck.getState() == null) {
            truck.setState(State.BASE);
        } else System.out.println("Your truck already on base");
        if (state == 2) {
            if (truck.getState() != State.ROUTE)
                truck.setState(State.ROUTE);
            else if (truck.getState() == null) {
                truck.setState(State.ROUTE);
            } else System.out.println("Your truck already on route");

        } else if (state == 3) {
            if (truck.getState() != State.REPAIR)
                truck.setState(State.REPAIR);
            else if (truck.getState() == null) {
                truck.setState(State.REPAIR);
            } else System.out.println("Your truck already on repair");
        }

    }
}












