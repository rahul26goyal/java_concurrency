package com.rahulg.threading.medium;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public class PollerExecutorService implements ExecutorService {

    private final Queue<Runnable> taskQueue;
    private final Integer maxConcurrentThreads;
    private final Integer pollInterval;
    private final Executor threadBasedExecutor;
    private final ReentrantLock lock = new ReentrantLock();

    public PollerExecutorService(Queue<Runnable> taskQueue, Integer maxConcurrentThreads, Integer pollInterval, Executor threadBasedExecutor) {
        this.taskQueue = taskQueue;
        this.maxConcurrentThreads = maxConcurrentThreads;
        this.pollInterval = pollInterval;
        this.threadBasedExecutor = threadBasedExecutor;
    }

    private void startPolling() {
        // code to poll the Q and submit the task to thread pool
        while (!taskQueue.isEmpty()) {
            Runnable task = taskQueue.poll();
            if(task != null) {
                threadBasedExecutor.execute(task);
            } else {
                // Wait a bit before checking for new tasks
                try {
                    Thread.sleep(pollInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return List.of();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return List.of();
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return List.of();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void execute(Runnable command) {

    }
}
