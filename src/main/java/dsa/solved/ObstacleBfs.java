package dsa.solved;

import java.util.*;

class ObstacleBfs
{
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // Directions: down, right, up, left

        Queue<List<Integer>> queue = new LinkedList<>(); // Use List<Integer> for the queue

        // Starting point
        queue.add(Arrays.asList(0, 0, k)); // Start at (0, 0) with k obstacles

        // Initialize visited 3D array
        boolean[][][] visited = new boolean[m][n][k + 1]; // 3D array for visited

        visited[0][0][k] = true; // Mark the starting point as visited

        int steps = 0;

        // Starting BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                List<Integer> temp = queue.poll();
                int curr_i = temp.get(0);
                int curr_j = temp.get(1);
                int obs = temp.get(2);

                // Check if current is the target
                if (curr_i == m - 1 && curr_j == n - 1) {
                    return steps; // Return the number of steps
                }

                // Move in all four directions
                for (int[] dir : dirs) {
                    int new_i = curr_i + dir[0];
                    int new_j = curr_j + dir[1];

                    // Check if we are going out of bounds
                    if (new_i < 0 || new_i >= m || new_j < 0 || new_j >= n) {
                        continue;
                    }

                    // Where I want to go
                    if (grid[new_i][new_j] == 0 && !visited[new_i][new_j][obs]) {
                        queue.add(Arrays.asList(new_i, new_j, obs)); // Add to queue
                        visited[new_i][new_j][obs] = true; // Mark as visited
                    }
                    else if (grid[new_i][new_j] == 1 && obs > 0 && !visited[new_i][new_j][obs - 1]) {
                        queue.add(Arrays.asList(new_i, new_j, obs - 1)); // Use one obstacle
                        visited[new_i][new_j][obs - 1] = true; // Mark as visited
                    }
                }
            }
            steps++; // Increment the number of steps after processing the current level
        }
        return -1; // If the target is unreachable
    }
}
