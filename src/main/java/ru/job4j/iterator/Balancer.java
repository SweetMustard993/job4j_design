package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        var balancedIterator = new CyclicIterator<ArrayList<Integer>>(nodes);
        while (source.hasNext()) {
            balancedIterator.next().add(source.next());
        }
    }
}