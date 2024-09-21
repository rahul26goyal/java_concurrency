package com.rahulg.threading.basics;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
//import sun.lwawt.macosx.CSystemTray;

public class Introduction {

    public static  void main(String[] args) throws InterruptedException {
        System.out.println("Introduction to Concurrency in Java..");
        //printMainThreadDetails();
        //executeThreadExample();
        //executeRunnableExample();
        //executeThreadInterrupt();
        //executeJoinThread();
        //executeNonSynchronizedThreds();
        executeSynchronizedThreds();
        //executeSynchronizedDataThreds();

    }

    public static void executeSynchronizedDataThreds() throws InterruptedException {
        System.out.println("executeSynchronizedDataThreds: called..  ");
        Thread[] myThreads = new Thread[10];
        for(int i = 0;i < 10;i++) {
            myThreads[i] = new Thread(new SynchronizedDataClassThread(), "ThreadNumber-" + i );
            myThreads[i].start();
        }
        for(int i = 0;i < 10;i++) {
            myThreads[i].join();
        }
        System.out.println("executeSynchronizedDataThreds: Finished.");
    }

    public static void executeSynchronizedThreds() throws InterruptedException {
        System.out.println("executeNonSynchronizedThreds: called..  ");
        Thread[] myThreads = new Thread[10];
        for(int i = 0;i < 10;i++) {
            myThreads[i] = new Thread(new SynchronizedCountExample(), "ThreadNumber-" + i );
            myThreads[i].start();
        }
        for(int i = 0;i < 10;i++) {
            myThreads[i].join();
        }
        System.out.println("executeNonSynchronizedThreds: Finished.");
    }

    public static void executeNonSynchronizedThreds() throws InterruptedException {
        System.out.println("executeNonSynchronizedThreds: called..  ");
        Thread[] myThreads = new Thread[10];
        for(int i = 0;i < 10;i++) {
            myThreads[i] = new Thread(new NonSynchronizedCounter(), "ThreadNumber-" + i );
            myThreads[i].start();
        }
        for(int i = 0;i < 10;i++) {
            myThreads[i].join();
        }

        System.out.println("executeNonSynchronizedThreds: Finished.");
    }

    public static void executeJoinThread() throws InterruptedException {
        Thread[] myThreads = new Thread[7];
        System.out.println("Creating 7 threads...");
        for(int i =0; i< 7;i++){
            myThreads[i] = new Thread(new JoinThreadExample(), "Thread-Name-" + i);
            myThreads[i].start();
        }
        System.out.println("Created 7 threads..." + System.currentTimeMillis());
        for(int i =0; i< 7;i++){
            myThreads[i].join();
        }
        System.out.println("Joined 7 threads..." + System.currentTimeMillis());

    }

    public static void printMainThreadDetails() {
        Thread main =  Thread.currentThread();
        long mainThreadId = main.getId();
        String mainThreadName = main.getName();
        int mainThreadPriority = main.getPriority();
        Thread.State currState = main.getState();
        String mainThreadGroupName = main.getThreadGroup().getName();
        System.out.println("mainThreadId: "+mainThreadId+"\nmainThreadId: "+mainThreadName+"\nmainThreadPriority:"+mainThreadPriority
                + "\ncurrState: "+currState+"\nmainThreadGroupName="+mainThreadGroupName + "\n");
    }

    public static void executeThreadExample() {
        MyThreadExample myThread = new MyThreadExample("Example1");
        myThread.start();
    }

    public static void executeRunnableExample() {
        Thread runnableThread = new Thread(new MyRunnableExample(), "MyRunnableExample");
        runnableThread.start();
    }

    public static void executeThreadInterrupt() throws InterruptedException {
        Thread intThread = new Thread(new InterreptableThread(), "InterruptedExceptionThread");
        intThread.start();
        System.out.println("Sleeping for 3 seconds...");
        Thread.sleep(3000);
        intThread.interrupt();
        System.out.println("Done with thread Interrupt...");
    }
}
