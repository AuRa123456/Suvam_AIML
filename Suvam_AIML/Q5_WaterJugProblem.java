/*
5. Given two water jugs with capacities X and Y litres. Initially, both the jugs are empty. Also given
that there is an infinite amount of water available. The jugs do not have markings to measure. The
task is to determine whether it is possible to measure Z litres of water using both the jugs. And if
true, print the possible steps alongwith the rule applied for that step. Solve for X = 4, Y = 3, Z = 2.
*/

package Suvam_AIML;

import java.util.*;

public class Q5_WaterJugProblem {
    private static int CAPACITY_X; // Capacity of Jug1
    private static int CAPACITY_Y; // Capacity of Jug2
    private static int GOAL; // Desired amount of water

    static class State {
        int x, y;

        State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity of Jug1: ");
        CAPACITY_X = scanner.nextInt();
        System.out.print("Enter the capacity of Jug2: ");
        CAPACITY_Y = scanner.nextInt();
        System.out.print("Enter the desired amount of water: ");
        GOAL = scanner.nextInt();

        bfs();
        scanner.close();
    }

    private static void bfs() {
        boolean[][] visited = new boolean[CAPACITY_X + 1][CAPACITY_Y + 1];
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0));

        while (!queue.isEmpty()) {
            State s = queue.poll();
            visited[s.x][s.y] = true;

            if (s.x == GOAL) {
                System.out.println("Found solution: (" + s.x + ", " + s.y + ")");
                return;
            }

            // Fill Jug1
            if (s.x < CAPACITY_X && !visited[CAPACITY_X][s.y]) {
                System.out.println("Applying rule: Fill Jug1. Current state: (" + CAPACITY_X + ", " + s.y + ")");
                queue.add(new State(CAPACITY_X, s.y));
            }

            // Fill Jug2
            if (s.y < CAPACITY_Y && !visited[s.x][CAPACITY_Y]) {
                System.out.println("Applying rule: Fill Jug2. Current state: (" + s.x + ", " + CAPACITY_Y + ")");
                queue.add(new State(s.x, CAPACITY_Y));
            }

            // Empty Jug1
            if (s.x > 0 && !visited[0][s.y]) {
                System.out.println("Applying rule: Empty Jug1. Current state: (0, " + s.y + ")");
                queue.add(new State(0, s.y));
            }

            // Empty Jug2
            if (s.y > 0 && !visited[s.x][0]) {
                System.out.println("Applying rule: Empty Jug2. Current state: (" + s.x + ", 0)");
                queue.add(new State(s.x, 0));
            }

            // Pour from Jug1 to Jug2
            if (s.x > 0 && s.y < CAPACITY_Y) {
                int pourAmount = Math.min(s.x, CAPACITY_Y - s.y);
                if (!visited[s.x - pourAmount][s.y + pourAmount]) {
                    System.out.println("Applying rule: Pour from Jug1 to Jug2. Current state: (" + (s.x - pourAmount) + ", " + (s.y + pourAmount) + ")");
                    queue.add(new State(s.x - pourAmount, s.y + pourAmount));
                }
            }

            // Pour from Jug2 to Jug1
            if (s.y > 0 && s.x < CAPACITY_X) {
                int pourAmount = Math.min(s.y, CAPACITY_X - s.x);
                if (!visited[s.x + pourAmount][s.y - pourAmount]) {
                    System.out.println("Applying rule: Pour from Jug2 to Jug1. Current state: (" + (s.x + pourAmount) + ", " + (s.y - pourAmount) + ")");
                    queue.add(new State(s.x + pourAmount, s.y - pourAmount));
                }
            }
        }

        System.out.println("No solution found.");
    }
}

/* Answer:
Enter the capacity of Jug1: 4
Enter the capacity of Jug2: 3
Enter the desired amount of water: 2
Applying rule: Fill Jug1. Current state: (4, 0)
Applying rule: Fill Jug2. Current state: (0, 3)
Applying rule: Fill Jug2. Current state: (4, 3)
Applying rule: Pour from Jug1 to Jug2. Current state: (1, 3)
Applying rule: Fill Jug1. Current state: (4, 3)
Applying rule: Pour from Jug2 to Jug1. Current state: (3, 0)
Applying rule: Empty Jug2. Current state: (1, 0)
Applying rule: Fill Jug2. Current state: (3, 3)
Applying rule: Pour from Jug1 to Jug2. Current state: (0, 1)
Applying rule: Pour from Jug2 to Jug1. Current state: (4, 2)
Applying rule: Fill Jug1. Current state: (4, 1)
Applying rule: Empty Jug1. Current state: (0, 2)
Applying rule: Pour from Jug1 to Jug2. Current state: (2, 3)
Applying rule: Pour from Jug2 to Jug1. Current state: (2, 0)
Found solution: (2, 3)

2nd Attempt:
Enter the capacity of Jug1: 5
Enter the capacity of Jug2: 3
Enter the desired amount of water: 4
Applying rule: Fill Jug1. Current state: (5, 0)
Applying rule: Fill Jug2. Current state: (0, 3)
Applying rule: Fill Jug2. Current state: (5, 3)
Applying rule: Pour from Jug1 to Jug2. Current state: (2, 3)
Applying rule: Fill Jug1. Current state: (5, 3)
Applying rule: Pour from Jug2 to Jug1. Current state: (3, 0)
Applying rule: Empty Jug2. Current state: (2, 0)
Applying rule: Fill Jug2. Current state: (3, 3)
Applying rule: Pour from Jug1 to Jug2. Current state: (0, 2)
Applying rule: Pour from Jug2 to Jug1. Current state: (5, 1)
Applying rule: Fill Jug1. Current state: (5, 2)
Applying rule: Empty Jug1. Current state: (0, 1)
Applying rule: Pour from Jug1 to Jug2. Current state: (4, 3)
Applying rule: Pour from Jug2 to Jug1. Current state: (1, 0)
Found solution: (4, 3)
 */