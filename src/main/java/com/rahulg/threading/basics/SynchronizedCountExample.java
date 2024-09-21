package com.rahulg.threading.basics;

public class SynchronizedCountExample implements Runnable {
    private static int count = 0;

    public void run() {
        String threadName = Thread.currentThread().getName();
        String tag = "[Thread: " + threadName + "]";
        System.out.println(tag + ": Started");
        synchronized (SynchronizedCountExample.class) { // class level sync lock..
             while (count < 7) {
                System.out.println(tag + "This Block is Synchronized...avalable every 3 seconds.");
                try {
                    Thread.sleep(1000);
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
