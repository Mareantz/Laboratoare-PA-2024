package VehicleRoutingProblem;

public class Drone extends Vehicle
{
    private int maxFlightDuration;

    public Drone(Depot depot, int id, boolean available, int maxFlightDuration)
    {
        super(depot, id, available);
        this.maxFlightDuration = maxFlightDuration;
    }

    public Drone(int maxFlightDuration)
    {
        this.maxFlightDuration = maxFlightDuration;
    }

    public int getMaxFlightDuration()
    {
        return maxFlightDuration;
    }

    public void setMaxFlightDuration(int maxFlightDuration)
    {
        this.maxFlightDuration = maxFlightDuration;
    }
}