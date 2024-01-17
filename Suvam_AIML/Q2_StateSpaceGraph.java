/*
2. Given the moveGen function in the table below, and the corresponding state space graph, the task is to find a path from the start node S to goal node G.
moveGen
S → (A B C)
A → (E)
B → (D)
C → (D G)
D → (E G)
E → (D G)
G → (D E)
 */

package Suvam_AIML;

import java.util.*;

public class Q2_StateSpaceGraph {

    public static void main(String[] args) {
        // Define the state space graph(HashMap Syntax:(key,value))
        Map<String, String[]> graph = new HashMap<>();
        graph.put("S", new String[] { "A", "B", "C" });
        graph.put("A", new String[] { "E" });
        graph.put("B", new String[] { "D" });
        graph.put("C", new String[] { "D", "G" });
        graph.put("D", new String[] { "E", "G" });
        graph.put("E", new String[] { "D", "G" });
        graph.put("G", new String[] { "D", "E" });

        // Test the moveGen function
        moveGen(graph, "S");
        moveGen(graph, "A");
        moveGen(graph, "B");
        moveGen(graph, "C");
        moveGen(graph, "D");
        moveGen(graph, "E");
        moveGen(graph, "G");
    }

    public static void moveGen(Map<String, String[]> graph, String node) {
        if (graph.containsKey(node)) {
            Set<String> visited = new HashSet<>();
            List<String> path = new ArrayList<>();
            path.add(node);
            visited.add(node);
            System.out.println("Next nodes from " + node + ": " + String.join(", ", graph.get(node)));
            findShortestPath(graph, node, "G", visited, path);
        } else {
            System.out.println("Node " + node + " not found in the graph.");
        }
    }

    public static void findShortestPath(Map<String, String[]> graph, String current, String goal,
            Set<String> visited, List<String> path) {
        if (current.equals(goal)) {
            System.out.println("Shortest path from " + path.get(0) + " to G: " + String.join(" -> ", path));
            return;
        }

        if (graph.containsKey(current)) {
            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    path.add(neighbor);
                    findShortestPath(graph, neighbor, goal, visited, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}

/*
 * Answer:
 * Next nodes from S: A, B, C
 * Shortest path from S to G: S -> A -> E -> D -> G
 * Next nodes from A: E
 * Shortest path from A to G: A -> E -> D -> G
 * Next nodes from B: D
 * Shortest path from B to G: B -> D -> E -> G
 * Next nodes from C: D, G
 * Shortest path from C to G: C -> D -> E -> G
 * Next nodes from D: E, G
 * Shortest path from D to G: D -> E -> G
 * Next nodes from E: D, G
 * Shortest path from E to G: E -> D -> G
 * Next nodes from G: D, E
 * Shortest path from G to G: G
 */