package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CarpoolService
{
    private List<Driver> drivers;
    private List<Passenger> passengers;
    private Map<String, List<Person>> destinationMap;

    public List<Driver> getDrivers()
    {
        return this.drivers;
    }

    public void addDriver(Driver driver)
    {
        this.drivers.add(driver);
    }

    public void addPassenger(Passenger passenger)
    {
        this.passengers.add(passenger);
    }

    public CarpoolService(int numberOfPeople)
    {
        this.drivers = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.destinationMap = new HashMap<>();
        Faker faker = new Faker();

        // Generate 10 drivers with random city names and random stops
        IntStream.range(0, 10).forEach(i ->
        {
            String name = faker.name().fullName();
            String destination = faker.address().cityName();
            int age = faker.number().numberBetween(18, 70);

            Driver driver = new Driver(name, destination, age);

            // Generate random stops for the driver
            int numberOfStops = faker.number().numberBetween(1, 5);
            Stream.generate(faker.address()::cityName)
                    .limit(numberOfStops)
                    .forEach(driver::addDestinationToRoute);

            drivers.add(driver);
        });

        // Get all city names generated for the drivers and their stops
        List<String> allCityNames = drivers.stream()
                .flatMap(driver -> Stream.concat(Stream.of(driver.getDestination()), driver.getRoute().stream()))
                .collect(Collectors.toList());

        // Generate passengers whose destinations are some of the city names generated for the drivers and their stops
        IntStream.range(0, numberOfPeople).forEach(i ->
        {
            String name = faker.name().fullName();
            int age = faker.number().numberBetween(18, 50);

            // Randomly select a city name from the list of city names generated for the drivers and their stops
            String destination = allCityNames.get(faker.number().numberBetween(0, allCityNames.size()));

            Passenger passenger = new Passenger(name, destination, age);
            passengers.add(passenger);
        });
    }

    public void addDestination(String destination)
    {
        // Add the destination to the map with an empty list of people
        destinationMap.putIfAbsent(destination, new ArrayList<>());
    }

    public Set<String> getAllDestinations()
    {
        return new HashSet<>(destinationMap.keySet());
    }

    public Map<String, List<Person>> getDestinationMap()
    {
        List<Person> allPeople = new ArrayList<>();
        allPeople.addAll(drivers);
        allPeople.addAll(passengers);

        return allPeople.stream()
                .collect(Collectors.groupingBy(Person::getDestination));
    }

    public void matchDriversAndPassengers() {
        passengers.forEach(passenger -> {
            drivers.stream()
                    .filter(driver -> driver.getDestination().equals(passenger.getDestination()) || driver.getRoute().contains(passenger.getDestination()))
                    .findFirst()
                    .ifPresent(driver -> driver.setPassenger(passenger));
        });
    }
}