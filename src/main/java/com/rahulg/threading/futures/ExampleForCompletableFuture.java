package com.rahulg.threading.futures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

// Reference: https://www.callicoder.com/java-8-completablefuture-tutorial/

/**
 * CompletableFuture allows parallel execution of multiple task in a program.
 * We can chain the execution which depends on each other with lexcibility to use same or different thread.
 * The future object returned is same as the future which we get by manually executing tasks using
 *  executorService.submittask(taask));
 */
public class ExampleForCompletableFuture {

    @Test
    public void simple_completablefuure() throws ExecutionException, InterruptedException {
        // define a place holder for the future value
        CompletableFuture<String> futureName = new CompletableFuture<>();
        //futureName.get(); // it will wait for ever
        futureName.complete("Rahul Goyal");
        Assertions.assertTrue(futureName.get().equals("Rahul Goyal"));
    }

    @Test
    public void simple_completablefuture_twice() throws ExecutionException, InterruptedException {
        // define a place holder for the future value
        CompletableFuture<String> futureName = new CompletableFuture<>();
        //futureName.get(); // it will wait for ever
        futureName.complete("Rahul Goyal"); // winner of future
        futureName.complete("Rahul"); // future can not be completed twice.
        Assertions.assertEquals(futureName.get(), "Rahul Goyal");
        Assertions.assertNotEquals(futureName.get(), "Rahul");
    }

    @Test
    void simple_completable_future_with_async_task() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        });
        Assertions.assertFalse(futureName.isDone());
        String name = futureName.get(); // this will return after 3 seconds
        Assertions.assertEquals(futureName.get(), "Rahul Goyal");
    }

    @Test
    void simple_completable_future_with_async_task_override() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        AtomicReference<Boolean> completed = new AtomicReference<>(false);
        CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
                System.out.println("awake...");
                completed.set(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        });
        Assertions.assertFalse(futureName.isDone());
        futureName.complete("Some Other name");
        String name = futureName.get(); // this will not block now
        Assertions.assertFalse(completed.get()); // this is still not get to true as the execution thead is still sleeeping
        Assertions.assertEquals(futureName.get(), "Some Other name");
        // now sleep the main thead so that the other completable thread can complete..
        Thread.sleep(2000);
        Assertions.assertTrue(completed.get()); // this will never get set to true as we exit this test without waiting.
        Assertions.assertEquals(futureName.get(), "Some Other name");
    }

    @Test
    public void simple_completable_future_with_runnable() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        AtomicReference<String> name = new AtomicReference<>();
        // runSync is used to run runnable which returns nothing
        CompletableFuture<Void> futureVoid = CompletableFuture.runAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
                name.set("Rahul");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assertions.assertEquals(name.get(), null);
        futureVoid.get();
        Assertions.assertEquals(name.get(), "Rahul");
    }

    @Test
    public void chain_completable_future_with_thenApply() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        }).thenApply((name) -> {
            System.out.println(String.format("Executing 2nd async in thread: {%s}", Thread.currentThread().getName()));
                return "Hello " + name;
            });
        Assertions.assertFalse(futureName.isDone());
        String name = futureName.get(); // this will return after 3 seconds
        Assertions.assertEquals(futureName.get(), "Hello Rahul Goyal");
    }

    @Test
    public void chain_completable_future_with_thenAccept() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        // all are executed in same async thread..
        CompletableFuture<Void> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        }).thenAccept((name) -> {
            System.out.println(String.format("Executing 2nd async in thread: {%s}", Thread.currentThread().getName()));
            System.out.println("Name: " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //return "Hello " + name; can not return anything
        }).thenRun(() -> { // accepts nothing.
            System.out.println(String.format("Executing 3rd async in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assertions.assertFalse(futureName.isDone());
        futureName.get(); // this will return after 3 seconds
        Assertions.assertNull(futureName.get());
    }

    @Test
    public void test_completable_future_with_custom_executor_service() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        AtomicReference<String> threadName = new AtomicReference<>("");
        CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
                threadName.set(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        }, executorService).thenApplyAsync((name) -> {
            System.out.println(String.format("Executing 2nd async in thread: {%s}", Thread.currentThread().getName()));
            Assertions.assertNotEquals(Thread.currentThread().getName(), threadName.get());
            return "Hello " + name;
        }, executorService); // if you dont pass executorService, it will execute in default poll

        Assertions.assertFalse(futureName.isDone());
        String name = futureName.get(); // this will return after 3 seconds
        Assertions.assertEquals(futureName.get(), "Hello Rahul Goyal");
    }

    @Test
    public void test_completable_future_with_custom_executor_service2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println(String.format("Main in thread: {%s}", Thread.currentThread().getName()));
        AtomicReference<String> threadName = new AtomicReference<>("");
        CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            threadName.set(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul Goyal";
        }, executorService)
            .thenApply((name) -> { // it will use the same thead from the above executorService
            System.out.println(String.format("Executing 2nd async in thread: {%s}", Thread.currentThread().getName()));
            Assertions.assertEquals(Thread.currentThread().getName(), threadName.get());
            return "Hello " + name;
        });
        Assertions.assertFalse(futureName.isDone());
        String name = futureName.get(); // this will return after 3 seconds
        Assertions.assertEquals(futureName.get(), "Hello Rahul Goyal");
    }



}
