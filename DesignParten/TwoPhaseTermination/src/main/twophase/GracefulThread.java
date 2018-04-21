package main.twophase;

/**
 * 优雅的线程终止类
 *
 * @author haoliu
 */
public abstract class GracefulThread extends Thread implements Terminator {
    private volatile boolean shutdownRequested = false;

    @Override
    public final void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    @Override
    public final boolean isShutdownRequested() {
        return shutdownRequested;
    }

    @Override
    public final void run() {
        try {
            while (!isShutdownRequested()) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    /**
     * 执行线程工作
     *
     * @throws InterruptedException
     */
    protected abstract void doWork() throws InterruptedException;

    /**
     * 处理在线程终止时的工作
     */
    protected abstract void doShutdown();
}