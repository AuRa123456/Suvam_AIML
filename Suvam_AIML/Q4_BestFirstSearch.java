/*
4. Apply Best First Search over the given state space graph to find the optimal path by employing the
heuristic value given for each node:
Heuristic value:
A → G = 40
B → G = 32
C → G = 25
D → G = 35
E → G = 19
F → G = 17
G → G = 0
H → G = 10
 */
package Suvam_AIML;
import java.util.*;


public class Q4_BestFirstSearch {
    private static class Node implements Comparable<Node> {
        String id;
        int heuristic;
        List<String> path;

        public Node(String id, int heuristic, List<String> path) {
            this.id = id;
            this.heuristic = heuristic;
            this.path = path;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.heuristic, other.heuristic);
        }
    }

    public static List<String> bestFirstSearch(HashMap<String, List<String>> graph, HashMap<String, Integer> heuristic, String start, String goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, heuristic.get(start), Arrays.asList(start)));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.id.equals(goal)) {
                return current.path;
            }

            List<String> neighbors = graph.get(current.id);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    List<String> newPath = new ArrayList<>(current.path);
                    newPath.add(neighbor);
                    queue.add(new Node(neighbor, heuristic.get(neighbor), newPath));
                }
            }
        }

        return null;  // return null if no path is found
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C", "D"));
        graph.put("B", Arrays.asList("E"));
        graph.put("C", Arrays.asList("E", "D", "F"));
        graph.put("D", Arrays.asList("F"));
        graph.put("E", Arrays.asList("H"));
        graph.put("F", Arrays.asList("G"));
        graph.put("H", Arrays.asList("G"));

        HashMap<String, Integer> heuristic = new HashMap<>();
        heuristic.put("A", 40);
        heuristic.put("B", 32);
        heuristic.put("C", 25);
        heuristic.put("D", 35);
        heuristic.put("E", 19);
        heuristic.put("F", 17);
        heuristic.put("G", 0);
        heuristic.put("H", 10);

        List<String> path = bestFirstSearch(graph, heuristic, "A", "G");
        if (path != null) {
            System.out.println("Path: " + String.join(" -> ", path));
        } else {
            System.out.println("No path found.");
        }
    }
}


/*
 * Answer:
 * Path: A -> C -> F -> G
 */
