package com.rahulg.threading.executor;

import java.util.concurrent.Executor;


public class SimpleExecutorImplementation implements Executor {

    // executor implementation which executes the runnable in a new thread
    public void execute(Runnable r){
        System.out.println("Executing the given runnable in a new thread..");
        new Thread(r, "some-work").start();
    }

    // or an executor implementation which executes the runnable in the same thread
    public void execute2(Runnable r){
        System.out.println("Executing the given runnable in the same thread..");
        r.run();
    }

    public static void executeSimpleExecutorImplementation() {
        System.out.println("Start with executeSimpleExecutorImplementation");
        SimpleExecutorImplementation anyExecutor = new SimpleExecutorImplementation();
        anyExecutor.execute(new Runnable() {
            public void run() {
                System.out.println("DO this work in thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done with work.bye");
            }
        });
        System.out.println("DOne with executeSimpleExecutorImplementation");
    }
}
