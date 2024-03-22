package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String[] names = {"Marian", "Dennis", "Cazan", "Sebi", "Miguel", "Luca", "Andrei"};
        String[] destinations = {"Galati", "Vaslui", "Iasi"};
        List<Person> persons = new ArrayList<>();

        // Create a random group of persons
        for (int i = 0; i < 10; i++) {
            String name = names[random.nextInt(names.length)];
            String destination = destinations[random.nextInt(destinations.length)];
            int age = random.nextInt(50) + 18;

            if (random.nextBoolean()) {
                persons.add(new Driver(name, destination, age));
            } else {
                persons.add(new Passenger(name, destination, age));
            }
        }

        List<Driver> drivers = persons.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        List<Passenger> passengers = persons.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toList());

        LinkedList<Driver> driverList = new LinkedList<>(drivers);
        driverList.sort(Comparator.comparingInt(Driver::getAge));

        System.out.println("Drivers:");

        driverList.forEach(driver -> System.out.println(driver.getName() + ", " + driver.getAge()));

        TreeSet<Passenger> passengerSet = new TreeSet<>(Comparator.comparing(Passenger::getName));
        passengerSet.addAll(passengers);

        System.out.println("\nPassengers:");

        passengerSet.forEach(passenger -> System.out.println(passenger.getName() + ", " + passenger.getAge()));
    }
}