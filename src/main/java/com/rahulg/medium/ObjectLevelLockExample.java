package com.rahulg.medium;

import sun.lwawt.macosx.CSystemTray;

public class ObjectLevelLockExample {
    private final Object lock = new Object(); //declaring a lock

    //method1 and methid2 uses `lock` objects monitor, so either method1 or methhod2 will execute at a time.
    public void method1() throws InterruptedException {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "method1 called..waiting for sync block");
        synchronized (lock) {
            System.out.println(tag + "sleeping....");
            Thread.sleep(10000);
            System.out.println(tag + "awake....");
            //method2();
        }
    }

    public void method2() throws InterruptedException {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "method2 called..waiting for sync block");
        synchronized (lock) {
            System.out.println(tag + "sleeping....");
            Thread.sleep(10000);
            System.out.println(tag + "awake....");
            //method1();
        }
    }

    //this used `this` objects monitor.
    public void method3() throws InterruptedException {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "method3 called..waiting for sync block");
        synchronized (this) {
            System.out.println(tag + "sleeping....");
            Thread.sleep(50000);
            System.out.println(tag + "awake....");
        }
    }
    //this used `this` objects monitor. so either method3 or method4 will execute at a time.
    public synchronized void method4() throws InterruptedException {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "method4 called.....");
        System.out.println(tag + "sleeping....");
        Thread.sleep(50000);
        System.out.println(tag + "awake....");
    }


    public static void executeExample() throws InterruptedException {
        System.out.println("Starting.. Executing of ObjectLevelThread Example..");
        final ObjectLevelLockExample data = new ObjectLevelLockExample();

        Thread firstThread = new Thread(new Runnable() {
            public void run() {
                try {
                    data.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-1");

        Thread secondThread = new Thread(new Runnable() {
            public void run() {
                try {
                    data.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-2");
        Thread thirdThread = new Thread(new Runnable() {
            public void run() {
                try {
                    data.method3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-3");

        Thread fourthThread = new Thread(new Runnable() {
            public void run() {
                try {
                    data.method4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-4");

        //Expectation is at any given time, (1 |2) && (3 |4) will be executing.

        System.out.println("Starting Threads...");
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        firstThread.join();
        System.out.println("Joinig 2nd thread..");
        secondThread.join();
        thirdThread.join();
        fourthThread.join();
        System.out.println("Finished Executing of ObjectLevelThread Example..");
    }
}
