package main.templet2;

/**
 * @author: haoliu on 17/04/2018 13:17
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("TestMain : BEGIN");
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                System.out.println("*******");
                System.out.println("UncaughtExceptionHandler:BEGIN");
                System.out.println("currentThread = " + Thread.currentThread());
                System.out.println("thread = " + t);
                System.out.println("exception = " + e);
                System.out.println("UncaughtExceptionHandler:END");
            }
        });


        Runtime.getRuntime().addShutdownHook(new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("*******");
                        System.out.println("ShutdownHook:BEGIN");
                        System.out.println("currentThread = " + Thread.currentThread());
                        System.out.println("ShutdownHook:END");
                    }
                }
        ));

        new Thread("MyThread") {
            @Override
            public void run() {
                super.run();
                System.out.println("MyThread : BEGIN");

                System.out.println("MyThread : SLEEP...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }

                System.out.println("MyThread : DIVIDE");
                int x = 1 / 0;

                System.out.println("MyThread not go this: END");
            }
        }.start();

        System.out.println("TestMain : END");
    }
}
