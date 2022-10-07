package com.company.service;


import com.company.entities.Driver;
import com.company.entities.Truck;

import java.util.ArrayList;
import java.util.List;

import static com.company.Main.*;

public class ServiceImpl implements Service{
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(),Driver[].class)));


    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void changeDriver(int truckId) {

    }

    @Override
    public void startDriving(int truckId) {

    }

    @Override
    public void startRepair(int truckId) {

    }

    @Override
    public void changeTruckState() {

    }
}












