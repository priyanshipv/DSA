package dsa.graphs;

import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju
{
    void dfsFill(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> st) {
        visited[u] = true; // Mark the current node as visited

        // Visit all adjacent vertices
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsFill(v, adj, visited, st); // Recursive DFS call
            }
        }

        // Push the current vertex to the stack
        st.push(u);
    }

    // Function to perform DFS on the transposed graph
    void dfsTraverse(int u, ArrayList<ArrayList<Integer>> adjReversed, boolean[] visited) {
        visited[u] = true; // Mark the current node as visited

        // Visit all adjacent vertices in the reversed graph
        for (int v : adjReversed.get(u)) {
            if (!visited[v]) {
                dfsTraverse(v, adjReversed, visited); // Recursive DFS call
            }
        }
    }

    // Function to find the number of strongly connected components in the graph
    int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<>(); // Stack to store the vertices
        boolean[] visited = new boolean[V]; // Visited array to track visited vertices

        // Fill the stack with vertices in the order of their finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsFill(i, adj, visited, st);
            }
        }

        // Create a reversed graph
        ArrayList<ArrayList<Integer>> adjReversed = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjReversed.add(new ArrayList<>());
        }

        // Reverse the direction of all edges
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                adjReversed.get(v).add(u); // u -> v becomes v -> u
            }
        }

        int count = 0; // Count of strongly connected components
        visited = new boolean[V]; // Reset visited array for the second DFS

        // Process all vertices in the order defined by the stack
        while (!st.isEmpty()) {
            int node = st.pop(); // Get the top vertex from the stack
            if (!visited[node]) {
                dfsTraverse(node, adjReversed, visited); // Perform DFS on the transposed graph
                count++; // Increment the count of strongly connected components
            }
        }

        return count; // Return the number of strongly connected components
    }
}