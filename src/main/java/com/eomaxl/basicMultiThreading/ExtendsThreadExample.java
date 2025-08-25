package com.eomaxl.basicMultiThreading;

public class ExtendsThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread1();
        t1.start();
        Thread t2 = new Thread2();
        t2.start();
    }
}


class Thread1 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread1: " + i);
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread2: " + i);
        }
    }
}