/*
3. Given a state space graph, the task is to find shortest path from the start node S to goal node G using DFS algorithm.
 */
package Suvam_AIML;

import java.util.*;

public class Q3_DFSShortestPath {

    private static Map<String, Map<String, Integer>> graph = new HashMap<>();
    private static List<String> shortestPath = new ArrayList<>();
    private static int shortestPathLength = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Build the state space graph
        addEdge("S", "A", 11);
        addEdge("S", "C", 14);
        addEdge("S", "B", 7);
        addEdge("A", "D", 15);
        addEdge("C", "D", 8);
        addEdge("C", "E", 10);
        addEdge("B", "C", 18);
        addEdge("B", "E", 25);
        addEdge("D", "F", 9);
        addEdge("E", "G", 20);
        addEdge("F", "G", 10);

        // Find the shortest path from S to G using DFS
        dfsShortestPath("S", "G");
        // Print the shortest path and its length
        System.out.println("Shortest Path: " + String.join(" -> ", shortestPath));
        System.out.println("Shortest Path Length: " + shortestPathLength);
    }

    private static void addEdge(String source, String destination, int weight) {
        graph.computeIfAbsent(source, k -> new HashMap<>()).put(destination, weight);
    }

    private static void dfsShortestPath(String currentNode, String goalNode) {
        dfs(currentNode, goalNode, new ArrayList<>(), 0);
    }

    private static void dfs(String currentNode, String goalNode, List<String> currentPath, int currentPathLength) {
        currentPath.add(currentNode);
        if (currentNode.equals(goalNode) && currentPathLength < shortestPathLength) {
            shortestPath = new ArrayList<>(currentPath);
            shortestPathLength = currentPathLength;
        }
        Map<String, Integer> neighbors = graph.get(currentNode);
        if (neighbors != null) {
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String nextNode = neighbor.getKey();
                int edgeWeight = neighbor.getValue();
                if (!currentPath.contains(nextNode)) {
                    dfs(nextNode, goalNode, currentPath, currentPathLength + edgeWeight);
                }
            }
        }
        currentPath.remove(currentPath.size() - 1);
    }
}

/* Answer:
 * Shortest Path: S -> C -> D -> F -> G
 * Shortest Path Length: 41
 */
