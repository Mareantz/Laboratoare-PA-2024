package org.example;

import java.util.List;

public class CarpoolService
{
    private Driver driver;
    private Passenger passenger;

    public CarpoolService(Driver driver, Passenger passenger)
    {
        this.driver = driver;
        this.passenger = passenger;
    }

    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }

    public void setPassenger(Passenger passenger)
    {
        this.passenger = passenger;
    }

    public void assignPassenger()
    {
        driver.setPassenger(passenger);
    }

    public void matchDriverAndPassenger(List<Driver> drivers, List<Passenger> passengers)
    {
        for(Driver driver : drivers)
        {
            for(Passenger passenger : passengers)
            {
                if(driver.getDestination().equals(passenger.getDestination()))
                {
                    driver.setPassenger(passenger);
                    break;
                }
            }
        }
    }
}
