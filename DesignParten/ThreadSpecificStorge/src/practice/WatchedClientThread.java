package practice;

public class WatchedClientThread extends Thread {

    public WatchedClientThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " BEGIN ");
        for (int i = 0; i < 10; i++) {
            WatchedLogger localLogger = new WatchedLogger();
            try {
                localLogger.printIn("content = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(" println failed e = " + e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " END ");
    }
}
