package dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijikstrasPrintPath
{

    class Solution
    {
        static class Pair {
            int node, dist;
            Pair(int node, int dist) {
                this.node = node;
                this.dist = dist;
            }
        }

        // Function to find the shortest distance of all vertices from the source vertex `src`
        static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int src)
        {
            PriorityQueue<Pair> minHeap = new PriorityQueue<>(v, Comparator.comparingInt(p -> p.dist));
            int dist[] = new int[v];
            int[] parent = new int[v]; // To store the parent of each node

            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Initialize the parent of each node as itself
            for (int i = 0; i < v; i++) {
                parent[i] = i;
            }

            minHeap.offer(new Pair(src, dist[src]));

            while (!minHeap.isEmpty()) {
                Pair p = minHeap.poll();

                for (ArrayList<Integer> u : adj.get(p.node)) {
                    int adjNode = u.get(0);
                    int edgeWeight = u.get(1);

                    // Relaxation step
                    if (dist[adjNode] > p.dist + edgeWeight) {
                        dist[adjNode] = p.dist + edgeWeight;
                        minHeap.offer(new Pair(adjNode, dist[adjNode]));

                        // Update parent of adjNode
                        parent[adjNode] = p.node;
                    }
                }
            }

            // Print shortest paths
            for (int i = 0; i < v; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println("Node " + i + " is unreachable from source.");
                } else {
                    System.out.print("Shortest path to node " + i + ": ");
                    printPath(i, parent); // Function to print the path
                    System.out.println(" with distance: " + dist[i]);
                }
            }

            return dist;
        }

        // Function to print the path from source to a given node
        static void printPath(int node, int[] parent) {
            if (parent[node] == node) {
                System.out.print(node); // Base case: reached the source
            } else {
                printPath(parent[node], parent); // Recursive call to print parent path
                System.out.print(" -> " + node); // Print current node
            }
        }

        public static void main(String[] args) {
            // Example graph adjacency list representation
            int v = 5;
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

            // Initialize adjacency list
            for (int i = 0; i < v; i++) {
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
            dijkstra(v, adj, 0);
        }
    }

}
