package main;

import java.util.Random;

public class ServerThread extends Thread {
    private final Random random = new Random(10000000L);
    private final RequestQueue queue;

    public ServerThread(RequestQueue queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Request request = queue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
