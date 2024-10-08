package dsa.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class DijkstrasIn2d
{
    // A pair to hold the distance and the coordinates
    static class Pair {
        int distance;
        int x, y;

        Pair(int distance, int x, int y) {
            this.distance = distance;
            this.x = x;
            this.y = y;
        }
    }

    // Directions for moving in 8 possible directions
    private static final int[][] directions = {
            {1, 1}, {0, 1}, {1, 0}, {0, -1},
            {-1, 0}, {-1, -1}, {1, -1}, {-1, 1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Return -1 if the starting point is blocked or if the matrix is empty
        if (m == 0 || n == 0 || grid[0][0] != 0) {
            return -1;
        }

        // Check if the position is safe to visit
        Predicate<int[]> isSafe = (coords) -> coords[0] >= 0 && coords[0] < m && coords[1] >= 0 && coords[1] < n;

        // Result distance array initialized to maximum values
        int[][] result = new int[m][n];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Min-Heap (priority queue) to select the node with the smallest distance
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        pq.add(new Pair(0, 0, 0)); // Starting point with distance 0
        result[0][0] = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int d = current.distance;
            int x = current.x;
            int y = current.y;

            // Explore all possible directions
            for (int[] dir : directions) {
                int x_ = x + dir[0];
                int y_ = y + dir[1];
                int dist = 1; // Distance to move to adjacent cell

                // Check if the new position is within bounds and is free
                if (isSafe.test(new int[]{x_, y_}) && grid[x_][y_] == 0 && d + dist < result[x_][y_]) {
                    // Update the result distance
                    result[x_][y_] = d + dist;
                    // Add the new position to the priority queue
                    pq.add(new Pair(d + dist, x_, y_));
                    // Mark the cell as visited by setting it to 1
                    grid[x_][y_] = 1; // Mark as visited
                }
            }
        }

        // Return -1 if the destination is unreachable
        if (result[m - 1][n - 1] == Integer.MAX_VALUE) {
            return -1;
        }

        // Return the distance of the bottom-right cell plus 1 for the number of steps
        return result[m - 1][n - 1] + 1;
    }
}