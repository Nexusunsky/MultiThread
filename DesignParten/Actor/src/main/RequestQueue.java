package main;

import main.request.Request;

/**
 * channel 阻塞队列
 *
 * @author: haoliu on 19/04/2018 12:27
 */
public class RequestQueue {
    private final int CAPACITY;
    private final Request[] requests;
    private int count;
    private int head;
    private int tail;

    private RequestQueue(final int capacity) {
        this.requests = new Request[capacity];
        this.CAPACITY = capacity;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
    }

    public static RequestQueue newInstance(int capacity) {
        return QueueHolder.loadSingleton(capacity);
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " [take in RequestQueue] filed. " + e);
            }
        }
        final Request request = requests[tail];
        tail = (tail + 1) % CAPACITY;
        count--;
        notifyAll();
        return request;
    }

    public synchronized void enqueue(Request request) {
        while (count >= CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("RequestQueue [take] filed." + e);
            }
        }
        requests[head] = request;
        head = (head + 1) % CAPACITY;
        count++;
        notifyAll();
    }

    public synchronized void clear() {
        for (int i = 0; i < CAPACITY; i++) {
            requests[i] = null;
        }
        count = 0;
        head = 0;
        tail = 0;
    }

    private static final class QueueHolder {
        //TODO  Single default

        private static RequestQueue instance;

        private static RequestQueue loadSingleton(int capacity) {
            if (instance == null) {
                instance = new RequestQueue(capacity);
            }
            return instance;
        }
    }
}
