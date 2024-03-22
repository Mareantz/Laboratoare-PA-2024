package touristProblem;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Coloring
{
    public void colorGraphByDegree(Graph graph, Trip trip)
    {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        nodes.sort((n1, n2) -> graph.getEdges(n1).size() - graph.getEdges(n2).size());

        long days = trip.getStartDate().until(trip.getEndDate(), ChronoUnit.DAYS) + 1;

        for (Node node : nodes)
        {
            Set<Integer> usedColors = new HashSet<>();
            for (Node neighbor : graph.getEdges(node))
            {
                usedColors.add(neighbor.getColor());
            }

            for (int color = 1; color <= days; color++) {
                LocalDate visitDate = trip.getStartDate().plusDays(color - 1);
                if (!usedColors.contains(color) && node.getAttraction() instanceof Visitable) {
                    Map<LocalDate, TimeInterval> timetable = ((Visitable) node.getAttraction()).getTimetable();
                    if (timetable != null && timetable.containsKey(visitDate) && timetable.get(visitDate) != null) {
                        node.setColor(color);
                        break;
                    }
                }
            }
        }
    }

    public void colorGraphByDSatur(Graph graph, Trip trip)
    {
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        nodes.sort((n1, n2) -> degreeOfSaturation(n1, graph) - degreeOfSaturation(n2, graph));

        long days = trip.getStartDate().until(trip.getEndDate(), ChronoUnit.DAYS) + 1;

        for (Node node : nodes)
        {
            Set<Integer> usedColors = new HashSet<>();
            for (Node neighbor : graph.getEdges(node))
            {
                usedColors.add(neighbor.getColor());
            }

            for (int color = 1; color <= days; color++) {
                LocalDate visitDate = trip.getStartDate().plusDays(color - 1);
                if (!usedColors.contains(color) && node.getAttraction() instanceof Visitable) {
                    Map<LocalDate, TimeInterval> timetable = ((Visitable) node.getAttraction()).getTimetable();
                    if (timetable != null && timetable.containsKey(visitDate) && timetable.get(visitDate) != null) {
                        node.setColor(color);
                        break;
                    }
                }
            }
        }
    }

    private int degreeOfSaturation(Node node, Graph graph)
    {
        Set<Integer> usedColors = new HashSet<>();
        for (Node neighbor : graph.getEdges(node))
        {
            usedColors.add(neighbor.getColor());
        }
        return usedColors.size();
    }
}