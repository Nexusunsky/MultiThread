package main;

import java.util.LinkedList;
import java.util.Queue;

/**
 * synchronized Queue
 *
 * @author haoliu
 */
public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() {
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    public synchronized void putQueue(Request request) {
        queue.offer(request);
        notifyAll();
    }
}
