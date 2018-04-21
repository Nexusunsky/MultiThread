package practice;

public class TestMain {
    public static void main(String[] args) {
        ThreadPerMessageChannel channel = new ThreadPerMessageChannel(20);
        new ThreadPerMessageClient("Alice", channel).start();
        new ThreadPerMessageClient("Bobby", channel).start();
        new ThreadPerMessageClient("Chris", channel).start();
        while (true){
            channel.takeToExecute();
        }
    }
}
