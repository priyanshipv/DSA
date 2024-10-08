package dsa.graphs;

import java.util.ArrayList;

public class EulerPath
{
        private void DFS(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited) {
            visited[u] = true;

            for (int i = 0; i < adj.get(u).size(); i++) {
                int neighbor = adj.get(u).get(i);
                if (!visited[neighbor]) {
                    DFS(adj, neighbor, visited);
                }
            }
        }

        private boolean isConnected(int V, ArrayList<ArrayList<Integer>> adj) {
            boolean[] visited = new boolean[V];

            // Find a vertex with non-zero degree
            int nonZeroDegreeVertex = -1;
            for (int i = 0; i < V; i++) {
                if (adj.get(i).size() != 0) {
                    nonZeroDegreeVertex = i;
                    break;
                }
            }

            // Start DFS traversal from a vertex with non-zero degree
            DFS(adj, nonZeroDegreeVertex, visited);

            // Check if all non-zero degree vertices are visited
            for (int i = 0; i < V; i++) {
                if (!visited[i] && adj.get(i).size() > 0) {
                    return false;
                }
            }
            return true;
        }

        public int isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj)
        {
            // Check if all non-zero degree vertices are connected
            if (!isConnected(V, adj)) {
                return 0; // Non-Eulerian
            }

            // Count vertices with odd degree
            int oddCount = 0;
            for (int i = 0; i < V; i++) {
                if (adj.get(i).size() % 2 != 0) {
                    oddCount++;
                }
            }

            // If count is more than 2, then graph is not Eulerian
            if (oddCount > 2) {
                return 0; // Non-Eulerian
            }

            if (oddCount == 2) {
                return 1; // Semi-Eulerian (It has only Euler Path)
            }

            return 2; // Euler Circuit
        }
}

