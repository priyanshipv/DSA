package dsa.general;
import java.util.Collections;
import java.util.PriorityQueue;
//Time complexity: O(logN) for insertNum and O(1) for findMedian
//divide the data into two halves
//one half will be stored in a max heap and the other half will be stored in a min heap
//this will allow us to find the median of the data in O(1) time
//if the total number of elements is even, the median will be the average of the two middle elements
//if the total number of elements is odd, the median will be the middle element
//the max heap will store the smaller half of the data while the min heap will store the larger half of the data
public class TwoHeapPattern
{
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void insertNum(int num)
    {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num)
        {
            maxHeap.add(num);
        }
        else
        {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1)
        {
            minHeap.add(maxHeap.poll());
        }
        else if (maxHeap.size() < minHeap.size())
        {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian()
    {
        if (maxHeap.size() == minHeap.size())
        {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }


    public static void main(String[] args)
    {
        TwoHeapPattern medianOfAStream = new TwoHeapPattern();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());

    }

}