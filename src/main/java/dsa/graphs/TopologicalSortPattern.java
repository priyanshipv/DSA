package dsa.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortPattern
{
    public static void main(String[] args)
    {
        // Example usage
        int vertices = 6;
        int[][] edges = {
                {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
        };

        System.out.println("Topological sort of the graph is: ");
        topologicalSort(vertices, edges).forEach(System.out::println);
    }

    public static List<Integer> topologicalSort(int vertices, int[][] edges)
    {
        int[] inDegree = new int[vertices];
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }

        for (ArrayList<Integer> list : adjList)
        {
            for (Integer node : list)
            {
                inDegree[node]++;
            }
        }

        boolean[] vis = new boolean[vertices];
        ArrayList<Integer> ans = new ArrayList<>();
        return bfs(adjList, vertices, 0, vis, ans, inDegree);
    }

    static List<Integer> bfs(ArrayList<ArrayList<Integer>> adj, int V, int v, boolean[] vis, ArrayList<Integer> ans, int[] inDegree)
    {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++)
        {
            if (inDegree[i] == 0)
            {
                q.add(i);
                vis[i] = true;
            }
        }
        while (!q.isEmpty())
        {
            int node = q.poll();
            ans.add(node);
            for (Integer it : adj.get(node))
            {
                inDegree[it]--;
                if (inDegree[it] == 0)
                {
                    q.add(it);
                    vis[it] = true;
                }
            }
        }
        return ans;
    }
}