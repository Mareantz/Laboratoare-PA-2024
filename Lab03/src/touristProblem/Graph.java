package touristProblem;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Graph
{
    private List<Node> nodes;
    private Map<Node, List<Node>> edges;

    public Graph()
    {
        nodes = new ArrayList<>();
        edges = new HashMap<>();
    }

    public void addNode(Node node)
    {
        nodes.add(node);
        edges.put(node, new ArrayList<>());
    }

    public void addEdge(Node node1, Node node2)
    {
        edges.get(node1).add(node2);
        edges.get(node2).add(node1);
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public List<Node> getEdges(Node node)
    {
        return edges.get(node);
    }

    public void connectSameTypeAttractions() {
        for (Node node1 : nodes) {
            for (Node node2 : nodes) {
                if (node1 != node2 && node1.getAttraction().getType().equals(node2.getAttraction().getType())) {
                    addEdge(node1, node2);
                }
            }
        }
    }
}
