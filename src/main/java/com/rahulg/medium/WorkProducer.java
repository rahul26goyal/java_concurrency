package com.rahulg.medium;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WorkProducer implements Runnable{

    public static final Queue queue = new ConcurrentLinkedQueue<String>();
    //public static final long startMillis = System.currentTimeMillis();

    public void run() {
        System.out.println("Producer Thread Started...");
        String tag = "[Thread: " +  Thread.currentThread().getName() + "]";
        System.out.println(tag + "Starting");
        int workCount = 0;
        while(workCount < 5) {
            String work = "Work Number="+ workCount;
            queue.add(work);
            workCount++;
            synchronized (queue) {
                System.out.println(tag + "notify");
                queue.notify();
            }
            try {
                System.out.println(tag + "Sleeping..");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (queue) {
            queue.notifyAll();
            System.out.println(tag + "notifyAll");
        }
        System.out.println(tag + "Finished");

    }

    public static void workStatus() {
        System.out.println("Pending Iterms: " + queue.size());
    }
}
