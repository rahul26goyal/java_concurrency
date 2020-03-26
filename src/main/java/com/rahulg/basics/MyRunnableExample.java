package com.rahulg.basics;

public class MyRunnableExample implements Runnable {
    public void run() {
        System.out.println("Executing MyRunnableExample ...");
        Utils.printThreadDetails(Thread.currentThread(), "MyRunnableExample");
    }
}
