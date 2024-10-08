package dsa.graphs;

public class FloydWarshall
{
    public void shortestDistance(int[][] grid) {
        int n = grid.length;

        // Replace -1 with a large value (representing infinity)
        // if no -edge between them
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    grid[i][j] = 100000; // Using a large value to represent infinity
                }
            }
        }

        // Floyd-Warshall algorithm
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][via] + grid[via][j]);
                }
            }
        }

        // Replace large values back to -1 to indicate no path
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 100000) {
                    grid[i][j] = -1; // No path exists
                }
            }
        }
    }
}
