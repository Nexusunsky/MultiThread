package main;

import java.util.Random;

public class Client extends Thread {
    private final Channel channel;
    private final Random random = new Random();

    public Client(String threadName, final Channel channel) {
        super(threadName);
        this.channel = channel;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}
