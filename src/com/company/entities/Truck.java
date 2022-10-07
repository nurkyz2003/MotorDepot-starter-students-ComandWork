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

    @Override
    public String toString() {
        return id + "  |" + truckName + "     |" + State + "      |" + driver;
    }

}

enum State {
    BASE, ROUTE, REPAIR


}

