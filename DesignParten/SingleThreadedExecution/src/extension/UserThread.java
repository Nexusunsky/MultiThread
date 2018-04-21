package extension;

import java.util.Random;

public class UserThread extends Thread {
    private static final Random RANDOM = new Random(26535);
    private final BoundedResource resource;

    public UserThread(final BoundedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                resource.use();
                Thread.sleep(RANDOM.nextInt(3000));
            } catch (InterruptedException e) {
            }
        }
    }
}
