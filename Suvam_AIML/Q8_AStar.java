/*
8. Given a state space graph, the task is to find the optimal path from the start node S to goal node F using A* algorithm. 
*/

package Suvam_AIML;

import java.util.*;

class Node implements Comparable<Node> {
    public final String name;
    public Edge[] adjacencies;
    public double shortestDistance = Double.POSITIVE_INFINITY;
    public Node parent;
    public double heuristicValue = 0.0;

    public Node(String val, double heuristic) {
        name = val;
        heuristicValue = heuristic;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Node other) {
        return Double.compare(shortestDistance + heuristicValue, other.shortestDistance + other.heuristicValue);
    }
}

class Edge {
    public final Node target;
    public final double weight;

    public Edge(Node targetNode, double costVal) {
        target = targetNode;
        weight = costVal;
    }
}

public class Q8_AStar {
    public static void computePaths(Node source) {
        source.shortestDistance = 0;
        PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
        nodeQueue.add(source);

        while (!nodeQueue.isEmpty()) {
            Node u = nodeQueue.poll();

            for (Edge e : u.adjacencies) {
                Node v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.shortestDistance + weight;
                if (distanceThroughU < v.shortestDistance) {
                    nodeQueue.remove(v);
                    v.shortestDistance = distanceThroughU;
                    v.parent = u;
                    nodeQueue.add(v);
                }
            }
        }
    }

    public static List<Node> getShortestPathTo(Node target) {
        List<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.parent)
            path.add(node);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Node n0 = new Node("S", 10);
        Node n1 = new Node("A", 7); 
        Node n2 = new Node("B", 8); 
        Node n3 = new Node("C", 6); 
        Node n4 = new Node("D", 4); 
        Node n5 = new Node("E", 11);
        Node n6 = new Node("F", 0); 
        

        n0.adjacencies = new Edge[] { new Edge(n1, 4), new Edge(n2, 3) };
        n1.adjacencies = new Edge[] { new Edge(n0, 4), new Edge(n3, 7), new Edge(n4, 10) };
        n2.adjacencies = new Edge[] {new Edge(n4, 12), new Edge(n5, 5) };
        n3.adjacencies = new Edge[] { new Edge(n1, 7), new Edge(n4, 2) };
        n4.adjacencies = new Edge[] { new Edge(n1, 10), new Edge(n2, 12), new Edge(n3, 2), new Edge(n6, 5) };
        n5.adjacencies = new Edge[] { new Edge(n2, 5), new Edge(n6, 16) };
        n6.adjacencies = new Edge[] { new Edge(n4, 5), new Edge(n5, 16) };

        Node[] nodes = { n0, n1, n2, n3, n4, n5, n6 };

        computePaths(n0);
        for (Node n : nodes) {
            System.out.println("Distance to " + n + ": " + n.shortestDistance);
            List<Node> path = getShortestPathTo(n);
            System.out.println("Path: " + path);
        }
    }
}

/*
 * Answer:
Distance to S: 0.0
Path: [S]
Distance to A: 4.0 
Path: [S, A]       
Distance to B: 3.0 
Path: [S, B]       
Distance to C: 11.0
Path: [S, A, C]    
Distance to D: 13.0
Path: [S, A, C, D] 
Distance to E: 8.0 
Path: [S, B, E]      
Distance to F: 18.0  
Path: [S, A, C, D, F]
 */