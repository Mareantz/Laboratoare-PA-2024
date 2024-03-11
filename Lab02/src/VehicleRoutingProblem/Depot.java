package VehicleRoutingProblem;

import java.util.List;

/**
 * The type Depot.
 */
public class Depot
{
    private String name;
    private Vehicle[] vehicles = new Vehicle[256];
    private int vehicleCount;
    private int clientCount;
    private int maxVehicles;
    private int maxClients;
    private final int id;

    /**
     * Instantiates a new Depot.
     *
     * @param name         the name
     * @param vehicleCount the vehicle count
     * @param clientCount  the client count
     * @param maxVehicles  the max vehicles
     * @param maxClients   the max clients
     * @param id           the id
     */
    public Depot(String name, int vehicleCount, int clientCount, int maxVehicles, int maxClients, int id)
    {
        this.name = name;
        this.vehicleCount = vehicleCount;
        this.clientCount = clientCount;
        this.maxVehicles = maxVehicles;
        this.maxClients = maxClients;
        this.id = id;
    }

    /**
     * Get vehicles vehicle [ ].
     *
     * @return the vehicle [ ]
     */
    public Vehicle[] getVehicles()
    {
        return vehicles;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets vehicle count.
     *
     * @return the vehicle count
     */
    public int getVehicleCount()
    {
        return vehicleCount;
    }

    /**
     * Sets vehicle count.
     *
     * @param vehicleCount the vehicle count
     */
    public void setVehicleCount(int vehicleCount)
    {
        this.vehicleCount = vehicleCount;
    }

    /**
     * Gets client count.
     *
     * @return the client count
     */
    public int getClientCount()
    {
        return clientCount;
    }

    /**
     * Sets client count.
     *
     * @param clientCount the client count
     */
    public void setClientCount(int clientCount)
    {
        this.clientCount = clientCount;
    }

    /**
     * Gets max vehicles.
     *
     * @return the max vehicles
     */
    public int getMaxVehicles()
    {
        return maxVehicles;
    }

    /**
     * Sets max vehicles.
     *
     * @param maxVehicles the max vehicles
     */
    public void setMaxVehicles(int maxVehicles)
    {
        this.maxVehicles = maxVehicles;
    }

    /**
     * Gets max clients.
     *
     * @return the max clients
     */
    public int getMaxClients()
    {
        return maxClients;
    }

    /**
     * Sets max clients.
     *
     * @param maxClients the max clients
     */
    public void setMaxClients(int maxClients)
    {
        this.maxClients = maxClients;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets vehicles.
     *
     * @param vehicles the vehicles
     */
    public void setVehicles(Vehicle[] vehicles)
    {
        this.vehicles = vehicles;
    }

    @java.lang.Override
    public java.lang.String toString()
    {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicleCount=" + vehicleCount +
                ", clientCount=" + clientCount +
                ", maxVehicles=" + maxVehicles +
                ", maxClients=" + maxClients +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Depot depot = (Depot) obj;
        return id == depot.id;
    }

}
