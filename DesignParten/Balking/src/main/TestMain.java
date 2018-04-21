package main;

public class TestMain {
    public static void main(String[] args) {
        final Data data = new Data("./Balking/data.txt", "(empty)");
        new ChangerThread(data).start();
        new SaverThread(data).start();
    }
}