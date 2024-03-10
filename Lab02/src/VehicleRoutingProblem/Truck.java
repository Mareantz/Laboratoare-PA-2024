package VehicleRoutingProblem;

public class Truck extends Vehicle
{
    private int capacity;

    public Truck(Depot depot, int id, boolean available, int capacity)
    {
        super(depot, id, available);
        this.capacity = capacity;
    }

    public Truck(int capacity)
    {
        this.capacity = capacity;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
}