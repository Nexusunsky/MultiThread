package practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * synchronized Queue
 *
 * @author haoliu
 */
public class RequestQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int TIME_OUT = 10000;

    public synchronized T getRequest() {
        long startCall = System.currentTimeMillis();
        while (queue.peek() == null) {
            try {
                long remains = TIME_OUT - (System.currentTimeMillis() - startCall);
                if (remains <= 0) {
                    throw new LivenessException("Time is Out.");
                }
                wait(remains);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    public synchronized void putQueue(T t) {
        queue.offer(t);
        notifyAll();
    }
}
