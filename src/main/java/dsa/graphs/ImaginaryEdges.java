package dsa.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImaginaryEdges
{
    int count = 0;
    public int minReorder(int n, int[][] connections) {
        // Initialize the adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();

        // Build the adjacency list
        for (int[] v : connections) {
            int a = v[0];
            int b = v[1];

            // If node 'a' is not in the adjacency list, initialize it
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());

            // Add the directed edge (a -> b) with sign 1 and (b -> a) with sign 0
            adj.get(a).add(new int[]{b, 1}); // Edge a -> b needs reordering
            adj.get(b).add(new int[]{a, 0}); // Edge b -> a does not need reordering
        }

        // Start DFS traversal from node 0
        dfs(0, -1, adj);

        return count;
    }

    // DFS function to traverse the graph
    void dfs(int u, int parent, Map<Integer, List<int[]>> adj) {
        // Visit all neighbors of node 'u'
        for (int[] p : adj.get(u)) {
            int v = p[0]; // neighbor node
            int check = p[1]; // direction of the edge (1: needs reorder, 0: no reorder)

            // If the neighbor is not the parent (to avoid traversing back)
            if (v != parent) {
                // If the edge direction is wrong, increment the count
                if (check == 1) {
                    count++;
                }

                // Recur for the child node
                dfs(v, u, adj);
            }
        }
    }
}
