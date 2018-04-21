package main;

import main.request.Request;

/**
 * @author: haoliu on 19/04/2018 12:26
 */
public class Scheduler {
    private static final int CAPACITY = 4;
    private final Thread[] workers = new Thread[CAPACITY];
    private final RequestQueue queue = RequestQueue.newInstance(CAPACITY);

    private boolean terminated = false;
    private boolean started = false;

    public Scheduler() {
        for (int i = 0; i < CAPACITY; i++) {
            final Thread worker = new Thread() {
                @Override
                public void run() {
                    while (!terminated && !isInterrupted()) {
                        doWork();
                    }
                    doShutdown();
                }
            };
            workers[i] = worker;
        }
    }

    public void invoke(Request request) {
        queue.enqueue(request);
    }

    public void start() {
        if (started) {
            System.out.println(" Scheduler is started. ");
            return;
        }
        started = true;
        for (Thread worker : workers) {
            worker.start();
        }
    }

    public void cancel() {
        terminated = true;
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }

    private void doShutdown() {
        System.out.println(" Scheduler shutdown.");
        queue.clear();
    }

    private void doWork() {
        final Request take = queue.take();
        take.execute();
    }
}
