package dsa.solved;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathInMatrix0
{
    public int shortestPathBinaryMatrix(int[][] grid) {
        // Directions: down-right, down, right, up, left, up-left, down-left, up-right
        int[][] dirs = {{1, 1}, {0, 1}, {1, 0}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        // Check for initial conditions
        if (m == 0 || n == 0 || grid[0][0] != 0) {
            return -1;
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(0, 0)); // Use List<Integer> for the queue
        grid[0][0] = 1; // Mark the starting point as visited

        int level = 0;

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                // Remove current position from queue
                List<Integer> current = queue.poll();
                int x = current.get(0);
                int y = current.get(1);

                // Check if we've reached the target
                if (x == m - 1 && y == n - 1) {
                    return level + 1; // Return the number of levels (steps)
                }

                // Check all possible directions
                for (int[] dir : dirs) {
                    int x_ = x + dir[0];
                    int y_ = y + dir[1];

                    // Check if the new position is within bounds and not visited
                    if (x_ >= 0 && x_ < m && y_ >= 0 && y_ < n && grid[x_][y_] == 0) {
                        queue.add(Arrays.asList(x_, y_)); // Add new position to queue
                        grid[x_][y_] = 1; // Mark as visited
                    }
                }
            }
            level++; // Increment the level after processing the current level
        }
        return -1; // If the target is unreachable
    }

    //using dijiktras in 2 D matrix

    public int shortestPathBinaryMatrixUsingDij(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        int[][] dirs = {{1, 1}, {0, 1}, {1, 0}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            for (int[] dir : dirs) {
                int x_ = x + dir[0];
                int y_ = y + dir[1];
                if (x_ >= 0 && x_ < m && y_ >= 0 && y_ < n && grid[x_][y_] == 0) {
                    if (dist[x_][y_] > dist[x][y] + 1) {
                        dist[x_][y_] = dist[x][y] + 1;
                        queue.add(new int[]{x_, y_});
                    }
                }
            }
        }
        return dist[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist[m - 1][n - 1];
    }
}
