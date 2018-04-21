package main;

public class Table {
    private final String[] buffers;
    private int tail = 0;
    private int head = 0;
    private int count = 0;

    public Table(final int size) {
        this.buffers = new String[size];
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buffers.length) {
            wait();
            System.out.println(Thread.currentThread().getName() + " is Waiting for put.");
        }
        buffers[tail] = cake;
        tail = (tail + 1) % buffers.length;
        count++;
        notifyAll();
        //        notify();
    }

    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            wait();
            System.out.println(Thread.currentThread().getName() + " is Waiting for take.");
        }
        String cake = buffers[head];
        head = (head + 1) % buffers.length;
        count--;
        notifyAll();
        //        notify();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }

    public synchronized void clear() {
        count = 0;
        tail = 0;
        head = 0;
        notifyAll();
    }
}
