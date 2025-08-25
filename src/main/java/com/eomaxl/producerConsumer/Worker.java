package com.eomaxl.producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class Worker {
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final List<Integer> container;
    private static final Object Lock = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new ArrayList<Integer>();
    }

    public void produce() throws InterruptedException {
        synchronized (Lock) {
            while(true) {
                if (container.size() == top ) {
                    System.out.println("Container full, waiting for items to be removed...");
                    Lock.wait();
                } else {
                    System.out.println(sequence+" Adding new item...");
                    container.add(sequence++);
                    Lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (Lock) {
            while(true) {
                if (container.size() == bottom) {
                    System.out.println("Container full, waiting for items to be removed...");
                    Lock.wait();
                } else {
                    System.out.println(container.removeFirst() +" Removing old item...");
                    Lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}