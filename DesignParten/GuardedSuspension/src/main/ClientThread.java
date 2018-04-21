package main;

import java.util.Random;

public class ClientThread extends Thread {
    private final Random random = new Random(66666600L);
    private final RequestQueue queue;

    public ClientThread(RequestQueue queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Request request = new Request("No. " + i);
            System.out.println(Thread.currentThread().getName() + " request " + request);
            queue.putQueue(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
