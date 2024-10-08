package dsa.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//Output: true
public class BfsTemplatePair
{
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Create an adjacency list from edges
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] edge: edges){
            int u= edge[0];
            int v= edge[1];
            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source]=true;

        while(!queue.isEmpty()){
            int curr = queue.remove();
            if(curr==destination){
                return true;
            }
            for(int neighbour: adjList.get(curr)){
                if(!visited[neighbour]){
                    visited[neighbour]= true;
                    queue.add(neighbour);
                }
            }

        }
        return false;
    }
}
// Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
