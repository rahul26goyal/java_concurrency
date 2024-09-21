package com.rahulg.threading.basics;

//import com.rahulg.threading.executors.ExampleForExecutor;

public class Utils {

    public static  void printThreadDetails(Thread t, String name) {
        long tThreadId = t.getId();
        String tThreadName = t.getName();
        int tThreadPriority = t.getPriority();
        Thread.State currState = t.getState();
        String tThreadGroupName = t.getThreadGroup().getName();
        System.out.println("Printing the details of Thread: " + name.toUpperCase());
        System.out.println("tThreadId: "+tThreadId+"\ntThreadId: "+tThreadName+"\ntThreadPriority:"+tThreadPriority
                + "\ncurrState: "+currState+"\ntThreadGroupName="+tThreadGroupName);
    }

    public static void test() {
       // ExampleForExecutor
    }
}
