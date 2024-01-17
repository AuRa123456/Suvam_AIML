/*
6. The traveling salesman problems abide by a salesman and a set of cities. The salesman has to visit
every one of the cities starting from a certain one (e.g., the hometown) and to return to the same city.
The challenge of the problem is that the traveling salesman needs to minimize the total length of the
trip. Given a graph with A as the start state from where the traveler starts its journey. Find and print
the path and the cost associated with each step with total cost.
 */

package Suvam_AIML;

import java.util.*;

public class Q6_TravelingSalesman {

    private static Map<String, Map<String, Integer>> graph = new HashMap<>();
    private static List<String> optimalPath = new ArrayList<>();
    private static int optimalPathCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Build the graph
        addEdge("A", "B", 20);
        addEdge("A", "C", 40);
        addEdge("A", "D", 22);
        addEdge("B", "C", 13);
        addEdge("B", "D", 30);
        addEdge("C", "D", 12);

        // Solve the Traveling Salesman Problem
        travelingSalesman("A");

        // Print the optimal path and its cost
        printOptimalPath();
    }

    private static void addEdge(String source, String destination, int cost) {
        graph.computeIfAbsent(source, k -> new HashMap<>()).put(destination, cost);
        graph.computeIfAbsent(destination, k -> new HashMap<>()).put(source, cost);
    }

    private static void travelingSalesman(String startNode) {
        List<String> cities = new ArrayList<>(graph.keySet());
        List<String> currentPath = new ArrayList<>();
        currentPath.add(startNode);

        Set<String> visited = new HashSet<>();
        visited.add(startNode);
        tspRec(startNode, cities, visited, currentPath, 0);
        optimalPath.add(startNode);
        optimalPathCost += getEdgeCost(optimalPath.get(optimalPath.size() - 2), startNode);
    }

    private static void tspRec(String currentNode, List<String> cities, Set<String> visited, List<String> currentPath,
            int currentCost) {
        if (visited.size() == cities.size()) {
            int cost = currentCost + getEdgeCost(currentNode, currentPath.get(0));
            if (cost < optimalPathCost) {
                optimalPath = new ArrayList<>(currentPath);
                optimalPathCost = cost;
            }
            return;
        }
        for (String neighbor : graph.get(currentNode).keySet()) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                currentPath.add(neighbor);
                int edgeCost = getEdgeCost(currentNode, neighbor);
                if (edgeCost != -1) {
                    tspRec(neighbor, cities, visited, currentPath, currentCost + edgeCost);
                }
                visited.remove(neighbor);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static int getEdgeCost(String source, String destination) {
        Map<String, Integer> edgeMap = graph.get(source);
        return edgeMap != null ? edgeMap.getOrDefault(destination, -1) : -1;
    }

    private static void printOptimalPath() {
        System.out.println("Optimal Path:");
        for (int i = 0; i < optimalPath.size() - 1; i++) {
            String start = optimalPath.get(i);
            String end = optimalPath.get(i + 1);
            int cost = getEdgeCost(start, end);
            System.out.println(start + " -> " + end + " Cost: " + cost);
        }
        System.out.println("Total Cost: " + optimalPathCost);
    }
}

/*
 * Answer:
 * Optimal Path:
 * A -> B Cost: 20
 * B -> C Cost: 13
 * C -> D Cost: 12
 * D -> A Cost: 22
 * Total Cost: 89
 */