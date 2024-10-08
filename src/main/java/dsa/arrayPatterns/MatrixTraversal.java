package dsa.arrayPatterns;

import java.util.ArrayDeque;
import java.util.Deque;
//O(n)
public class MatrixTraversal
{
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length ||
                j >= grid[0].length || visited[i][j]) return;
        visited[i][j] = true;
        dfs(grid, i + 1, j, visited);
        dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited);
        dfs(grid, i, j - 1, visited);
    }

    int[][] dirs = { {0,1}, {0,-1}, {1,0}, {-1,0} };
    void bfs(char[][] grid, int _i, int _j) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        q.addLast(new int[] {_i, _j});
        visited[_i][_j] = true;
        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            for (int[] dir : dirs) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];
                if (i < 0 || j < 0 || i >= grid.length ||
                        j >= grid[0].length || visited[i][j]) continue;
                visited[i][j] = true;
                q.addLast(new int[] {i, j});
            }
        }
    }
}
