package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        SERVICE.submit(new WatchedClientThread("Alice"));
        SERVICE.submit(new WatchedClientThread("Bobby"));
        SERVICE.submit(new WatchedClientThread("Chris"));
    }
}
