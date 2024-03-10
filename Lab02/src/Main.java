import VehicleRoutingProblem.Problem;
import VehicleRoutingProblem.Depot;
import VehicleRoutingProblem.Vehicle;
import VehicleRoutingProblem.Client;
import VehicleRoutingProblem.Drone;
import VehicleRoutingProblem.Truck;


import java.time.LocalTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create some depots
        Depot depot1 = new Depot("Depot 1", 0, 0, 10, 10, 1);
        Depot depot2 = new Depot("Depot 2", 0, 0, 10, 10, 2);

        // Create some vehicles
        Vehicle vehicle1 = new Truck(depot1, 1, true,100);
        Vehicle vehicle2 = new Drone(depot1, 2, true,60);
        Vehicle vehicle3 = new Truck(depot2, 3, true,80);
        Vehicle vehicle4 = new Drone(depot2, 4, true,50);

        // Assign the vehicles to the depots
        depot1.setVehicles(new Vehicle[]{vehicle1, vehicle2});
        depot2.setVehicles(new Vehicle[]{vehicle3, vehicle4});

        // Create more clients with different visit times
        Client client1 = new Client("Client 1", 1, LocalTime.of(9, 0), LocalTime.of(10, 0));
        Client client2 = new Client("Client 2", 2, LocalTime.of(10, 0), LocalTime.of(11, 0));
        Client client3 = new Client("Client 3", 3, LocalTime.of(11, 0), LocalTime.of(12, 0));
        Client client4 = new Client("Client 4", 4, LocalTime.of(12, 0), LocalTime.of(13, 0));
        Client client5 = new Client("Client 5", 5, LocalTime.of(9, 30), LocalTime.of(10, 30));
        Client client6 = new Client("Client 6", 6, LocalTime.of(10, 30), LocalTime.of(11, 30));
        Client client7 = new Client("Client 7", 7, LocalTime.of(11, 30), LocalTime.of(12, 30));
        Client client8 = new Client("Client 8", 8, LocalTime.of(12, 30), LocalTime.of(13, 30));

        // Create the problem
        Problem problem = new Problem(Arrays.asList(depot1, depot2), Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8));

        // Assign the clients to the vehicles
        problem.assignClients();

        // Print out the assignments
        for (Vehicle vehicle : problem.getVehicles()) {
            System.out.println("Vehicle " + vehicle.getId() + " has clients: " + vehicle.getClients());
        }
    }
}