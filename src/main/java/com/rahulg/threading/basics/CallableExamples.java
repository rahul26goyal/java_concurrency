package com.rahulg.threading.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * Simple example to define callable..
 * Threads do not take callable as input directly..
 * we can use executorService to pass Callables and get a future back.
 * or we can spawn a thread inside a callable::call() but thats the general practice
 * of using this interface..
 *
 * Concurrency should be managed outisde the scope the task using soem executorService / concurrency management framework..
 *  - Runnabel and Callable objects should simply perform the task with in the thread in which they are invoked.
 *  - do not complecate the logics inside runnabel / callable to take decision across threads or tasks.
 */
public class CallableExamples {

    @Test
    public void test_lambda_Callable() throws Exception {
        // defining a callable
        Callable<String> getStringCallable = () -> {
            System.out.println("getting the name");
            return "Rahul goyal";
        };

        String name = getStringCallable.call();
        System.out.println("name = " + name);
    }

    @Test
    public void test_define_inline_callableObject() throws Exception {
        Callable<Integer> getNameCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Callable called..");
                return 12345;
            }
        };
        System.out.println("Invoking the callable");
        Integer randomNum = getNameCallable.call();
        System.out.println("randomNum = " + randomNum);
    }

    @Test
    public void test_callable_throws_exception() {
        Integer number = -19;
        Callable<Integer> myCallable = () -> {
            if (number < 0) {
                throw new Exception("Number must be greater than 0");
            }
            return number %5;
        };
        Exception ex = Assertions.assertThrows(Exception.class, () -> {
            myCallable.call();
        }, "Should have thrown execption.");
        Assertions.assertEquals(ex.getMessage(),  "Number must be greater than 0");
    }

}
