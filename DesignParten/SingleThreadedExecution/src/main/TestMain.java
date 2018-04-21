package main;

public class TestMain {

    public static void main(String[] args) {
        final IGate gate = new UnsafeGate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }
}
