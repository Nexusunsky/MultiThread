package main.twophase;

/**
 * 负责发出Termination请求的对象
 *
 * @author: haoliu on 17/04/2018 12:26
 */
public class TerminationRequester {
    public static void main(String[] args) {
        System.out.println("main : BEGIN");

        try {
            CountThread countWorker = new CountThread();
            countWorker.start();

            System.out.println("main: sleep");
            Thread.sleep(10000);

            System.out.println("main: shutdownRequest");
            countWorker.shutdownRequest();

            System.out.println("main:join");
            countWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main : END");
    }
}
