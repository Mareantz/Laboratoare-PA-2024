package org.example;

public class Driver extends Person
{
    private Passenger passenger;

    public Driver(String name, String destination,int age)
    {
        super(name, destination, age);
    }

    public void setPassenger(Passenger passenger)
    {
        if(this.destination.equals(passenger.getDestination()))
        {
            this.passenger = passenger;
        }
    }
}
