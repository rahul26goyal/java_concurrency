package com.rahulg.threading.medium;

public class WorkConsumer implements Runnable{
    public void run() {
        String tag = "[Thread: " + Thread.currentThread().getName() + "]";
        System.out.println(tag + "Started...");
        while (true) {
            System.out.println(tag + "Witaing for synchrinaized access...");
            synchronized (WorkProducer.queue) {
                try {
                    System.out.println(tag + "waiting for the notification..");
                    //Thread.sleep(3000); //to test the delay in other threads getting lock for this block.
                    WorkProducer.queue.wait(); //this will release the  lock on the object monitor and wait for notify message.
                    System.out.println(tag + "got Notification...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!WorkProducer.queue.isEmpty()) {
                    String work = WorkProducer.queue.poll().toString();
                    System.out.println(tag + "Work to be done: " + work);
                }
                else {
                    System.out.println(tag + "No Work..");
                    //break; // this is to exit if no work is pending.
                }
            }
        }
    }
}
