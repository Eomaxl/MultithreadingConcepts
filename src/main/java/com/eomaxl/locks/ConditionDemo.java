package com.eomaxl.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private final Integer MAX_SIZE = 5;
    private final Lock lock = new ReentrantLock();
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    private void produce(int item){
        lock.lock();
        try {   // processing
            while(buffer.size() == MAX_SIZE){
                bufferNotFull.await();
            }
            buffer.offer(item);
            System.out.println("Produced >>> "+item);
            bufferNotEmpty.signal();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void consume(){
        lock.lock();
        try {
            while(buffer.isEmpty()){
                bufferNotEmpty.await();
            }
            System.out.println("Consumed >>> "+buffer.peek());
            buffer.poll();
            bufferNotFull.signal();
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        Thread producerThread = new Thread(() -> {
           try {
               for(int i = 0; i < 10; i++){
                   demo.produce(i);
                   Thread.sleep(1000);
               }
           } catch (InterruptedException e) {
                throw new RuntimeException(e);
           }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for(int i = 0; i < 10; i++){
                    demo.consume();
                    Thread.sleep(2000);
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
