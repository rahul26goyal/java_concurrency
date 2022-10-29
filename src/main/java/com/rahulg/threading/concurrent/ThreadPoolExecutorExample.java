package com.rahulg.threading.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample {

    private static AtomicInteger counter = new AtomicInteger(); //to generate atomic number for this class.

    public static void executeThreadPoolExecutorExample() {
        System.out.println("Starting Execution of executeThreadPoolExecutorExample");
        BlockingQueue<Runnable> waitingThreadQueue = new LinkedBlockingQueue<Runnable>(6);
        ThreadFactory factory = new SomeThreadFactory();
        RejectedExecutionHandler rejectionHandler = new ThreadRejectionHandler();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.SECONDS, waitingThreadQueue,
               factory, rejectionHandler);
        System.out.println("Creating Runnable..and executing..");
        for(int i = 0; i < 20; i++) {
            executor.execute(new WorkerThread(i));
            System.out.println("Runnable ID created-" + i);
        }
        System.out.println("..shutting down the executor.");
        executor.shutdown();
        System.out.println("Done with executeThreadPoolExecutorExample");
    }

    //worker thread
    public static  class WorkerThread implements Runnable {
        private final int taskId;

        public  WorkerThread(int id) {
            this.taskId = id;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public void run() {
            String tag = "[Thread: " + Thread.currentThread().getName() + "]";
            System.out.println(tag + "executing");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tag + "Done");
        }
    }

    //A FACTORY CLASS TO CREATE Threads given a runnable object.
    //new WorkerThreadFactory().newThread(Runnable r)
    public static class SomeThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            String tag = "[Thread: " + Thread.currentThread().getName() + "]";
            int threadCount = counter.getAndIncrement();
            System.out.println(tag + "Creating Thread Number :" + threadCount);
            return new Thread(r, "Thread-id:" + threadCount);
        }
    }

    //to handle rejection of a runnable object by ThreadPoolExecutor.
    public static  class ThreadRejectionHandler implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if(r instanceof WorkerThread) {
                WorkerThread th = (WorkerThread) r;
                System.out.println("Task Id rejected:" + th.getTaskId());
            }
        }
    }
}
