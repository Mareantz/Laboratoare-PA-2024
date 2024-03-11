package VehicleRoutingProblem;

/**
 * The type Drone.
 */
public class Drone extends Vehicle
{
    private int maxFlightDuration;

    /**
     * Instantiates a new Drone.
     *
     * @param depot             the depot
     * @param id                the id
     * @param available         the available
     * @param maxFlightDuration the max flight duration
     */
    public Drone(Depot depot, int id, boolean available, int maxFlightDuration)
    {
        super(depot, id, available);
        this.maxFlightDuration = maxFlightDuration;
    }

    /**
     * Instantiates a new Drone.
     *
     * @param maxFlightDuration the max flight duration
     */
    public Drone(int maxFlightDuration)
    {
        this.maxFlightDuration = maxFlightDuration;
    }

    /**
     * Gets max flight duration.
     *
     * @return the max flight duration
     */
    public int getMaxFlightDuration()
    {
        return maxFlightDuration;
    }

    /**
     * Sets max flight duration.
     *
     * @param maxFlightDuration the max flight duration
     */
    public void setMaxFlightDuration(int maxFlightDuration)
    {
        this.maxFlightDuration = maxFlightDuration;
    }
}