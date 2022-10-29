package com.rahulg.threading.basics;

public class NonSynchronizedCounter implements Runnable {
    private static int count = 2;

    public void run() {
        String threadName = Thread.currentThread().getName();
        String tag = "[Thread: " + threadName + "]";
        System.out.println(tag + ": Started");


        while(count < 7) {
            System.out.println(tag + "Value Before: " + count);
            count += 1;
            System.out.println(tag + "Value After: " + count);
        }
        System.out.println(tag + ": Finished");
    }
}
