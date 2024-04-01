package org.example;

public class Passenger extends Person
{
    public Passenger(String name, String destination, int age)
    {
        super(name, destination, age);
    }

    @Override
    public String toString() {
        return "Passenger{name='" + name + "', destination='" + destination + "', age=" + age + "}";
    }

}
