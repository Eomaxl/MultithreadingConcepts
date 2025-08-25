package com.eomaxl.basicMultiThreading;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 : "+i);
            }
        });

        Thread two = new Thread(()->{
            for (int i = 0; i < 15; i++) {
                System.out.println("Thread 2 : "+i);
            }
        });

        one.start();
        two.start();
        one.join();
        System.out.println("Thread 1 execution completed ");
        two.join();
        System.out.println("Thread 2 execution completed ");
        System.out.println("Completed execution of threads!");
    }
}
