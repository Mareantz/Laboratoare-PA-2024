package org.example;

import java.util.ArrayList;
import java.util.List;

public class Driver extends Person
{
    private Passenger passenger;
    private List<String> route;

    public Driver(String name, String destination, int age)
    {
        super(name, destination, age);
        this.route = new ArrayList<>();
    }

    public void setPassenger(Passenger passenger)
    {
        if (this.getDestination().equals(passenger.getDestination()) || this.getRoute().contains(passenger.getDestination()))
        {
            this.passenger = passenger;
        }
    }

    public Passenger getPassenger()
    {
        return this.passenger;
    }

    public String getName()
    {
        return super.getName();
    }

    public String getDestination()
    {
        return super.getDestination();
    }

    public List<String> getRoute()
    {
        return this.route;
    }

    public void addDestinationToRoute(String destination)
    {
        this.route.add(destination);
    }

    @Override
    public String toString() {
        return "Driver{name='" + name + "', destination='" + destination + "', age=" + age + "}";
    }
}