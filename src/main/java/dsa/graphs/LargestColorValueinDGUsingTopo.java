package dsa.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LargestColorValueinDGUsingTopo
{
    public int largestPathValue(String colors, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int N = colors.length();
        int[] indegree = new int[N];

        // Build adjacency list and calculate indegree
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[][] t = new int[N][26];  // to track frequency of each color at each node

        // Start with nodes having indegree 0
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                t[i][colors.charAt(i) - 'a'] = 1; // mark the current color frequency
            }
        }

        int answer = 0;
        int countNodes = 0;

        // Topological sort using BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();
            countNodes++;

            // Update the answer with the max frequency of the current node's color
            answer = Math.max(answer, t[u][colors.charAt(u) - 'a']);

            // Traverse all neighbors of node u
            if (adj.containsKey(u)) {
                for (int v : adj.get(u)) {

                    // Update the color frequency for the neighbor v
                    for (int i = 0; i < 26; i++) {
                        t[v][i] = Math.max(t[v][i], t[u][i] + ((colors.charAt(v) - 'a') == i ? 1 : 0));
                    }

                    // Decrease indegree of the neighbor v
                    indegree[v]--;

                    // If indegree becomes 0, add it to the queue
                    if (indegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }
        }

        // If we haven't processed all nodes, there is a cycle
        if (countNodes < N) {
            return -1;
        }

        return answer;
    }
}
