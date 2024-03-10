package VehicleRoutingProblem;
import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public abstract class Vehicle
{
    private Depot depot;
    private boolean available;
    private List<Client> clients = new ArrayList<>();


    private int id; // poate fi nr de sasiu etc

    public void performTour(List<Client> clients)
    {
        System.out.println("Starting from depot: " + depot.getName());
        for (Client client : clients)
        {
            System.out.println("Visiting client: " + client.getName());
        }
        System.out.println("Returning to depot: " + depot.getName());
    }

    public Vehicle(Depot depot, int id,boolean available)
    {
        this.depot = depot;
        this.id = id;
        this.available = available;
    }

    public Vehicle()
    {
    }

    public Depot getDepot()
    {
        return depot;
    }

    public void setDepot(Depot depot)
    {
        this.depot = depot;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    public void assignClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getId()
    {
        return id;
    }

    public Client getLastClient() {
        if (clients.isEmpty()) {
            return null;
        } else {
            return clients.get(clients.size() - 1);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "depot=" + depot +
                ", available=" + available +
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
        Vehicle vehicle = (Vehicle) obj;
        return id == vehicle.id;
    }
}