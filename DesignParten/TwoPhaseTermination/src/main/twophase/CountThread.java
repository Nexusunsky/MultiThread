package main.twophase;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: haoliu on 17/04/2018 12:34
 */
public class CountThread extends GracefulThread {
    private int count = 0;

    @Override
    protected void doShutdown() {
        System.out.println("doShutdown: counter = " + count);
        System.out.println("doShutdown: Save BEGIN");
        FileWriter writer = null;
        try {
            writer = new FileWriter("counter.txt");
            writer.write("counter = " + count);
        } catch (IOException e) {
            System.out.println("FileWriter.write" + e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("FileWriter.close" + e);
            }
        }
        System.out.println("doShutdown: Save END");
    }

    @Override
    protected void doWork() throws InterruptedException {
        count++;
        System.out.println("doWork: count = " + count);
        Thread.sleep(500);
    }
}
