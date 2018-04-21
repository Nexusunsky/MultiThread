package practice.terminate;

import main.Request;

public class Worker extends Thread {
    private final Channel channel;
    private boolean terminated = false;

    public Worker(final String name, final Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        super.run();
        while (!terminated) {
            Request request = channel.take();
            request.execute();
        }
    }

    public void stopWork() {
        terminated = true;
        interrupt();
    }
}
