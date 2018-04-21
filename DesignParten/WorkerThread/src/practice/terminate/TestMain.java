package practice.terminate;

public class TestMain {
    public static void main(String[] args) {
        Channel channel = new Channel(5);   // 工人线程的个数
        channel.startWorking();
        Client alice = new Client("Alice", channel);
        Client bobby = new Client("Bobby", channel);
        Client chris = new Client("Chris", channel);
        alice.start();
        bobby.start();
        chris.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is Interrupted ");
        }
        alice.stopThread();
        bobby.stopThread();
        chris.stopThread();
        channel.stopAllWorkers();
    }
}