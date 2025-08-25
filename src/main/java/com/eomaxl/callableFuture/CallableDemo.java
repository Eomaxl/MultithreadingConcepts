package com.eomaxl.callableFuture;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            System.out.println("Main Thread started");
            Future<Integer> result = service.submit(new ReturnValueTask());   // Future is a blocking operation.

            result.cancel(false);

            boolean cancelled = result.isCancelled();

            boolean done = result.isDone();

            System.out.println(result.get(2, TimeUnit.SECONDS));
            System.out.println("Main thread execution completed!!");
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 12;
    }
}
