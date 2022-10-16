package com.company;

import com.company.entities.Truck;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

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
            service.getTrucks().forEach(System.out::println);
            System.out.println("-----------------------------------------------");
            System.out.println("id   | name         | truckName");
            service.getDrivers().forEach(System.out::println);
            System.out.println("Enter the id of truck:");
            int id = scannerN.nextInt();
            try {
                System.out.println(" id  |  TruckName   |    State    | Driver   \n" +
                        service.getTrucks().stream().filter(x -> x.getId() == id).map(Truck::toString).findFirst().get());
            } catch (NoSuchElementException e) {
                System.out.println("We didn't find the truck, try again");
            }

            System.out.println();
            buttons();
            switch (scannerW.nextLine()) {
                case "1" -> {
                    try {
                        System.out.println("Type the id of truck");
                        service.changeDriver(id);
                    } catch (NoSuchElementException e) {
                        System.out.println("All drivers are busy");
                    }
                }
                case "2" -> service.startDriving(id);
                case "3" -> service.startRepair(id);
            }
        }
    }

    public static void buttons() {
        System.out.println(
                """
                        Press 1 to change Driver
                        Press 2 to send to the Route
                        Press 3 to send to the Repairing
                         """
        );

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