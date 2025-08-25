package com.eomaxl.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        // private resources
        try {
            ExecutorService service = Executors.newSingleThreadExecutor();
            for(int i =0; i < 5; i++) {
                service.execute(new Task(i));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}

class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run(){
        System.out.println("Task with ID " + taskId + " is being executed by thread "+ Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
