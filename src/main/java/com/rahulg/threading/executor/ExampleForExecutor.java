package com.rahulg.threading.executor;

import com.rahulg.threading.basics.Utils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

class ExampleForExecutor {

    @Test
    public void test_SimpleExecutor_Impl() {
        // create the executor
        SimpleExecutor simpleExecutor = new SimpleExecutor();

        Runnable task1 = () -> {
            Utils.printThreadDetails(Thread.currentThread(), "task1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task2 = () -> {
            Utils.printThreadDetails(Thread.currentThread(), "task2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        // execute task
        simpleExecutor.execute(task1);
        simpleExecutor.execute(task2);
    }

    @SneakyThrows
    @Test
    public void test_ThreadedExecutor_Impl() {
        // create the executor
        ThreadedExecutor threadExecutor = new ThreadedExecutor();

        Runnable task1 = () -> {
            Utils.printThreadDetails(Thread.currentThread(), "task1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task2 = () -> {
            Utils.printThreadDetails(Thread.currentThread(), "task2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        // execute task
        threadExecutor.execute(task1); // non blocking
        threadExecutor.execute(task2); // non blocking
        // we need to do something to wait for these the tasks to complete.
        // simple approach is to sleep
        Thread.sleep(3000);
    }

}

/**
 * This class will execute the task on the same thread.
 */
class SimpleExecutor implements Executor {

    @Override
    public void execute(Runnable task) {
        System.out.println("Executing task = " + task);
        task.run();
        System.out.println("Completed Executing task = " + task);
    }
}

/**
 * An Executor which will execute on a new thread for every task it receives.
 * Does not block the call and return nothing back to the caller.
 * It is the responsibility of the caller to detect if the work has been completed or not.
 */
class ThreadedExecutor implements Executor {

    @SneakyThrows
    @Override
    public void execute(Runnable task) {
        System.out.println("*************Executing task = " + task);
        Thread thread = new Thread(task, "ThreadedExecutor");
        thread.start();
        //thread.join(); // blocks the execution to wait for the thread..
        System.out.println("********Completed Executing task = " + task);
    }
}


