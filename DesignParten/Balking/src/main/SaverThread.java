package main;

import java.io.IOException;

public class SaverThread extends Thread {
    private final Data date;

    public SaverThread(final Data date) {
        super(SaverThread.class.getName());
        this.date = date;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                date.save();
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
