package com.eomaxl.concurrentCollection;

import java.util.concurrent.CountDownLatch;

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);
        new Thread(new Chef("Chef A", "Pizza", latch)).start();
        new Thread(new Chef("Chef B", "Pasta", latch)).start();
        new Thread(new Chef("Chef C", "Salad", latch)).start();

        latch.await();

        System.out.println("All dishes has been prepared. Let's start serving customer");
    }
}

class Chef implements Runnable {
    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run(){
        try {
            System.out.println(name + " is preparing "+ dish);
            Thread.sleep(2000);
            System.out.println(name + " has finished preparing "+ dish);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


/**
 *
 * Introduction to countDown Latch
 * When to use countdown latch
 * Code demo
 * Is it functionality similar to join ?
 *  Purpose
 *  Usage
 *  Can we reset the count ? // we cannot reset the count
 *
 * */