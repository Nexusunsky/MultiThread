package main;

/**
 * 工作线程
 *
 * @author: haoliu on 18/04/2018 10:21
 */
public class ClientThread extends Thread {

    public ClientThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " BEGIN ");
        for (int i = 0; i < 10; i++) {
            Logger localLogger = new Logger();
            try {
                localLogger.printIn("content = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(" println failed e = " + e);
            } finally {
                localLogger.close();
            }
        }
        System.out.println(Thread.currentThread().getName() + " END ");
    }
}
