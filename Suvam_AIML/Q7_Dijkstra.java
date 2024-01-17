/*
7. Given a state space graph, the task is to find the optimal path from the start node A to
goal node F using Dijkstra’s algorithm. Print the path with the cost.
 */

package Suvam_AIML;

import java.util.*;

public class Q7_Dijkstra {
    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 8, 0, 0},
            {2, 0, 6, 5, 0, 0},
            {0, 6, 0, 3, 1, 9},
            {8, 5, 3, 0, 2, 0},
            {0, 0, 1, 2, 0, 3},
            {0, 0, 9, 0, 3, 0}
        };
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F'};
        int start = 0;  // Node 'A'
        int goal = 5;   // Node 'F'

        dijkstra(graph, vertices, start, goal);
    }

    public static void dijkstra(int[][] graph, char[] vertices, int start, int goal) {
        int n = graph.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }

            if (dist[u] == Integer.MAX_VALUE) break;

            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        List<Character> path = new ArrayList<>();
        for (int v = goal; v != start; v = prev[v]) {
            path.add(vertices[v]);
        }
        path.add(vertices[start]);
        Collections.reverse(path);

        System.out.println("Path: " + path);
        System.out.println("Cost: " + dist[goal]);
    }
}


/*
 * Answer:
Path: [A, B, D, E, F]
Cost: 12
 */