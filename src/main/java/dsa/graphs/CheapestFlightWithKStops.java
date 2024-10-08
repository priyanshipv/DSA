package dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CheapestFlightWithKStops
{
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);

            Map<Integer, List<int[]>> adj = new HashMap<>();

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int cost = flight[2];

                adj.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, cost});
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{src, 0});
            distance[src] = 0;

            int level = 0;

            while (!queue.isEmpty() && level <= k) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();
                    int u = current[0];
                    int u_d = current[1];

                    List<int[]> neighbors = adj.getOrDefault(u, Collections.emptyList());
                    for (int[] neighbor : neighbors) {
                        int v = neighbor[0];
                        int v_d = neighbor[1];

                        if (distance[v] > u_d + v_d) {
                            distance[v] = u_d + v_d;
                            queue.offer(new int[]{v, u_d + v_d});
                        }
                    }
                }
                level++;
            }

            return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
        }
    }
