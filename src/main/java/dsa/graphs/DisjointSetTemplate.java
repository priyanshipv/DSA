package dsa.graphs;

import java.util.HashMap;
import java.util.Map;
//undirected graph that has no cycle
public class DisjointSetTemplate {
    private static Map<Integer, Integer> parent = new HashMap<>();
    private static Map<Integer, Integer> rank = new HashMap<>();

    // Initializes the sets
    public static void makeSet(int[] universe) {
        for (int i : universe) {
            parent.put(i, i);
            rank.put(i, 0);
        }
    }
    // Finds the root of the set containing k
    public static int find(int k) {
        if (parent.get(k) != k) {
            parent.put(k, find(parent.get(k))); // Path compression
        }
        return parent.get(k);
    }
    // Unites the sets containing a and b
    public static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) {
            return; // They are already in the same set
        }

        // Union by rank
        if (rank.get(x) > rank.get(y)) {
            parent.put(y, x);
        } else if (rank.get(x) < rank.get(y)) {
            parent.put(x, y);
        } else {
            parent.put(x, y);
            rank.put(y, rank.get(y) + 1);
        }
    }

    // Prints the representative (root) of each set
    public static void printSets(int[] universe) {
        for (int i : universe) {
            System.out.print(find(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Universe of items
        int[] universe = { 1, 2, 3, 4, 5 };

        // Initialize the disjoint set
        makeSet(universe);

        // Print initial sets
        System.out.println("Initial sets:");
        printSets(universe);

        // Perform union operations
        union(4, 3);
        System.out.println("After union(4, 3):");
        printSets(universe);

        union(2, 1);
        System.out.println("After union(2, 1):");
        printSets(universe);

        union(1, 3);
        System.out.println("After union(1, 3):");
        printSets(universe);
    }
}
