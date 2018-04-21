package main;

import java.util.Random;

public class Consumer extends Thread {
    private final Random random;
    private final Table table;

    public Consumer(final Table table) {
        this.table = table;
        this.random = new Random(1000);
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                table.take();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}
