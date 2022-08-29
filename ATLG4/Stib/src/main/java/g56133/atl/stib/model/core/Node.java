package g56133.atl.stib.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Node {
    
    private String name;
    
    private List<Integer> lines;
    
    private List<Node> shortestPath = new LinkedList<>();
    
    private Integer distance = Integer.MAX_VALUE;
    
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    
    public Node(String name, int line) {
        this.name = name;
        this.lines = new ArrayList<>();
        this.lines.add(line);
    }
    
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
    
    public void addLine(int line) {
        this.lines.add(line);
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }
    
    public Integer getDistance() {
        return distance;
    }
    
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    
    public List<Integer> getLines() {
        return this.lines;
    }
    
    public String getLinesToString() {
        return this.lines.toString();
    }
}
