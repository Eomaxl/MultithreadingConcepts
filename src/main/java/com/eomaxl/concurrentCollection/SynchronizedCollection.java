package com.eomaxl.concurrentCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollection {
    public static void main(String[] args) throws InterruptedException {

       // List<Integer> list = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread one = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(list.size());

    }
}


// Downside of using Collections.synchronized() approach
/**
 * Coarse grained locking
 * Limited functionality
 * No Fail Fast iterators
 * Performance overhead
 *
 *
 * */