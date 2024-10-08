package dsa.graphs;

import javax.print.attribute.standard.OrientationRequested;
import java.time.OffsetTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

//if edges are given in a question

//Map<Integer, List<Integer>> adj = new HashMap<>();
//
//// Build the adjacency list
//        for (int i = 0; i < n; i++) {
//        adj.put(i, new ArrayList<>());  // Ensure every node has an empty list in the adjacency map
//        }
//
//        for (int[] edge : edges)
//        {
//           int u = edge[0];
//           int v = edge[1];
//
//            adj.get(u).add(v);
//            adj.get(v).add(u);
//        }
//OR

// Initialize the adjacency list
//Map<Integer, List<int[]>> adj = new HashMap<>();

//        for (int[] v : connections) {
//              int a = v[0];
//              int b = v[1];
//
//        adj.putIfAbsent(a, new ArrayList<>());
//        adj.putIfAbsent(b, new ArrayList<>());
//
//        // Add the directed edge (a -> b) with sign 1 and (b -> a) with sign 0
//        adj.get(a).add(new int[]{b, 1}); // Edge a -> b needs reordering
//        adj.get(b).add(new int[]{a, 0}); // Edge b -> a does not need reordering
//        }
//}
public class AdjacencyList
{
    private Map<Integer, List<Integer>> adjListMap;

    public AdjacencyList()
    {
        this.adjListMap = new HashMap<>();
    }

    public void addVertex(int vertex)
    {
        adjListMap.put(vertex, new LinkedList<>());
    }

    public void addEdge(int source, int destination)
    {
        adjListMap.get(source).add(destination);
        adjListMap.get(destination).add(source);
    }

    public void removeEdge(int source, int destination)
    {
        adjListMap.get(source).remove((Integer) destination);
        adjListMap.get(destination).remove((Integer) source);
    }

    public void removeVertex(int vertex)
    {
        adjListMap.remove(vertex);
        for (List<Integer> neighbors : adjListMap.values())
        {
            neighbors.remove(vertex);
        }
    }

    public void printGraph()
    {
        for (Map.Entry<Integer, List<Integer>> entry : adjListMap.entrySet())
        {
            System.out.println(entry.getKey() + "->");
            for (Integer neighbour : entry.getValue())
            {
                System.out.println(neighbour + " ");
            }
            System.out.println();
        }
    }

    public void dfsRecursive(int startVertex)
    {
        Set<Integer> visited = new HashSet<>();
        dfsRecursiveUtil(startVertex, visited);
    }

    public void dfsRecursiveUtil(int vertex, Set<Integer> visited)
    {
        //add vertex to visited set
        visited.add(vertex);
        System.out.println(vertex + " ");

        //Iterate through each neighbour of the vertex
        for (int neighbour : adjListMap.getOrDefault(vertex, Collections.emptyList()))
        {
            //if neighbour is not in visited set, recursively call dfsRecursive
            if (!visited.contains(neighbour))
            {
                dfsRecursiveUtil(neighbour, visited);
            }
        }
    }

    public void dfsIterative(int startVertex)
    {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty())
        {
            //Get element from the stack and mark visited
            int currVertex = stack.pop();
            System.out.println(currVertex + " ");
            visited.add(currVertex);

            //add all unvisited neighbours to the stack
            for (int neighbour : adjListMap.getOrDefault(currVertex, Collections.emptyList()))
            {
                if (!visited.contains(neighbour))
                {
                    stack.push(neighbour);
                }
            }
        }
    }

    //find the shortest distance between two vertices
    public void bfsIterative(int startVertex)
    {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty())
        {
            //get all elements from the queue and mark visited
            int currVertex = queue.poll();
            System.out.println(currVertex + " ");

            //add all unvisited neighbours to the queue
            for (int neighbour : adjListMap.getOrDefault(currVertex, Collections.emptyList()))
            {
                if (!visited.contains(neighbour))
                {
                    queue.add(neighbour);
                    visited.add(neighbour);
                }

            }

        }
    }
    public static void main(String args[]){
        AdjacencyList graph = new AdjacencyList();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.printGraph();
        System.out.println("DFS Recursive:");
        graph.dfsRecursive(0);
        System.out.println("DFS Iterative:");
        graph.dfsIterative(0);
        System.out.println("BFS Iterative:");
        graph.bfsIterative(0);
    }
}
