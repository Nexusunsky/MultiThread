package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Thread-Specific-Storage的类
 *
 * @author: haoliu on 18/04/2018 10:20
 */
public class Main {
    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        SERVICE.submit(new ClientThread("Alice"));
        SERVICE.submit(new ClientThread("Bobby"));
        SERVICE.submit(new ClientThread("Chris"));
    }
}
