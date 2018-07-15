package practice.threadpermessage;

import main.Request;

public class ThreadPerMessageWorker extends Thread {
    private final Request request;

    public ThreadPerMessageWorker(final String name, final Request request) {
        super(name);
        this.request = request;
    }

    @Override
    public void run() {
        super.run();
        request.execute();
    }
}
