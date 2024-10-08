package dsa.graphs;

import java.util.ArrayList;
import java.util.List;

public class PrintDfs
{
    private void DFS(int node, int[][] graph, List<Integer> path, List<List<Integer>> result) {
        // Add the current node to the current path
        path.add(node);

        // If we reached the target node, add the current path to the result
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
        } else {
            // Recur for all adjacent nodes
            for (int nextNode : graph[node]) {
                DFS(nextNode, graph, path, result);
            }
        }

        // Backtrack: remove the current node from the path to explore new paths
        path.remove(path.size() - 1);
    }

    // Main method to find all paths from source (0) to target (n-1)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        // Start DFS from node 0
        DFS(0, graph, path, result);

        return result;
    }
}
