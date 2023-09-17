package com.rahulg.threading.concurrent;

import java.util.Random;
import java.util.concurrent.*;

public class SimpleExecutorServiceExample {

   //Define a callable class which is the Unit of work for thread.
   static class CallableThread implements Callable<Integer> {
        private final Random random = new Random(System.currentTimeMillis());

        //a callable thread can return Integer which is accepted in a future Object.
        public Integer call() throws Exception {
            String tag = "[Thread: " + Thread.currentThread().getName() + "]";
            System.out.println(tag + "Executing CallableThread..");
            Thread.sleep(5000);
            Integer returnValue = random.nextInt(100);
            System.out.println(tag + "Done with CallableThread with returnValue: " + returnValue);
            return returnValue;
        }
    }

    public static  void executeSimpleExecutorServiceExample() throws ExecutionException, InterruptedException {
        System.out.println("Startring with executeSimpleExecutorServiceExample");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> [] futures = new Future[10];

        for(int i = 0; i<10; i++) {
            futures[i] = executorService.submit(new CallableThread());
            System.out.println("Created future ID-"+ i);
        }
        for(int i = 0; i<10; i++) {
            Integer returnVal = futures[i].get(); //blocking operation for this thread.
            System.out.println("For Future-ID:" + i + " returnval:" + returnVal);
        }
        System.out.println("DOnw with executeSimpleExecutorServiceExample");
        executorService.shutdown();
    }

}
