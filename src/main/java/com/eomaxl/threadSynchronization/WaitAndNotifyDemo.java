package com.eomaxl.threadSynchronization;

public class WaitAndNotifyDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(() ->{
            try{
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(()-> {
            try {
                two();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        one.start();
        two.start();
    }

    private static void one() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Hello from method one ... | Current Thread: " + Thread.currentThread().getName());
            lock.wait(); // The thread goes into the waiting state,
            System.out.println("Back again in the method one ... | Current Thread: " + Thread.currentThread().getName());
        }
    }

    private static void two() throws InterruptedException {
        synchronized (lock) { // as the lock is available to grab after thread1 went into waiting state
            System.out.println("Hello from method two ... | Current Thread: " + Thread.currentThread().getName());
            lock.notify(); // notifies that the lock has been released and up for grab. the other part of the method gets executed and then the control goes to the method1
            System.out.println("Notify again in the method two after notifiying ...| Current Thread: " + Thread.currentThread().getName());
        }
    }
}
