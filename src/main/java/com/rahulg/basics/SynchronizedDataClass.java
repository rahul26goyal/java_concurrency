package com.rahulg.basics;

public class SynchronizedDataClass {
    private  int syncValue = 0;


    public int getSyncValue() {
        return syncValue;
    }

    public void incrementSyncValue() {
        String threadName = Thread.currentThread().getName();
        String tag = "[Thread: " + threadName + "]";
        System.out.println(tag + ": Started");
        while(this.syncValue < 3) {
            synchronized (SynchronizedDataClass.class) {
                System.out.println(tag + "This Block is Synchronized...avalable every 3 seconds.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tag + "Value Before: " + syncValue);
                syncValue += 1;
                System.out.println(tag + "Value After: " + syncValue);
            }
        }
    }


}
