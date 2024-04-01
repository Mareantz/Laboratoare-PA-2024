package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
//import org.jgrapht.alg.matching.MaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class CarpoolService
{
    private static final String[] DESTINATIONS = {"Botosani", "Vaslui", "Craiova", "Calarasi", "Iasi", "Focsani", "Galati", "Hunedoara", "Cluj", "Oradea"};
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

    public CarpoolService(int numberOfPeople) {
        this.drivers = new ArrayList<>();
        this.passengers = new ArrayList<>();
        Faker faker = new Faker();

        // Generate drivers with random destinations from A to J
        for (int i = 0; i < numberOfPeople; i++) {
            String name = faker.name().fullName();
            String destination = DESTINATIONS[faker.random().nextInt(DESTINATIONS.length)];
            int age = faker.number().numberBetween(18, 50);

            Driver driver = new Driver(name, destination, age);
            drivers.add(driver);
        }

        // Generate passengers with random destinations from A to J
        for (int i = 0; i < numberOfPeople; i++) {
            String name = faker.name().fullName();
            String destination = DESTINATIONS[faker.random().nextInt(DESTINATIONS.length)];
            int age = faker.number().numberBetween(18, 50);

            Passenger passenger = new Passenger(name, destination, age);
            passengers.add(passenger);
        }
    }

   public void findMaximumCardinalityMatching() {

    Graph<Person, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);


    drivers.forEach(graph::addVertex);
    passengers.forEach(graph::addVertex);


    for (Driver driver : drivers) {
        for (Passenger passenger : passengers) {
            if (driver.getDestination().equals(passenger.getDestination()) && Math.random() < 0.1) {
                graph.addEdge(driver, passenger);
                break;
            }
        }
    }


    HopcroftKarpMaximumCardinalityBipartiteMatching<Person, DefaultEdge> matchingAlgorithm =
            new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, new HashSet<>(drivers), new HashSet<>(passengers));
    Set<DefaultEdge> matching = matchingAlgorithm.getMatching().getEdges();


    System.out.println("Number of matchings: " + matching.size());
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