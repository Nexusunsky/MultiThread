package main;

import java.io.IOException;
import java.util.Random;

public class ChangerThread extends Thread {
    private final Data date;
    private Random random = new Random();

    public ChangerThread(final Data date) {
        super(ChangerThread.class.getName());
        this.date = date;
    }

    @Override
    public void run() {
        super.run();
        int count = 0;
        while (true) {
            try {
                date.change("No." + (++count));
                Thread.sleep(random.nextInt(1000));
                if (random.nextBoolean()) {
                    date.save();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
