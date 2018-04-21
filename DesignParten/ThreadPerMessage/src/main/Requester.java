package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Requester {
    public static void main(String[] args) {
        System.out.println("main BEGIN");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Host host = new Host(threadPool);
        try {
            host.request(10, 'A');
            host.request(20, 'B');
            host.request(30, 'C');
        } finally {
            threadPool.shutdown();
        }
        System.out.println("main END");
    }
}
