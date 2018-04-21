package practice;

public class CountThreadWithoutFlag extends Thread {
    private int count = 0;

    public void shutdownRequest() {
        interrupt();
    }

    @Override
    public void run() {
        super.run();
        try {
            while (!isInterrupted()) {
                doWork();
            }
        } catch (InterruptedException e) {
            System.out.println("work is interrupted.");
        } finally {
            doShutdown();
        }
    }

    private void doShutdown() {
        System.out.println("doShutdown : when count = " + count);
    }

    private void doWork() throws InterruptedException {
        interrupted();
        count++;
        System.out.println("doWork: count = " + count);
    }
}
