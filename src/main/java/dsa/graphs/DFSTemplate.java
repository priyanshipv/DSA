package dsa.graphs;
//Base Condition like check the row,column boundary(i>total row or j> total column etc.).
//Check in all 4 adjacent neighbors, like for check in upper side(row-1,col), down(row+1,col), left(row,col-1), right(row,col+1).
public class DFSTemplate
{
    public void dfs(char[][] grid, int i, int j)
    {
        //base case row=grid.length, total column= grid[0].length
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;
        //Make the cell visited
        grid[i][j] = 'X';
        //Check in all 4 adjacent neighbors
        dfs(grid, i + 1, j);//check in bottom side
        dfs(grid, i - 1, j);// check in upper side
        dfs(grid, i, j + 1);//check in right side
        dfs(grid, i, j - 1); //check in left side

    }

    //Number of Islands
    //Capture all 0 surrounded region
    //Max Area of Island



    //walls and gates

}
