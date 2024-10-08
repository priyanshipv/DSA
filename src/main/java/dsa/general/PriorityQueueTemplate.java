package dsa.general;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueTemplate
{

    public static void main(String[] args)
    {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap2 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>();
    }
}
