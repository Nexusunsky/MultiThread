package practice.magic;

public class Blackhole {

    /**
     * 观察可知目标是实现:
     * 锁住obj在magic方法里面，让任何线程都不能获取到obj的锁，
     * 从而阻塞在synchronized (obj)
     * 但是又需要放行mainThread通过；
     */
    public static void enter(Object obj) {
        System.out.println("Step 1");
        magic(obj);
        System.out.println("Step 2");
        synchronized (obj) {
            System.out.println("Step 3 (never reached here)");
        }
    }

    public static void magic(final Object obj) {
        final Object lock = new Object();
        Thread lockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //需要将obj锁在这个线程当中
                synchronized (lock) {
                    lock.notifyAll();
                }
                synchronized (obj) {
                    while (true) {
                    }
                }
            }
        });
        synchronized (lock) {
            try {
                lockThread.start();
                lock.wait();
                //实现让mainThread通过此次
            } catch (InterruptedException e) {
            }
        }
    }
}