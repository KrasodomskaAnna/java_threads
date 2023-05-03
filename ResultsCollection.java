package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class ResultsCollection {
    private static final Queue<Integer> resultsCollection = new LinkedList<>();

    public synchronized void put(Integer value) {
        resultsCollection.add(value);
        System.out.println(value);
    }
}
