package com.eomaxl.threadSynchronization;

public class LockWithCustomObjects {

    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        System.out.println("start time :"+System.currentTimeMillis());
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                increment1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                increment2();
            }
        });

        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
           throw new RuntimeException(e);
        }

        System.out.println(counter1+" --- "+counter2+" with end time :"+System.currentTimeMillis());
    }

    private static void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }
}
