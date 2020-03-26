package com.rahulg.basics;

public class SynchronizedDataClassThread implements Runnable {

    public void run() {
        //each thread will get its own data class but still they will have to wait for the synchromized block to be free from other threads in the same JVm to access it.
        SynchronizedDataClass data = new SynchronizedDataClass();
        data.incrementSyncValue();
    }
}
