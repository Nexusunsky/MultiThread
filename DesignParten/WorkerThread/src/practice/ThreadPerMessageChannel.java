package practice;

import main.Request;

public class ThreadPerMessageChannel {
    private int count;
    private int head;
    private int tail;
    private final ThreadPerMessageWorker[] workers;

    public ThreadPerMessageChannel(final int workersCount) {
        count = 0;
        head = 0;
        tail = 0;

        workers = new ThreadPerMessageWorker[workersCount];
    }

    public synchronized void addToRequest(final Request request) {
        while (count >= workers.length) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        workers[tail] = new ThreadPerMessageWorker(Thread.currentThread().getName(), request);
        count++;
        tail = (tail + 1) % workers.length;
        notifyAll();
    }

    /**
     * Take from head of RequestArray
     */
    public synchronized void takeToExecute() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        count--;
        ThreadPerMessageWorker worker = workers[head];
        worker.start();
        head = (head + 1) % workers.length;
        notifyAll();
    }
}
