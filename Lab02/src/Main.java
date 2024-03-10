import VehicleRoutingProblem.Problem;
import VehicleRoutingProblem.Depot;
import VehicleRoutingProblem.Vehicle;
import VehicleRoutingProblem.Client;
import VehicleRoutingProblem.Drone;
import VehicleRoutingProblem.Truck;
import VehicleRoutingProblem.Solution;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Depot depot1 = new Depot("Depot 1", 0, 0, 10, 10, 1);
        Depot depot2 = new Depot("Depot 2", 0, 0, 10, 10, 2);
        List<Depot> depots = new ArrayList<Depot>();
        depots.add(depot1);
        depots.add(depot2);

        Vehicle vehicle1 = new Truck(depot1, 1, true, 100);
        Vehicle vehicle2 = new Drone(depot1, 2, true, 60);
        Vehicle vehicle3 = new Truck(depot2, 3, true, 80);
        Vehicle vehicle4 = new Drone(depot2, 4, true, 50);

        depot1.setVehicles(new Vehicle[]{vehicle1, vehicle2});
        depot2.setVehicles(new Vehicle[]{vehicle3, vehicle4});

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Client 1", 1, LocalTime.of(9, 0), LocalTime.of(10, 0)));
        clients.add(new Client("Client 2", 2, LocalTime.of(10, 0), LocalTime.of(11, 0)));
        clients.add(new Client("Client 3", 3, LocalTime.of(11, 0), LocalTime.of(12, 0)));
        clients.add(new Client("Client 4", 4, LocalTime.of(12, 0), LocalTime.of(13, 0)));
        clients.add(new Client("Client 5", 5, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        clients.add(new Client("Client 6", 6, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        clients.add(new Client("Client 7", 7, LocalTime.of(11, 30), LocalTime.of(12, 30)));
        clients.add(new Client("Client 8", 8, LocalTime.of(12, 30), LocalTime.of(13, 30)));

        Problem problem = new Problem(depots, clients);

        Solution solution = new Solution(problem);

        for (Vehicle vehicle : problem.getVehicles())
        {
            System.out.println("Vehicle " + vehicle.getId() + " has clients: " + vehicle.getClients());
        }
    }
}