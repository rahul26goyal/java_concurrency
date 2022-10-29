package com.rahulg.threading.medium;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Running Example from Medium Set...");
        //executeProducerConsumerWaitAndNotifyExample();
        //ObjectLevelLockExample.executeExample();
        ThreadLocalStorageExample.executeThreadLocalStorageExample();
    }

    public static void executeProducerConsumerWaitAndNotifyExample() throws InterruptedException {
        System.out.println("Executing executeProducerConsumerWaitAndNotifyExample");
        //producer..
        Thread producerThread = new Thread(new WorkProducer(), "Work-Producer");
        producerThread.start();

        Thread[] consumerThread = new Thread[5];
        for(int i = 0; i< 5;i++) {
            consumerThread[i] = new Thread(new WorkConsumer(), "ConsumerThread-" + i);
            consumerThread[i].start();
        }
        for(int i = 0; i< 5;i++) {
            consumerThread[i].join();
        }

        System.out.println("Finished executeProducerConsumerWaitAndNotifyExample");
        producerThread.join();
        WorkProducer.workStatus();
    }
}
