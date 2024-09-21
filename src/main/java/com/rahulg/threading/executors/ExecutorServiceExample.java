package com.rahulg.threading.executors;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// we will take a  look at how we can use runnable and callable with an inbuilt ExecutorService Implementation.
public class ExecutorServiceExample {

    @Test
    public void test_newSingleThreadExecutor_with_Runnable_nowait() throws Exception{
        // create a single threaded executor Service.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // create a runnable task
        Runnable myRunnableTask = getMyRunnableTask();
        // invoking using the executor service.
        System.out.println("Submitting myRunnableTask to executorservice.");
        executorService.execute(myRunnableTask); // this is not going to blocking.. so it wont wait for myRunnableTask to complete its task

        Thread.sleep(3000); // if i want to wait for the myRunnableTask to complete.
        System.out.println("Completed execution of myRunnableTask");
    }

    @Test
    public void test_newSingleThreadExecutor_with_Runnable_waiton_Future() throws InterruptedException, ExecutionException {
        // create a single threaded executor Service.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // create a runnable task
        Runnable myRunnableTask = getMyRunnableTask();
        // invoking using the executor service.
        System.out.println("Submitting myRunnableTask to executorservice.");
        Future result = executorService.submit(myRunnableTask);
        // Thread.sleep(3000); // if i want to wait for the myRunnableTask to complete.
        while(!result.isDone()) {
            System.out.println("watiing...");
            Thread.sleep(500);
        } // or result.get() whichi  is also blocking call.
        System.out.println("Completed execution of myRunnableTask with result: " + result.get());
    }

    @Test
    public void test_newSingleThreadExecutor_with_Runnable_waiton_FutureWithResult() throws InterruptedException, ExecutionException {
        // create a single threaded executor Service.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // create a runnable task
        Runnable myRunnableTask = getMyRunnableTask();
        // invoking using the executor service.
        System.out.println("Submitting myRunnableTask to executorservice.");
        Future<String> result = executorService.submit(myRunnableTask, "Success");
        // Thread.sleep(3000); // if i want to wait for the myRunnableTask to complete.
        while(!result.isDone()) {
            System.out.println("Waiting...");
            Thread.sleep(500);
        }
        System.out.println("Completed execution of myRunnableTask with result: " + result.get());
    }

    @Test
    public void test_newSingleThreadedExecutorService_with_Callable_Future() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> myCallableTask = getMyCallableTask();
        System.out.println("Submitting mycallable for execution...");
        Future<String> futureName = executorService.submit(myCallableTask);

        while(!futureName.isDone()) {
            System.out.println("watinig..");
            Thread.sleep(1000);
        }
        System.out.println("Completed execution of callable with result : "+ futureName.get());
    }



    private Runnable getMyRunnableTask() {
        return () -> {
            System.out.println("MyCallableTask invoked and it does not return anything");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Returning from myRunnableTask");
        };
    }

    private Callable<String> getMyCallableTask() {
        return () -> {
            System.out.println("myRunnableTask invoked and it does  return somethting ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Returning from MyCallableTask");
            return "Rahul Goyal";
        };
    }

}
