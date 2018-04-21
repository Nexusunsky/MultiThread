package practice;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("TestMain : BEGIN");

        try {
            CountThreadWithoutFlag countWorker = new CountThreadWithoutFlag();
            countWorker.start();

            System.out.println("TestMain: sleep");
            Thread.sleep(10000);

            System.out.println("TestMain: shutdownRequest");
            countWorker.shutdownRequest();

            System.out.println("TestMain:join");
            countWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TestMain : END");
    }
}
