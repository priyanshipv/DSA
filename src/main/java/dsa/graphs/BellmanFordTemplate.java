package dsa.graphs;

import java.util.Arrays;

//Bellman Ford is a single source shortest path algorithm that works for graphs with negative edge weights, but it does not work for graphs with negative cycles.
//The algorithm works by relaxing all the edges of the graph V-1 times, where V is the number of vertices in the graph.
//The algorithm is as follows:
//1. Initialize the distance of the source node to 0 and the distance of all other nodes to infinity.
//2. Relax all the edges of the graph V-1 times.
//3. For each edge (u, v) with weight w, update the distance of v if the distance of u + w is smaller than the current distance of v.
//4. Repeat step 3 V-1 times.
//5. If the distance of any node is updated in the Vth iteration, then the graph contains a negative cycle.
//The algorithm can be implemented using an adjacency list to store the graph and an array to store the distances of the nodes.
//The algorithm is guaranteed to find the shortest path from the source node to all other nodes in the graph if the graph does not contain negative cycles.
//The algorithm can be modified to detect negative cycles by running the relaxation step one more time after the V-1 iterations.
//The algorithm can also be used to find the shortest path between two specific nodes by stopping the algorithm when the destination node is relaxed.
//(V-1)e IS THE TIME COMPLEXITY
// ORDER DOESN'T MATTER
//if there is a negative weight cycle, shortest distance cannot happen
public class BellmanFordTemplate
{
    public int isNegativeWeightCycle(int n, int[][] edges)
    {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        //
        for (int count = 0; count < n - 1; count++)
        {
            for (int[] edge : edges)
            {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v])
                {
                    distance[v] = distance[u] + w;
                }
            }
            //relax once more to check if there is a negative cycle
            for (int[] edge : edges)
            {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v])
                {
                    return -1;
                }
            }
        }
        return 1;
    }
}
