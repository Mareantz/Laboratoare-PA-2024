package org.example;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        CarpoolService carpoolService = new CarpoolService(5000);
        carpoolService.findMaximumCardinalityMatching();

//        Map<String, List<Person>> destinationMap = carpoolService.getDestinationMap();
//        System.out.println("\nDestination Map:");
//        destinationMap.entrySet().stream().forEach(entry ->
//        {
//            System.out.println(entry.getKey() + ":");
//            entry.getValue().stream().forEach(person -> System.out.println("\t" + person.getName()));
//        });
//
//        carpoolService.matchDriversAndPassengers();
//        System.out.println("\nAfter matching drivers and passengers:");
//        carpoolService.getDrivers().stream().forEach(driver ->
//        {
//            if (driver.getPassenger() != null)
//            {
//                System.out.println(driver.getName() + " is going with " + driver.getPassenger().getName() + " to " + driver.getDestination());
//            } else
//            {
//                System.out.println(driver.getName() + " is going alone to " + driver.getDestination());
//            }
//        });
    }
}