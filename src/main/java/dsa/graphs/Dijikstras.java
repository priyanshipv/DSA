package dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijikstras
{

    static class Pair
    {
        int node, dist;

        Pair(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }

    // Function to find the shortest distance from the source vertex `src` to all vertices
    static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int src)
    {
        // Priority queue to store the minimum distance node
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
        int[] dist = new int[v]; // Array to store distances

        // Initialize distances to infinity and the source distance to 0
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Add the source node to the priority queue
        minHeap.offer(new Pair(src, dist[src]));

        while (!minHeap.isEmpty())
        {
            // Get the node with the smallest distance
            Pair p = minHeap.poll();
            int node = p.node;
            int currDist = p.dist;

            // If the current distance is greater than the recorded distance, skip it
            if (currDist > dist[node])
            {
                continue;
            }

            // Iterate through the adjacent nodes
            for (ArrayList<Integer> u : adj.get(node))
            {
                int adjNode = u.get(0);
                int edgeWeight = u.get(1);

                // Relaxation step
                if (dist[adjNode] > currDist + edgeWeight)
                {
                    dist[adjNode] = currDist + edgeWeight; // Update distance
                    minHeap.offer(new Pair(adjNode, dist[adjNode])); // Add to the queue
                }
            }
        }

        return dist; // Return the array of distances
    }

    public static void main(String[] args)
    {
        // Example graph adjacency list representation
        int v = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < v; i++)
        {
            adj.add(new ArrayList<>());
        }

        // Add edges (for undirected graph, add both u -> v and v -> u)
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2))); // 0 -> 1 (weight 2)
        adj.get(1).add(new ArrayList<>(Arrays.asList(0, 2))); // 1 -> 0 (weight 2)
        adj.get(0).add(new ArrayList<>(Arrays.asList(2, 4))); // 0 -> 2 (weight 4)
        adj.get(2).add(new ArrayList<>(Arrays.asList(0, 4))); // 2 -> 0 (weight 4)
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 1))); // 1 -> 2 (weight 1)
        adj.get(2).add(new ArrayList<>(Arrays.asList(1, 1))); // 2 -> 1 (weight 1)
        adj.get(1).add(new ArrayList<>(Arrays.asList(3, 7))); // 1 -> 3 (weight 7)
        adj.get(3).add(new ArrayList<>(Arrays.asList(1, 7))); // 3 -> 1 (weight 7)
        adj.get(2).add(new ArrayList<>(Arrays.asList(3, 3))); // 2 -> 3 (weight 3)
        adj.get(3).add(new ArrayList<>(Arrays.asList(2, 3))); // 3 -> 2 (weight 3)
        adj.get(2).add(new ArrayList<>(Arrays.asList(4, 5))); // 2 -> 4 (weight 5)
        adj.get(4).add(new ArrayList<>(Arrays.asList(2, 5))); // 4 -> 2 (weight 5)
        adj.get(3).add(new ArrayList<>(Arrays.asList(4, 1))); // 3 -> 4 (weight 1)
        adj.get(4).add(new ArrayList<>(Arrays.asList(3, 1))); // 4 -> 3 (weight 1)

        // Run Dijkstra's algorithm from source node 0
        int[] distances = dijkstra(v, adj, 0);

        // Print shortest distances from the source
        System.out.println("Shortest distances from source node 0:");
        for (int i = 0; i < distances.length; i++)
        {
            if (distances[i] == Integer.MAX_VALUE)
            {
                System.out.println("Node " + i + " is unreachable.");
            }
            else
            {
                System.out.println("Distance to node " + i + " is " + distances[i]);
            }
        }
    }
}