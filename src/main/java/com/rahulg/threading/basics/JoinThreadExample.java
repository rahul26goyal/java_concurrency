package com.rahulg.threading.basics;

import java.util.Random;

public class JoinThreadExample implements Runnable {
    private Random randomGenerator = new Random(System.currentTimeMillis());

    public void run() {
        int sleepTime = randomGenerator.nextInt(10);
        System.out.println("Sleep time for : "+ Thread.currentThread().getName() + " is: "+ sleepTime);
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Thread: "+ Thread.currentThread().getName());
    }
}
