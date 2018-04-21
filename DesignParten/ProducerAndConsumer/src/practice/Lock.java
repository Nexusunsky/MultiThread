package practice;

/**
 * 安全的定时锁
 *
 * @author haoliu
 */
public class Lock {
    public static void sleepLock(long sleepTime) throws InterruptedException {
        if (sleepTime != 0) {
            Object lock = new Object();
            synchronized (lock) {
                lock.wait(sleepTime);
            }
        }
    }
}
