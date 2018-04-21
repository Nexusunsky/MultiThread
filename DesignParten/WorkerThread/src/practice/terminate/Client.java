package practice.terminate;

import java.util.Random;

import main.Request;

public class Client extends Thread {
    private final Channel channel;
    private final Random random = new Random();
    private boolean terminated = false;

    public Client(String threadName, final Channel channel) {
        super(threadName);
        this.channel = channel;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; !terminated; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " is Interrupted.");
        }
    }

    public void stopThread() {
        terminated = true;
        interrupt();
    }
}
