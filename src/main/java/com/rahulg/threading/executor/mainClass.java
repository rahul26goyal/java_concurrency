package com.rahulg.threading.executor;

import java.util.concurrent.ExecutionException;

public class mainClass {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Running Example from  Concurrent Package....");
        SimpleExecutorImplementation.executeSimpleExecutorImplementation();
        ThreadPoolExecutorExample.executeThreadPoolExecutorExample();
        SimpleExecutorServiceExample.executeSimpleExecutorServiceExample();
    }
}
