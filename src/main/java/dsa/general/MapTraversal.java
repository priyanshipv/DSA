package dsa.general;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//here we will create a new Map & traverse it
public class MapTraversal
{

    public static void main(String[] args)
    {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 25);
        map.put("Jane", 30);
        map.put("David", 35);

        //Iterating over the keys using for each loop
        for(String key: map.keySet()){
            Integer value = map.get(key);
            System.out.println(value);
        }

        //Iterating over the keys + val using iterator
        Iterator<String> itr = map.keySet().iterator();
        while(itr.hasNext()){
            String key = itr.next();
            Integer value = map.get(key);
            System.out.println(key + " : " + value);
        }

        //Iterating over the values
        for(Integer value: map.values())
        {
            System.out.println(value);
        }

        //Iterating over the values using iterator
        Iterator<Integer> iterator = map.values().iterator();
        while (iterator.hasNext()){
            Integer value = iterator.next();
            System.out.println(value);
        }

        // Iterating over entries K+V using for-each loop
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toString();
            System.out.println(key + " : " + value);
        }
        // Iterating over entries K+V using iterator
        Iterator<Map.Entry<String , Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String , Integer> entry = entryIterator.next();
            String key = entry.getKey();
            String value = entry.getValue().toString();
            System.out.println(key + " : " + value);
        }

    }
}
