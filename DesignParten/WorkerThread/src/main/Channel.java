package main;

public class Channel {
    private static final int MAX_REQUEST = 100;
    private int count;
    private int head;
    private int tail;
    private final Worker[] workers;
    private final Request[] requestsQueue;

    public Channel(final int workersCount) {
        count = 0;
        head = 0;
        tail = 0;

        requestsQueue = new Request[MAX_REQUEST];

        workers = new Worker[workersCount];
        for (int i = 0; i < workersCount; i++) {
            workers[i] = new Worker("Worker-" + i, this);
        }
    }

    public void startWorking() {
        for (int i = 0; i < workers.length; i++) {
            workers[i].start();
        }
    }

    /**
     * Put in the tail of RequestArray
     */
    public synchronized void put(final Request request) {
        while (count >= MAX_REQUEST) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        count++;
        requestsQueue[tail] = request;
        tail = (tail + 1) % requestsQueue.length;
        notifyAll();
    }

    /**
     * Take from head of RequestArray
     */
    public synchronized Request take() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        count--;
        Request request = requestsQueue[head];
        head = (head + 1) % requestsQueue.length;
        notifyAll();
        return request;
    }
}
