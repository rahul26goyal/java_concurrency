package com.rahulg.basics;

public class InterreptableThread implements Runnable {
    public void run() {
        System.out.println("Executing InterreptableThread....");
        try {
            System.out.println("Sleeping forever...");
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is InterruptedException.");
        }
    }
}
