package main;

public class Worker extends Thread {
    private final Channel channel;

    public Worker(final String name, final Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            Request request = channel.take();
            request.execute();
        }
    }
}
