package practice;

import java.util.Random;

import main.Request;

public class ThreadPerMessageClient extends Thread {
    private final ThreadPerMessageChannel perChannel;
    private final Random random = new Random();

    public ThreadPerMessageClient(String threadName, final ThreadPerMessageChannel perChannel) {
        super(threadName);
        this.perChannel = perChannel;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.perChannel.addToRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}
