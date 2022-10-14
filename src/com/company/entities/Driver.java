package com.company.entities;



public class Driver {
    private int idDiver;
    private String name;
    private String truckName;

    public Driver(int idDiver, String name, String truckName) {
        this.idDiver = idDiver;
        this.name = name;
        this.truckName = truckName;
    }

    public int getIdDiver() {
        return idDiver;
    }

    public void setIdDiver(int idDiver) {
        this.idDiver = idDiver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }
    @Override
    public String toString() {
//         idDiver + "    |" + name + "               |" + truckName;
        String s1 = String.format("%-5s|", idDiver);
        String s2 = String.format("%-14s|", name);
        String s3 = String.format("%-13s|", truckName);
        return s1+s2+s3;
    }
}
