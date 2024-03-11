package VehicleRoutingProblem;

import java.util.List;
import java.util.ArrayList;

/**
 * The Vehicle class represents a vehicle in a vehicle routing problem.
 * It contains a depot, a list of clients, and a status indicating whether it is available.
 */

public abstract class Vehicle
{
    /**
     * The depot where the vehicle is located.
     */
    private Depot depot;
    /**
     * The availability status of the vehicle.
     */
    private boolean available;
    /**
     * A list of clients assigned to the vehicle.
     */
    private List<Client> clients = new ArrayList<>();

    /**
     * The ID of the vehicle.
     */
    private int id; // poate fi nr de sasiu etc
    /**
     * Performs a tour by visiting all assigned clients and then returning to the depot.
     */

    public void performTour()
    {
        System.out.println("Vehicle " + this.getId() + " is starting from depot: " + depot.getName());
        for (Client client : this.getClients())
        {
            System.out.println("Vehicle " + this.getId() + " is visiting client: " + client.getName());
        }
        System.out.println("Vehicle " + this.getId() + " is returning to depot: " + depot.getName());
    }
    /**
     * Constructs a new Vehicle with the given depot, ID, and availability status.
     *
     * @param depot     the depot
     * @param id        the ID
     * @param available the availability status
     */

    public Vehicle(Depot depot, int id, boolean available)
    {
        this.depot = depot;
        this.id = id;
        this.available = available;
    }
    /**
     * Default constructor.
     */

    public Vehicle()
    {
    }
    /**
     * Returns the depot of the vehicle.
     *
     * @return the depot
     */
    public Depot getDepot()
    {
        return depot;
    }
    /**
     * Sets the depot of the vehicle.
     *
     * @param depot the depot
     */

    public void setDepot(Depot depot)
    {
        this.depot = depot;
    }
    /**
     * Returns the availability status of the vehicle.
     *
     * @return the availability status
     */

    public boolean isAvailable()
    {
        return available;
    }
    /**
     * Sets the availability status of the vehicle.
     *
     * @param available the availability status
     */

    public void setAvailable(boolean available)
    {
        this.available = available;
    }
    /**
     * Assigns a client to the vehicle.
     *
     * @param client the client
     */

    public void assignClient(Client client)
    {
        clients.add(client);
    }
    /**
     * Returns the list of clients assigned to the vehicle.
     *
     * @return the list of clients
     */

    public List<Client> getClients()
    {
        return clients;
    }
    /**
     * Sets the list of clients assigned to the vehicle.
     *
     * @param clients the list of clients
     */

    public void setClients(List<Client> clients)
    {
        this.clients = clients;
    }
    /**
     * Returns the ID of the vehicle.
     *
     * @return the ID
     */

    public int getId()
    {
        return id;
    }
    /**
     * Returns the last client assigned to the vehicle.
     *
     * @return the last client, or null if no clients are assigned
     */

    public Client getLastClient()
    {
        if (clients.isEmpty())
        {
            return null;
        }
        else
        {
            return clients.get(clients.size() - 1);
        }
    }
    /**
     * Returns a string representation of the vehicle.
     *
     * @return a string representation of the vehicle
     */

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "{" + "depot=" + depot + ", available=" + available + ", id=" + id + '}';
    }
    /**
     * Determines whether this vehicle is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */

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
        Vehicle vehicle = (Vehicle) obj;
        return id == vehicle.id;
    }
}