package dsa.solved;

public class MinAreaRect
{
    public int minAreaRect(int[][] points) {
        int n = points.length;
        int minArea = Integer.MAX_VALUE;
        boolean[][] grid = new boolean[40001][40001];
        for(int[] point : points){
            grid[point[0]][point[1]] = true;
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(points[i][0] == points[j][0] || points[i][1] == points[j][1]){
                    continue;
                }
                if(grid[points[i][0]][points[j][1]] && grid[points[j][0]][points[i][1]]){
                    minArea = Math.min(minArea, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
