package VehicleRoutingProblem;

import java.time.LocalTime;

/**
 * The Client class represents a client in a vehicle routing problem.
 * It contains an ID, a name, and visit start and end times.
 */
public class Client
{
    /**
     * The type of the client, either regular or premium.
     */
    private enum type
    {
        /**
         * Regular type.
         */
        regular,
        /**
         * Premium type.
         */
        premium
    }
    /**
     * The ID of the client.
     */
    private int id;
    /**
     * The name of the client.
     */
    private final String name;
    /**
     * The visit start time of the client.
     */
    private LocalTime visitStartTime;
    /**
     * The visit end time of the client.
     */
    private LocalTime visitEndTime;

    /**
     * Constructs a new Client with the given name, ID, visit start time, and visit end time.
     *
     * @param name           the name
     * @param id             the ID
     * @param visitStartTime the visit start time
     * @param visitEndTime   the visit end time
     */
    public Client(String name, int id, LocalTime visitStartTime, LocalTime visitEndTime)
    {
        this.name = name;
        this.id = id;
        this.visitStartTime = visitStartTime;
        this.visitEndTime = visitEndTime;
    }

    /**
     * Returns the name of the client.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

     /**
     * Returns the visit start time of the client.
     *
     * @return the visit start time
     */
    public LocalTime getVisitStartTime()
    {
        return visitStartTime;
    }

    /**
     * Sets the visit start time of the client.
     *
     * @param visitStartTime the visit start time
     */
    public void setVisitStartTime(LocalTime visitStartTime)
    {
        this.visitStartTime = visitStartTime;
    }

    /**
     * Returns the visit end time of the client.
     *
     * @return the visit end time
     */
    public LocalTime getVisitEndTime()
    {
        return visitEndTime;
    }

    /**
     * Sets the visit end time of the client.
     *
     * @param visitEndTime the visit end time
     */
    public void setVisitEndTime(LocalTime visitEndTime)
    {
        this.visitEndTime = visitEndTime;
    }

    /**
     * Returns the ID of the client.
     *
     * @return the ID
     */
    public int getId()
    {
        return id;
    }
    /**
     * Returns a string representation of the client.
     *
     * @return a string representation of the client
     */

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
//                ", name='" + name + '\'' +
//                ", visitStartTime=" + visitStartTime +
//                ", visitEndTime=" + visitEndTime +
                '}';
    }
    /**
     * Determines whether this client is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass())  // if both objects are null and not of the same class
        {
            return false;
        }
        Client guest = (Client) obj;
        return id == guest.id; // id is unique so it's enough
    }
}
