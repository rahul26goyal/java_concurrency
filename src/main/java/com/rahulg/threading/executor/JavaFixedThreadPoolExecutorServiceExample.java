package com.rahulg.threading.executor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class JavaFixedThreadPoolExecutorServiceExample {

    private static class Task implements Runnable {

        private String command;

        Task(String input) {
            command = input;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start. Command = "+ command);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" Ended task.");
        }
    }

    @Test
    public void test_fixedThreadPool() throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);
        // the service will process 5 threads at any point in time.
        for(int i = 0; i<10; i++) {
            Runnable task = new Task("Task-NUmber-" + i);
            System.out.println("Submitting task = " + task.toString());
            service.submit(task);
        }
        // there are 10 tasks which are pushed to the thread pool for execution
        // but not all will executor in parallel as the size of the poll is only 5

        // send  shutdown request.
        service.shutdown();
        while (!service.isTerminated()) {
            Thread.sleep(1000);
        }
        System.out.println("Completed Execution of all theeads..");
//        Runnable task = new Task("Task-NUmber-" + 44);
//        System.out.println("Submitting task = " + task.toString());
//        service.submit(task); // this will throw exeception as the executor service has been terminated.

    }

    @Test
    public void test_scheduledThreadpool() throws ExecutionException, InterruptedException {
        Runnable task = new Task("Task-NUmber-1-scheduled");

        ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);
        System.out.println("Scheduling the task for execution..");
        ScheduledFuture<?> future =  execService.schedule(task, 5, TimeUnit.SECONDS);
        future.get();
        System.out.println("future.isDone() = " + future.isDone());
        execService.shutdownNow();
    }

    @Test
    public void test_scheduledThreadpool_reoccuring() throws ExecutionException, InterruptedException {
        Runnable task = new Task("Task-NUmber-1-scheduled");

        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        System.out.println("Scheduling the task for execution..");
        ScheduledFuture future =  service.scheduleAtFixedRate(task, 2, 3, TimeUnit.SECONDS);
        int i = 0;
        while (Boolean.TRUE) {
            System.out.println("Sleeping Tread: " + Thread.currentThread().getName());
            Thread.sleep(6000);
            i += 1;
            if ( i> 3) {
                System.out.println("cancelling the periodic job now....");
                future.cancel(true);
                break;
            }
        }
        service.shutdown();
        while(service.isTerminated()) {

        }
        System.out.println("Completed...");
    }

}
