package com.rahulg.threading.futures;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * In these examples we will take a look at how we can work with individual
 * CF and combine them if needed.
 * https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
public class ExampleForCompletableFutureCombination {

    CompletableFuture<String> getUserName(String id) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing getUserName in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Rahul";
        });
    }

    CompletableFuture<Integer> getUserRating(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("Executing getUserRating in thread: {%s}", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
    }

    @Test
    public void simply_execute_2_independent_CF() throws ExecutionException, InterruptedException {
        CompletableFuture<CompletableFuture<Integer>> futureRating = getUserName("abc")
            .thenApply(username -> getUserRating(username));
        // the thenApply is executing another independent CF and not a sync Function.. so it will return a CF<CF<Integer>>
        Assertions.assertEquals(10, futureRating.get().get());

        // we can change this to
        CompletableFuture<Integer> userRating = getUserName("bdff")
            .thenCompose(username -> getUserRating(username));
            // thenCompose with flatten the CF<Integer> returned by getUserRating -> Integer
        // thus, thenCompose with only return CF<Integer> and not CF<Cf<Integer>>
        Assertions.assertEquals(10, userRating.get());
    }

    @SneakyThrows
    @Test
    public void test_combine_result_of_2_independent_CF() {
        // execute 2 tasks to fetch independently
        CompletableFuture<String> futureName = getUserName("abc");
        CompletableFuture<Integer> futureRating = getUserRating("rahul");

        // combine the result 2 future and processing using a hanlder BiFUnction
        CompletableFuture<String> futureMessage = futureName
            .thenCombine(futureRating, (username, rating) -> {
               return String.format("The rating for user %s is %d", username, rating);
            });
        Assertions.assertEquals("The rating for user Rahul is 10", futureMessage.get());
    }

}
