package VehicleRoutingProblem;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
public class Problem
{
    private List<Depot> depots;
    private List<Client> clients;
    private int[][] depotToClientTimes;
    private int[][] clientToClientTimes;

    public Problem(List<Depot> depots, List<Client> clients) {
        this.depots = depots;
        this.clients = clients;
        generateTravelTimes();
    }
    public Vehicle[] getVehicles()
    {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots)
        {
            allVehicles.addAll(Arrays.asList(depot.getVehicles()));
        }
        return allVehicles.toArray(new Vehicle[0]);
    }

    private void generateTravelTimes() {
        Random random = new Random();
        depotToClientTimes = new int[depots.size()][clients.size()];
        clientToClientTimes = new int[clients.size()][clients.size()];

        for (int i = 0; i < depots.size(); i++) {
            for (int j = 0; j < clients.size(); j++) {
                depotToClientTimes[i][j] = random.nextInt(120);
            }
        }

        for (int i = 0; i < clients.size(); i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (i != j) {
                    clientToClientTimes[i][j] = random.nextInt(120);
                }
            }
        }
    }

    public void assignClients() {

        // Sort the clients by the earliest start time
        clients.sort(Comparator.comparing(Client::getVisitStartTime));

        for (Client client : clients) {
            Vehicle quickestVehicle = null;
            int shortestTime = Integer.MAX_VALUE;

            for (Depot depot : depots) {
                for (Vehicle vehicle : depot.getVehicles()) {
                    if (!vehicle.isAvailable()) {
                        continue;
                    }

                    // Check if the vehicle is already assigned a client whose visit end time is after the current client's visit start time
                    Client lastClient = vehicle.getLastClient();
                    if (lastClient != null && lastClient.getVisitEndTime().isAfter(client.getVisitStartTime())) {
                        continue;
                    }

                    int travelTime;
                    if (lastClient == null) {
                        // If the vehicle has no clients yet, consider the travel time from the depot to the client
                        int depotIndex = depots.indexOf(depot);
                        int clientIndex = clients.indexOf(client);
                        travelTime = depotToClientTimes[depotIndex][clientIndex];
                    } else {
                        // If the vehicle already has clients, consider the travel time from the last client to the next client
                        int lastClientIndex = clients.indexOf(lastClient);
                        int clientIndex = clients.indexOf(client);
                        travelTime = clientToClientTimes[lastClientIndex][clientIndex];
                    }

                    if (travelTime < shortestTime) {
                        quickestVehicle = vehicle;
                        shortestTime = travelTime;
                    }
                }
            }

            if (quickestVehicle != null) {
                quickestVehicle.assignClient(client);
            } else {
                // Handle the case where no vehicle could be assigned
                System.out.println("No vehicle could be assigned for client " + client.getName());
            }
        }
    }

}
