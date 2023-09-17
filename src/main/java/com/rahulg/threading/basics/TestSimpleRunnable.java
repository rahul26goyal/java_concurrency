package com.rahulg.threading.basics;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestSimpleRunnable {

    @Test
    public void test_executeSimpleRunnableTasks() throws InterruptedException {
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                String tag = "[Thread: " + Thread.currentThread().getName() + "]";
                System.out.println(tag + "Executing CallableThread..");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random random = new Random(System.currentTimeMillis());
                Integer returnValue = random.nextInt(100);
                System.out.println(tag + "Done with CallableThread with returnValue: " + returnValue);
            }
        };

        Thread t1 = new Thread(runnableTask, "Saome-Name");
        System.out.println("Start running task...");
        t1.start();
        t1.join(); // blocks the main thread..
        System.out.println("Completed running task..");
    }

    @Test
    public void test_nested_Runnabel() throws InterruptedException {
        final Integer[] count = {0};
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 1 running in thread: " + Thread.currentThread().getName());
                System.out.println("count = " + count[0]);
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 2 running in thread : " + Thread.currentThread().getName());
                count[0] +=1;
                task1.run();
            }
        };

        // task2.run();
        Thread t1 = new Thread(task2, "Child-thread-1");
        t1.start();
        t1.join();
        System.out.println("Completed running tasks..");
    }

}
