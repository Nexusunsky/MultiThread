package main;

public class TestMain {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorking();
        new Client("Alice", channel).start();
        new Client("Bobby", channel).start();
        new Client("Chris", channel).start();
    }
}
