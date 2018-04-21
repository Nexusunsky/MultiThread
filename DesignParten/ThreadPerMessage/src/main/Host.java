package main;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public class Host {
    private ThreadFactory factory;
    private Executor executor;
    private ExecutorService threadPool;

    public Host(final ThreadFactory factory) {
        this.factory = factory;
    }

    public Host(final Executor executor) {
        this.executor = executor;
    }

    public Host(final ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    public Host() {
    }

    public void request(final int count, final char c) {
        System.out.println(" request (" + count + ", " + c + ") BEGIN");

        Runnable r = () -> doHandle(count, c);

        if (threadPool != null) {
            threadPool.submit(r);
        } else if (executor != null) {
            executor.execute(r);
        } else if (factory != null) {
            HelperThread thread = new HelperThread(r);
            factory.newThread(thread).start();
        } else {
            HelperThread thread = new HelperThread(r);
            thread.start();
        }

        System.out.println(" request (" + count + ", " + c + ") END");
    }

    private void doHandle(final int count, final char c) {
        new Handler().handle(count, c);
    }

    private class HelperThread extends Thread {
        private final Runnable r;

        private HelperThread(final Runnable r) {
            this.r = r;
        }

        @Override
        public void run() {
            super.run();
            r.run();
        }
    }
}
