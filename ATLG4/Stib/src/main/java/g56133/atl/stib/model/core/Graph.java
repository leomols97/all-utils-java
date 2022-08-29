package g56133.atl.stib.model.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Graph {
    
    private Map<Integer, Node> graph;
    
    public Graph() {
        this.graph = new HashMap<>();
    }
    
    public void addNode(int key, Node n) {
        graph.put(key, n);
    }
    
    public boolean contain(int key) {
        return this.graph.containsKey(key);
    }
    
    public Node getNode(int idStation) {
        return this.graph.get(idStation);
    }
    
    public Map<Integer, Node> getGraph() {
        return this.graph;
    }
}
