package dsa.graphs;

import java.util.*;

class PrimMSTPattern {
    // Function to find the sum of weights of edges of the Minimum Spanning Tree.
    public int spanningTree(int V, List<List<Integer>>[] adj) {
        // Min-Heap (Priority Queue) to store the edge weights and nodes
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Start from the first node (0)
        pq.offer(new int[]{0, 0}); // {weight, node}

        boolean[] inMST = new boolean[V]; // To track nodes included in the MST
        int sum = 0; // Variable to store the sum of weights

        while (!pq.isEmpty()) {
            // Get the edge with the minimum weight
            int[] p = pq.poll();
            int wt = p[0]; // Weight of the edge
            int node = p[1]; // Current node

            // If the node is already included in the MST, continue
            if (inMST[node]) {
                continue;
            }

            // Include this node in the MST
            inMST[node] = true;
            sum += wt; // Add the weight to the sum

            // Explore the neighbors of the current node
            for (List<Integer> tmp : adj[node]) {
                int neighbor = tmp.get(0); // Neighbor node
                int neighborWt = tmp.get(1); // Weight of the edge to the neighbor

                // If the neighbor is not included in the MST, add it to the priority queue
                if (!inMST[neighbor]) {
                    pq.offer(new int[]{neighborWt, neighbor});
                }
            }
        }

        return sum; // Return the total weight of the MST
    }
}
