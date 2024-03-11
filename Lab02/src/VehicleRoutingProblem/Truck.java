package VehicleRoutingProblem;

/**
 * The type Truck.
 */
public class Truck extends Vehicle
{
    private int capacity;

    /**
     * Instantiates a new Truck.
     *
     * @param depot     the depot
     * @param id        the id
     * @param available the available
     * @param capacity  the capacity
     */
    public Truck(Depot depot, int id, boolean available, int capacity)
    {
        super(depot, id, available);
        this.capacity = capacity;
    }

    /**
     * Instantiates a new Truck.
     *
     * @param capacity the capacity
     */
    public Truck(int capacity)
    {
        this.capacity = capacity;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
}