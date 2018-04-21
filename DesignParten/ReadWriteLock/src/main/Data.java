package main;

public class Data {
    private final char[] buffers;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffers = new char[size];
        for (int i = 0; i < size; i++) {
            buffers[i] = '*';
        }
    }

    public /*synchronized*/ char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.unReadLock();
        }
    }

    public /*synchronized*/ void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.unWriteLock();
        }
    }

    private void doWrite(final char c) {
        for (int i = 0; i < buffers.length; i++) {
            buffers[i] = c;
            slowly();
        }
    }

    private char[] doRead() {
        char[] temp = new char[buffers.length];
        for (int i = 0; i < buffers.length; i++) {
            temp[i] = buffers[i];
        }
        slowly();
        return temp;
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }
}
