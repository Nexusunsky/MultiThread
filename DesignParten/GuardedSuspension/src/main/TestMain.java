package main;

public class TestMain {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Client").start();
        new ServerThread(queue, "Server").start();
    }
}
