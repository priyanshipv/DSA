package dsa.graphs;

public class LongestCycle
{
        // Variable to store the length of the longest cycle
        int result = -1;

    void dfs(int u, int[] edges, boolean[] visited, int[] count, boolean[] inRecursion) {
        if (u != -1) {
            visited[u] = true;
            inRecursion[u] = true;

            // Get the next node that this node points to
            int v = edges[u];

            // If the next node is valid and hasn't been visited
            if (v != -1 && !visited[v]) {
                // Update the distance for the next node
                count[v] = count[u] + 1;

                // Recursively visit the next node
                dfs(v, edges, visited, count, inRecursion);
            }
            // If the next node is part of the current recursion stack, it means a cycle is found
            else if (v != -1 && inRecursion[v]) {
                // Update the result with the length of the cycle
                result = Math.max(result, count[u] - count[v] + 1);
            }

            // Mark the current node as not in the recursion stack
            inRecursion[u] = false;
        }
    }

    // Function to find the longest cycle in the graph
    public int longestCycle(int[] edges) {
        int n = edges.length;

        // Arrays to keep track of visited nodes, distances, and recursion stack
        boolean[] visited = new boolean[n];
        int[] count = new int[n];
        boolean[] inRecursion = new boolean[n];

        // Iterate through all nodes and apply DFS if the node is not visited
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, edges, visited, count, inRecursion);
            }
        }

        return result;
    }
}
