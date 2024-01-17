package Suvam_AIML;

import java.util.*;

public class Q10_GraphColoring {
    private static final String[] COLORS = { "Blue", "Green", "Red", "Yellow" };

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("a", Arrays.asList("b", "a", "e", "g"));
        graph.put("b", Arrays.asList("a", "d", "c"));
        graph.put("c", Arrays.asList("d", "g", "b"));
        graph.put("d", Arrays.asList("e", "b", "c"));
        graph.put("e", Arrays.asList("a", "d", "f", "g"));
        graph.put("f", Arrays.asList("a", "g", "e"));

        Map<String, String> colorMap = new HashMap<>();
        for (String node : graph.keySet()) {
            colorMap.put(node, getColorForNode(graph, colorMap, node));
        }

        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String getColorForNode(Map<String, List<String>> graph, Map<String, String> colorMap, String node) {
        for (String color : COLORS) {
            if (neighborsAreNotColoredWith(graph, colorMap, node, color)) {
                return color;
            }
        }
        throw new IllegalArgumentException("No valid color found for node " + node);
    }

    private static boolean neighborsAreNotColoredWith(Map<String, List<String>> graph, Map<String, String> colorMap,
            String node, String color) {
        for (String neighbor : graph.get(node)) {
            String neighborColor = colorMap.get(neighbor);
            if (color.equals(neighborColor)) {
                return false;
            }
        }
        return true;
    }
}

/*
 * Answer:
 * a: Blue
 * b: Green
 * c: Blue
 * d: Red
 * e: Green
 * f: Red
 */
