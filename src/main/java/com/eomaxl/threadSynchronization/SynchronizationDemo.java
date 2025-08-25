package com.eomaxl.threadSynchronization;

public class SynchronizationDemo {

    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args) {
        System.out.println("Start time :"+System.currentTimeMillis());
        Thread t1 = new Thread(()->{
            for(int i =0; i<10_000; i++){
                increment1();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i =0; i<10_000; i++){
                increment2();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter1+" --- "+counter2+" and end time is : "+System.currentTimeMillis());
    }
//
//    private synchronized static void increment(){
//        count++;
//    }

    private synchronized static void increment1(){
        counter1++;
    }

    private synchronized static void increment2(){
        counter2++;
    }
}


/**
 *
 * Above the operation is non-atomic due to which we are getting the value around 16k
 * 1. Load
 * 2. Increment
 * 3. Set back to the value
 *
 * Race condition is the reason why the value of count is not 20_000.
 * the increment() is called critical condition.
 *
 *
 * monitor lock() --> intrisic lock which is acquired by the synchronized keyword.
 * */