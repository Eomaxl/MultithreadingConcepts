package com.eomaxl.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void m1(){
        lock1.lock();
        System.out.println("M1 Method: Have acquired lock on lock1 :"+Thread.currentThread().getName());
        lock2.lock();
        lock2.unlock();
        System.out.println("M1 Method: Have released lock on lock2 :"+Thread.currentThread().getName());
        lock1.unlock();
    }

    public void m2(){
        lock2.lock();
        System.out.println("M2 Method: Have acquired lock on lock2 :"+Thread.currentThread().getName());
        lock1.lock();
        lock1.unlock();
        System.out.println("M2 Method: Have released lock on lock1 :"+Thread.currentThread().getName());
        lock2.unlock();
    }

    public static void main(String[] args) {
        DeadlockDemo demo = new DeadlockDemo();
        Thread t1 = new Thread(()->{
            demo.m1();
        });
        Thread t2 = new Thread(()->{
            demo.m2();
        });
        t1.start();
        t2.start();
        new Thread(()->{
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while(true){
                long[] threadIds = mxBean.findDeadlockedThreads();
                if (threadIds != null) {
                    System.out.println("Deadlock detected!");

                    // ThreadInfo
                    for(long threadId : threadIds){
                        System.out.println("Thread with IDs "+threadId+" detected!");
                    }
                    break;
                }
            } try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

/**
 * Prevent deadlocks:
 * 1 ) Use Timeouts
 * 2) Global ordering of locks
 * 3) Avoid nesting of locks
 * 4) Use thread safe alternatives (atomic variable, thread safe collections)
 *
 *
 *
 * */