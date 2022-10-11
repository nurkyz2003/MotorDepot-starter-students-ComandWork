package com.company.entities;

public class Truck {
    private int id;
    private String truckName;
    private String driver;
    private State State;


    public Truck(int id, String truckName, String driver, State State) {
        this.id = id;
        this.truckName = truckName;
        this.driver = driver;
        this.State = State;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public com.company.entities.State getState() {
        return State;
    }

    public void setState(com.company.entities.State state) {
        State = state;
    }


    @Override
    public String toString() {
//        return id + "  |" + truckName + "     |" + State + "      |" + driver;
        String s1 = String.format("%-5s|", id);
        String s2 = String.format("%-14s|", truckName);
        String s3 = String.format("%-13s|", State);
        String s4 = String.format("%-20s", driver);
        return s1 + s2 + s3 + s4;
    }
}




