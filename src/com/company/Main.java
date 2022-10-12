package com.company;

import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;
import com.company.service.Service;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static final Scanner scannerW = new Scanner(System.in);
    public static final Scanner scannerN = new Scanner(System.in);
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static final Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        ServiceImpl service = new ServiceImpl();
        while (true) {
            System.out.println(" id  |  TruckName   |    State    | Driver    ");
            service.getTruckList().stream().forEach(System.out::println);
            buttons();
            System.out.println("Choose the button:");
            String word = scannerW.nextLine();
            switch (word) {
                case "0" -> {
                    System.out.println("Enter the id of truck");
                    service.startDriving(scannerN.nextInt());
                }
                case "1" -> {
                    System.out.println("Type the id of truck");
                    service.changeDriver(scannerN.nextInt());
                }
                case "2" -> {
                    service.changeTruckState();
                }
                case "3" -> {
                    service.findTruckById(scannerN.nextInt()).setState(State.REPAIR);
                }
                case "4" -> {

                    service.changeTruckState();
                }
                case "5" -> {
                    for (Driver driver : service.getDriverList()) {
                        System.out.println(driver.toString());
                    }
                }
            }
        }
    }

    public static void buttons() {
        System.out.println(
                "Press 0 to startDriving\n" +
                "Press 1 to change Driver\n" +
                "Press 2 to send to the Route\n" +
                "Press 3 to send to the Repairing\n" +
                "Press 4 to send to the Repairing\n" +
                "Press 5 to see all drivers");

    }


    public static String readTruck() {
        return getString(WRITE_PATH);
    }

    public static String readDriver() {
        return getString(WRITE_PATH1);
    }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))) {
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }
}