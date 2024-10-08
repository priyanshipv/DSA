package dsa.graphs;

import java.util.*;

class KruskalTemplate {
    // DSU (Disjoint Set Union) data structure to manage components
    private int[] parent;
    private int[] rank;

    // Method to find the root of a node with path compression
    private int find(int x) {
        if (x == parent[x]) {
            return x; // If x is the root, return x
        }
        // Path compression heuristic
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // Method to union two sets
    private void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        // If both nodes are in the same set, no need to union
        if (xParent == yParent) {
            return;
        }

        // Union by rank heuristic
        if (rank[xParent] > rank[yParent]) {
            parent[yParent] = xParent; // Attach y's root to x's root
        } else if (rank[xParent] < rank[yParent]) {
            parent[xParent] = yParent; // Attach x's root to y's root
        } else {
            parent[xParent] = yParent; // Attach x's root to y's root
            rank[yParent]++; // Increment the rank
        }
    }

    // Method to perform Kruskal's algorithm
    private int kruskal(List<int[]> edges) {
        int sum = 0; // Variable to store the sum of weights
        // Process each edge in increasing order of weight
        for (int[] edge : edges) {
            int u = edge[0]; // First vertex of the edge
            int v = edge[1]; // Second vertex of the edge
            int weight = edge[2]; // Weight of the edge

            // Find the roots of both vertices
            int parentU = find(u);
            int parentV = find(v);

            // If they are in different components, union them
            if (parentU != parentV) {
                union(u, v);
                sum += weight; // Add the weight to the total sum
            }
        }

        // To check if the MST is valid, we can count the edges in the MST.
        // An MST will have exactly V - 1 edges if it is a connected graph.
        return sum; // Return the total weight of the MST
    }

    // Function to find the sum of weights of edges of the Minimum Spanning Tree
    public int spanningTree(int V, List<List<int[]>> adj) {
        parent = new int[V]; // Initialize parent array
        rank = new int[V]; // Initialize rank array

        // Initialize each node as its own parent (self loop)
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        List<int[]> edges = new ArrayList<>(); // To store all edges

        // Build the list of edges from the adjacency list
        for (int i = 0; i < V; i++) {
            for (int[] temp : adj.get(i)) {
                int u = i; // Current node
                int v = temp[0]; // Neighbor node
                int weight = temp[1]; // Weight of the edge

                // Add the edge to the edges list
                edges.add(new int[]{u, v, weight});
            }
        }

        // Sort the edges based on their weights
        edges.sort(Comparator.comparingInt(edge -> edge[2]));

        // Call Kruskal's algorithm to calculate the total weight of the MST
        return kruskal(edges);
    }
}
