package com.rahulg.medium;

public class ThreadLocalStorageExample implements  Runnable{
    //providing each thread with a storage of its own.
    private static final ThreadLocal<Integer> localIntStorage = new ThreadLocal<Integer>();

    private final int intValue;

    public ThreadLocalStorageExample(int value) {
        this.intValue = value;
    }


    public void run() {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "executing.");
        localIntStorage.set(this.intValue);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int newValue = localIntStorage.get();
        System.out.println(tag + ": value:" + newValue);
    }

    public static void executeThreadLocalStorageExample() throws InterruptedException {
        System.out.println("Starting executeThreadLocalStorageExample");
        Thread[] myThreads = new Thread[5];
        for(int i=0;i<5;i++) {
            myThreads[i] = new Thread(new ThreadLocalStorageExample(i), "Thread-Id-"+ i);
            myThreads[i].start();
        }
        System.out.println("Joining Threads,.");
        for(int i=0;i<5;i++) {
            System.out.println("Joing thread-" + i);
            myThreads[i].join();
        }
        System.out.println("Completed executeThreadLocalStorageExample");
    }
    /*
    You may wonder that each thread outputs exactly the value that it gets through the constructor although the variable threadLocal
is declared static. ThreadLocal’s internal implementation makes sure that every time you call set() the given value is stored within
a memory region that only the current thread has access to. Hence when you call afterwards get() you retrieve the value you have
set before, despite the fact that in the meantime other threads may have called set().
     */
}
