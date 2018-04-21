package practice;

import main.Log;

public class Watcher extends Thread {
    private final Log logger;
    private final Thread watchedThread;

    private Watcher(Thread thread, Log logger) {
        this.logger = logger;
        this.watchedThread = thread;
    }

    public static void watchThread(Thread thread, Log logger) {
        Watcher watcher = new Watcher(thread, logger);
        watcher.start();
    }

    @Override
    public void run() {
        super.run();
        try {
            //等待线程终止执行完成
            watchedThread.join();
        } catch (InterruptedException e) {
            System.out.println("Watcher Joined failed " + e);
        } finally {
            logger.close();
            System.out.println(watchedThread.getName() + " Successfully closed FileWriter.");
        }
    }
}
