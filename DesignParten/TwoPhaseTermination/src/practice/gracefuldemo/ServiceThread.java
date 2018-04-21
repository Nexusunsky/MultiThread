package practice.gracefuldemo;

import main.twophase.GracefulThread;

public class ServiceThread extends GracefulThread {
    @Override
    protected void doWork() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            Thread.sleep(100);
        }
        shutdownRequest();
    }

    @Override
    protected void doShutdown() {
        System.out.print("done.");
    }
}
