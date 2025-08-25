package com.eomaxl.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread one = new Thread(new FirstThread(exchanger));
        Thread two = new Thread(new SecondThread(exchanger));

        one.start();
        two.start();
    }
}

class FirstThread implements Runnable {
    private final Exchanger<Integer>  exchanger;

    public FirstThread(Exchanger<Integer>  exchanger) {
        this.exchanger = exchanger;
    }


    @Override
    public void run(){
        int dataToSend = 10;
        System.out.println("First Thread is sending data : "+dataToSend);
        try {
            Integer receiveData = exchanger.exchange(dataToSend);
            System.out.println("First Thread received data : "+receiveData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondThread implements Runnable {
    private final Exchanger<Integer>  exchanger;

    public SecondThread(Exchanger<Integer>  exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(3000);
            int dataToSend = 20;
            System.out.println("Second Thread is sending data : "+dataToSend);
            Integer receiveData = exchanger.exchange(dataToSend);
            System.out.println("Second Thread received data : "+receiveData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


/**
 *
 *
 * Introduction to exchanger
 *
 * What is Exchanger in Java ?
 * Why and when to use Exchangers ?
 * ELI5
 *
 * Exchanger Implementation in java
 * Comparing Queue with exchanger
 * Exchanger
 *  1) Point to Point communication
 *  2) Simplicity for two threads
 *  3) Synchronous
 *  4) Symmetrical exchange
 *
 * Queue
 *  1) One to many implementation
 *  2) Asychronous
 *  3)Buffering
 *  4) Non symmetrical exchange
 *
 * Similarity to Synchronous Queue
 * */
