package practice;

import main.IGate;
import sun.awt.Mutex;

/**
 * Mutex is Mutual Exclusion（互斥）
 */
public class MutexGate implements IGate {
    private int count = 0;
    private String name;
    private String address;
    private final Mutex mutex = new Mutex();

    @Override
    public void pass(final String name, final String address) {
        mutex.lock();
        try {
            this.count++;
            this.name = name;
            this.address = address;
            checkout();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public String toString() {
        mutex.lock();
        try {
            return "NO." + count + " : " + name + " , " + address;
        } finally {
            mutex.unlock();
        }
    }

    private void checkout() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("*****  BROKEN *****" + toString());
        }
    }
}
