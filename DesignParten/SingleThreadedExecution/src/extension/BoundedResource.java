package extension;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BoundedResource {
    private final Semaphore signal;
    private final int permits;
    private static final Random RANDOM = new Random(314159);

    public BoundedResource(final int permits) {
        this.signal = new Semaphore(permits);
        this.permits = permits;
    }

    public void use() throws InterruptedException {
        signal.acquire();
        try {
            doUse();
        } finally {
            signal.release();
        }
    }

    private void doUse() throws InterruptedException {
        Log.printLn("BEGIN: used = " + (permits - signal.availablePermits()));
        Thread.sleep(RANDOM.nextInt(500));
        Log.printLn("END: used = " + (permits - signal.availablePermits()));
    }
}
