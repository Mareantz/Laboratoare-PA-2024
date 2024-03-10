package VehicleRoutingProblem;
import java.util.List;
public class Depot
{
    private String name;
    private Vehicle[] vehicles = new Vehicle[256];
    private Client[] clients = new Client[256];
    private int vehicleCount;
    private int clientCount;
    private int maxVehicles;
    private int maxClients;
    private final int id;

    public Depot(String name, int vehicleCount, int clientCount, int maxVehicles, int maxClients, int id)
    {
        this.name = name;
        this.vehicleCount = vehicleCount;
        this.clientCount = clientCount;
        this.maxVehicles = maxVehicles;
        this.maxClients = maxClients;
        this.id = id;
    }

    public Vehicle[] getVehicles()
    {
        return vehicles;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getVehicleCount()
    {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount)
    {
        this.vehicleCount = vehicleCount;
    }

    public int getClientCount()
    {
        return clientCount;
    }

    public void setClientCount(int clientCount)
    {
        this.clientCount = clientCount;
    }

    public int getMaxVehicles()
    {
        return maxVehicles;
    }

    public void setMaxVehicles(int maxVehicles)
    {
        this.maxVehicles = maxVehicles;
    }

    public int getMaxClients()
    {
        return maxClients;
    }

    public void setMaxClients(int maxClients)
    {
        this.maxClients = maxClients;
    }

    public int getId()
    {
        return id;
    }

    public void setVehicles(Vehicle[] vehicles)
    {
        this.vehicles = vehicles;
    }

    public void assignClient(Client client, int[][] depotToClientTimes, List<Client> allClients, int depotIndex) {
        Vehicle quickestVehicle = null;
        int quickestTime = Integer.MAX_VALUE;

        for (Vehicle vehicle : vehicles) {
            int clientIndex = allClients.indexOf(client);
            int travelTime = depotToClientTimes[depotIndex][clientIndex];

            if (travelTime < quickestTime) {
                quickestVehicle = vehicle;
                quickestTime = travelTime;
            }
        }

        quickestVehicle.assignClient(client);
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
