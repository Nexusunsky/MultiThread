package main;

public class TestMain {
    public static void main(String[] args) {
        Table table = new Table(3);
        new Producer(table).start();
        new Producer(table).start();
        new Producer(table).start();
        new Consumer(table).start();
        new Consumer(table).start();
        new Consumer(table).start();
        new Consumer(table).start();
        new TableClearer(table).start();
        new InterferenceThread(table).start();
        new InterferenceThread(table).start();
    }
}
