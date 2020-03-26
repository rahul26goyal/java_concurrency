package com.rahulg.basics;

public class SynchronizedCountExample implements Runnable {
    private static int count = 0;

    public void run() {
        String threadName = Thread.currentThread().getName();
        String tag = "[Thread: " + threadName + "]";
        System.out.println(tag + ": Started");
        while (count < 7) {
            synchronized (SynchronizedCountExample.class) {
                System.out.println(tag + "This Block is Synchronized...avalable every 3 seconds.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tag + "Value Before: " + count);
                count += 1;
                System.out.println(tag + "Value After: " + count);
            }
        }
        System.out.println(tag + ": Finished");
    }
}
