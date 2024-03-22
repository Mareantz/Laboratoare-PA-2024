import touristProblem.Trip;
import touristProblem.Statue;
import touristProblem.Church;
import touristProblem.Visitable;
import touristProblem.Concert;
import touristProblem.TravelPlan;
import touristProblem.Attraction;
import touristProblem.TimeInterval;
import touristProblem.Graph;
import touristProblem.Node;
import touristProblem.Coloring;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args)
    {
        Map<LocalDate, TimeInterval> timetable1 = new HashMap<>();
        Map<LocalDate, TimeInterval> timetable2 = new HashMap<>();
        Map<LocalDate, TimeInterval> timetable3 = new HashMap<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(2);

        timetable1.put(startDate, new TimeInterval(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        timetable2.put(startDate.plusDays(1), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(18, 0)));
        timetable3.put(startDate.plusDays(2), new TimeInterval(LocalTime.of(11, 0), LocalTime.of(19, 0)));

        Statue eastStatue = new Statue("East Statue", timetable1);
        Statue northenStatue = new Statue("Northern Statue", timetable2);
        Church northernChurch = new Church("Northern Church", timetable3);
        Church middleChurch = new Church("Middle Church", timetable1);
        Church southChurch = new Church("South Church", timetable2);
        Concert middleConcert = new Concert("Middle Concert", timetable1, 50.0);
        Concert beachPleaseConcert = new Concert("Beach Please Concert", timetable2, 70.0);

        List<Attraction> attractions = new ArrayList<>();
        attractions.add(southChurch);
        attractions.add(eastStatue);
        attractions.add(northenStatue);
        attractions.add(northernChurch);
        attractions.add(middleConcert);
        attractions.add(beachPleaseConcert);
        attractions.add(middleChurch);

        Graph graph = new Graph();

        List<Integer> colorList = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());

        for (Attraction attraction : attractions)
        {
            Node node = new Node(attraction, colorList);
            graph.addNode(node);
        }

        for (Node node1 : graph.getNodes())
        {
            for (Node node2 : graph.getNodes())
            {
                if (node1.getAttraction().getClass() == node2.getAttraction().getClass() && node1 != node2)
                {
                    graph.addEdge(node1, node2);
                }
            }
        }



        graph.connectSameTypeAttractions();

        Coloring coloring = new Coloring();



        Trip trip = new Trip("Iasi", attractions);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);

        trip.displayVisitableNotPayable(LocalDate.now());

        coloring.colorGraphByDegree(graph, trip);
        System.out.println("Coloring by degree:");
        for (Node node : graph.getNodes())
        {
            System.out.println(node.getAttraction().getName() + " - Day: " + (node.getColor() == 0 ? "Not assigned" : "Day" + node.getColor()));
        }

        for (Node node : graph.getNodes())
        {
            node.setColor(0);
        }

        coloring.colorGraphByDSatur(graph, trip);
        System.out.println("Coloring by DSatur:");
        for (Node node : graph.getNodes())
        {
            System.out.println(node.getAttraction().getName() + " - Day: " + (node.getColor() == 0 ? "Not assigned" : "Day" + node.getColor()));
        }
    }
}