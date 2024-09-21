package com.rahulg.threading.futures;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * In this class we will take a look at how we can handle exception which might occur within the
 * async tasks.
 * https://www.callicoder.com/java-8-completablefuture-tutorial/
 *
 * Exception are thrown when we call CF.get() or we can handle exception within the CF chain.
 */
public class ExampleForCompletableFutureWithExceptions {

    private Supplier<Integer> getSupplierWhichThrowsException() {
        return () -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer age = 0;
            // throw new Exception("Something happened.."); we cant throw checked exception
            if (age == 0) {
                throw new IllegalArgumentException("Something went wrong here.");
            }
            return 123;
        };
    }

    private Supplier<Integer> getSupplierWithSuccess() {
        return () -> {
            System.out.println(String.format("Executing in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1234567;
        };
    }

    @Test
    public void simple_completable_future_with_Exception() throws ExecutionException, InterruptedException {
        Supplier<Integer> supplierTask = getSupplierWhichThrowsException();
        CompletableFuture<Integer> futureAge = CompletableFuture.supplyAsync(supplierTask);

        Exception ex = Assertions.assertThrows(ExecutionException.class, () -> futureAge.get());
        Assertions.assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
        Assertions.assertEquals("Something went wrong here.", ex.getCause().getMessage());
    }

    @SneakyThrows
    @Test
    public void simple_completable_which_handles_exception() {
        CompletableFuture<Integer> futureAge = CompletableFuture
            .supplyAsync(getSupplierWhichThrowsException())
            .exceptionally(ex -> {
                System.out.println(String.format("Exception in thread: {%s}", Thread.currentThread().getName()));
                return 123;
            });
        Assertions.assertEquals(123, futureAge.get());
    }

    @SneakyThrows
    @Test
    public void  simple_completable_future_with_finally_handler() {

        CompletableFuture<Integer> futureAge = CompletableFuture
            .supplyAsync(getSupplierWhichThrowsException())
            .handle((result, exception) -> { // always invoked
                if (exception == null) {
                    return 12345;
                }
                return 123;
            });
        Assertions.assertEquals(123, futureAge.get());

        // no exception case.
        CompletableFuture<Integer> futureAge2 = CompletableFuture
            .supplyAsync(getSupplierWithSuccess())
            .handle((res, ex) -> {
                System.out.println(String.format("Executing handle in thread: {%s}", Thread.currentThread().getName()));
               if (ex != null) {
                   return 123;
               }
               return res; // returns the resposne as it is.
            });
        Assertions.assertEquals(1234567, futureAge2.get());
    }
}
