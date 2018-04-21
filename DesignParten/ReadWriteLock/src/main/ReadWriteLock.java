package main;

/**
 * 同时可多读，
 * 同时仅独写
 *
 * @author haoliu
 */
public class ReadWriteLock {
    private int writingWriters = 0;
    private int readingReaders = 0;
    private int waitingWriters = 0;
    private boolean preferWriting = true;

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriting && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;
    }

    public synchronized void unReadLock() {
        readingReaders--;
        preferWriting = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    public synchronized void unWriteLock() {
        writingWriters--;
        preferWriting = false;
        notifyAll();
    }
}
