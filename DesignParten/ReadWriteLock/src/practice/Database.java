package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void clear() {
        writeLock.lock();
        try {
            timeToWait(500);
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public void assign(K key, V value) {
        writeLock.lock();
        try {
            timeToWait(500);
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V retrieve(K key) {
        readLock.lock();
        try {
            timeToWait(500);
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    private void timeToWait(int await) {
        try {
            Thread.sleep(await);
        } catch (InterruptedException e) {
        }
    }
}
