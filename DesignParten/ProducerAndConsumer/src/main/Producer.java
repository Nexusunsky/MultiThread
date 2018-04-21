package main;

import java.util.Random;

public class Producer extends Thread {
    private final Random random;
    private final Table table;
    private int id;

    public Producer(final Table table) {
        this.random = new Random();
        this.table = table;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                String cake = "[cake No." + (++id) + " by " + getName() + "]";
                table.put(cake);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
