package com.rahulg.threading.medium;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelExecutorService implements ExecutorService {
    private final int nThreads;
    private final List<Worker> workers;
    private final Queue<Runnable> taskQueue;
    private final AtomicBoolean isShutdown;
    private final AtomicInteger activeWorkers;

    public ParallelExecutorService(int nThreads) {
        this.nThreads = nThreads;
        this.workers = new ArrayList<>(nThreads);
        this.taskQueue = new ConcurrentLinkedQueue<>();
        this.isShutdown = new AtomicBoolean(false);
        this.activeWorkers = new AtomicInteger(0);

        // Create and start worker threads
        for (int i = 0; i < nThreads; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (!isShutdown.get() || !taskQueue.isEmpty()) {
                Runnable task = taskQueue.poll();
                if (task != null) {
                    activeWorkers.incrementAndGet();
                    try {
                        task.run();
                    } finally {
                        activeWorkers.decrementAndGet();
                    }
                } else {
                    // Wait a bit before checking for new tasks
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isShutdown.get()) {
            throw new RejectedExecutionException("Executor has been shut down");
        }
        taskQueue.offer(command);
    }

    @Override
    public void shutdown() {
        isShutdown.set(true);
    }

    @Override
    public List<Runnable> shutdownNow() {
        isShutdown.set(true);
        for (Worker worker : workers) {
            worker.interrupt();
        }
        List<Runnable> remainingTasks = new ArrayList<>();
        //taskQueue.drain(remainingTasks);
        return remainingTasks;
    }

    @Override
    public boolean isShutdown() {
        return isShutdown.get();
    }

    @Override
    public boolean isTerminated() {
        return isShutdown.get() && taskQueue.isEmpty() && activeWorkers.get() == 0;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long endTime = System.currentTimeMillis() + unit.toMillis(timeout);
        while (System.currentTimeMillis() < endTime) {
            if (isTerminated()) {
                return true;
            }
            Thread.sleep(100);
        }
        return isTerminated();
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> futureTask = new FutureTask<>(task);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        FutureTask<T> futureTask = new FutureTask<>(task, result);
        execute(futureTask);
        return futureTask;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return submit(task, null);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(submit(task));
        }
        for (Future<T> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                // Ignore execution exceptions
            }
        }
        return futures;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        long endTime = System.currentTimeMillis() + unit.toMillis(timeout);
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(submit(task));
        }
        for (Future<T> future : futures) {
            try {
                if (System.currentTimeMillis() > endTime) {
                    future.cancel(true);
                } else {
                    future.get(endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (ExecutionException | TimeoutException e) {
                // Ignore execution and timeout exceptions
            }
        }
        return futures;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        ExecutorCompletionService<T> ecs = new ExecutorCompletionService<>(this);
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(ecs.submit(task));
        }
        try {
            return ecs.take().get();
        } finally {
            for (Future<T> future : futures) {
                future.cancel(true);
            }
        }
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorCompletionService<T> ecs = new ExecutorCompletionService<>(this);
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        for (Callable<T> task : tasks) {
            futures.add(ecs.submit(task));
        }
        try {
            return ecs.poll(timeout, unit).get();
        } finally {
            for (Future<T> future : futures) {
                future.cancel(true);
            }
        }
    }
}

