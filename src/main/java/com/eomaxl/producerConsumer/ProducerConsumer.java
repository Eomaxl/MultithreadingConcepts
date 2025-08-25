package com.eomaxl.producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {
        Worker worker = new Worker(5,0);
        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
