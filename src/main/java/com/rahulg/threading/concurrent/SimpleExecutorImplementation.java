package com.rahulg.threading.concurrent;

import java.util.concurrent.Executor;


public class SimpleExecutorImplementation implements Executor {

    public void execute(Runnable r){
        System.out.println("Executing the given runnable..");
        new Thread(r, "some-work").start();
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
