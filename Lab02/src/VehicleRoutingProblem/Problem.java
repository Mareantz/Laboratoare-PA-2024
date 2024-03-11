package VehicleRoutingProblem;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
import java.time.LocalTime;
import java.util.Map;
import java.util.HashMap;

/**
 * The Problem class represents a vehicle routing problem.
 * It contains a list of depots and clients, and the travel times between them.
 */
public class Problem
{
    /**
     * A list of depots in the problem.
     */
    private List<Depot> depots;
    /**
     * A list of clients in the problem.
     */
    private List<Client> clients;
    /**
     * A 2D array representing the travel times from each depot to each client.
     */
    private int[][] depotToClientTimes;
    /**
     * A 2D array representing the travel times from each client to each other client.
     */
    private int[][] clientToClientTimes;

    /**
     * Constructs a new Problem with the given depots and clients.
     * Also generates the travel times between depots and clients.
     *
     * @param depots  the depots
     * @param clients the clients
     */
    public Problem(List<Depot> depots, List<Client> clients)
    {
        this.depots = depots;
        this.clients = clients;
        generateTravelTimes();
    }

    /**
     * Returns an array of all vehicles in all depots.
     *
     * @return the vehicle array
     */
    public Vehicle[] getVehicles()
    {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots)
        {
            allVehicles.addAll(Arrays.asList(depot.getVehicles()));
        }
        return allVehicles.toArray(new Vehicle[0]);
    }

    /**
     * Generates random travel times between depots and clients, and between clients.
     */

    private void generateTravelTimes()
    {
        Random random = new Random();
        depotToClientTimes = new int[depots.size()][clients.size()];
        clientToClientTimes = new int[clients.size()][clients.size()];

        for (int i = 0; i < depots.size(); i++)
        {
            for (int j = 0; j < clients.size(); j++)
            {
                depotToClientTimes[i][j] = random.nextInt(120);
            }
        }

        for (int i = 0; i < clients.size(); i++)
        {
            for (int j = 0; j < clients.size(); j++)
            {
                if (i != j)
                {
                    clientToClientTimes[i][j] = random.nextInt(120);
                }
            }
        }

//        System.out.println("Depot to client travel times:");
//        for (int i = 0; i < depots.size(); i++)
//        {
//            for (int j = 0; j < clients.size(); j++)
//            {
//                System.out.print(depotToClientTimes[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("Client to client travel times:");
//        for (int i = 0; i < clients.size(); i++)
//        {
//            for (int j = 0; j < clients.size(); j++)
//            {
//                System.out.print(clientToClientTimes[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    /**
     * Assigns clients to vehicles based on their visit start times and the travel times.
     * If a client cannot be assigned to a vehicle, a message is printed.
     * After all clients are assigned, each vehicle performs its tour.
     */
    public void assignClients()
    {
        clients.sort(Comparator.comparing(Client::getVisitStartTime));
        Map<Vehicle, LocalTime> vehicleTimes = new HashMap<>();

        for (Client client : clients)
        {
            Vehicle quickestVehicle = null;
            int shortestTime = Integer.MAX_VALUE;

            for (Depot depot : depots)
            {
                for (Vehicle vehicle : depot.getVehicles())
                {
                    if (!vehicle.isAvailable())
                    {
                        continue;
                    }

                    vehicleTimes.putIfAbsent(vehicle, LocalTime.of(9, 0));

                    // Check if the vehicle is already assigned a client whose visit end time is after the current client's visit start time
                    Client lastClient = vehicle.getLastClient();
                    if (lastClient != null && lastClient.getVisitEndTime().isAfter(client.getVisitStartTime()))
                    {
                        continue;
                    }

                    int travelTime;
                    if (lastClient == null)
                    {
                        // If the vehicle has no clients yet, consider the travel time from the depot to the client
                        int depotIndex = depots.indexOf(depot);
                        int clientIndex = clients.indexOf(client);
                        travelTime = depotToClientTimes[depotIndex][clientIndex];
                    }
                    else
                    {
                        // If the vehicle already has clients, consider the travel time from the last client to the next client
                        int lastClientIndex = clients.indexOf(lastClient);
                        int clientIndex = clients.indexOf(client);
                        travelTime = clientToClientTimes[lastClientIndex][clientIndex];
                    }

                    // Check if the vehicle can reach the client within the client's available time window
                    LocalTime arrivalTime = vehicleTimes.get(vehicle).plusMinutes(travelTime);
                    if (!arrivalTime.isBefore(client.getVisitEndTime()) || arrivalTime.isAfter(LocalTime.of(17, 0)))
                    {
                        continue;
                    }

                    if (travelTime < shortestTime)
                    {
                        quickestVehicle = vehicle;
                        shortestTime = travelTime;
                    }
                }
            }

            if (quickestVehicle != null)
            {
                quickestVehicle.assignClient(client);
                LocalTime newTime = vehicleTimes.get(quickestVehicle).plusMinutes(shortestTime);
                vehicleTimes.put(quickestVehicle, newTime);
                //System.out.println("Vehicle " + quickestVehicle.getId() + " current time: " + newTime);
            }
            else
            {
                // Handle the case where no vehicle could be assigned
                System.out.println("No vehicle could be assigned for " + client.getName());
            }
        }

        // Print the routes for each vehicle
        for (Depot depot : depots)
        {
            for (Vehicle vehicle : depot.getVehicles())
            {
                vehicle.performTour();
            }
        }
    }

}