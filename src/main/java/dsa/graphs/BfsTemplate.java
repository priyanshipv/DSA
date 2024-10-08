package dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BfsTemplate
{
    public int maxDistance(int[][] grid)
    {
        Queue<int[]> q=new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(grid[i][j]==1)
                {
                    q.add(new int[]{i, j});
                }
        }
    }
        // Directions for moving in the grid: right, left, down, up
        int [][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int maxDistance = -1;
        while (!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                int[] curr = q.poll();
                // Explore all possible directions
                for(int[] dir: dirs)
                {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    // Check if the new position is within bounds and is water
                    if(x>=0 && x<m && y>=0 && y<n && grid[x][y]==0)
                    {
                        // Update the cell with the distance from the nearest land
                        grid[x][y] = grid[curr[0]][ curr[1]] + 1;
                        // Add the new water cell to the queue
                        q.add(new int[]{x, y});
                        // Update the maximum distance found so far
                        maxDistance = Math.max(maxDistance, grid[x][y]);
                    }
                }
            }
            }
        return maxDistance;
        }
}
