package com.rahulg.threading.executors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * In this we will look at how the single threaded excutor behaves with different scenarios.
 */
public class SingleThreadedExecutorExample {

    //private ExecutorService executorService;

    private boolean throwsException = false;

    @BeforeEach
    public void setup() {
        throwsException = false;
    }

    @Test
    public void test_simple_Callable() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> myCallable1 = getMyCallableTask(2000, "task1", 12345);
        Callable<Integer> myCallable2 = getMyCallableTask(3000, "task2", 123);
         // myCallable.call(); this is sync call without any threads and block the main thread.

        // submit 2 tasks which will be executed one after the other.
        Future<Integer> result1 = executorService.submit(myCallable1);
        Future<Integer> result2 = executorService.submit(myCallable2);
        Assertions.assertEquals(123, result2.get());

    }

    //https://stackoverflow.com/questions/2248131/handling-exceptions-from-java-executorservice-tasks
    @Test
    public void test_Callable_with_exception() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> myCallable1 = getMyCallableTask(2000, "task1", 12345);
        Callable<Integer> myCallable2 = getMyCallableTask(3000, "task2", 123);
        // myCallable.call(); this is sync call without any threads and block the main thread.

        // submit 2 tasks which will be executed one after the other.
        this.throwsException = true;
        Future<Integer> result1 = executorService.submit(myCallable1);
        Future<Integer> result2 = executorService.submit(myCallable2);
        Exception ex1 = Assertions.assertThrows(Exception.class, () -> result1.get());
        Exception ex2 = Assertions.assertThrows(Exception.class, () -> result2.get());
        Assertions.assertTrue(ex1.getMessage().contains("task1"));
        Assertions.assertTrue(ex2.getMessage().contains("task2"));
    }

    private Callable<Integer> getMyCallableTask(long sleepTime, final String taskName, Integer returnValue) {
        final long sleep = Optional.ofNullable(sleepTime).orElse(2000L);
        return () -> {
            System.out.println("MyCallable invoked=" +taskName +  " with sleep time :"+ sleep);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (throwsException) {
                System.out.println(String.format("raiesing exception in task %s", taskName));
                throw new Exception(String.format("Exception occurred in task: {%s}", taskName));
            }
            System.out.println(String.format("Returning from MyCallableTask: {%s}", taskName));
            return returnValue;
        };
    }

}
