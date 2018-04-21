package practice.gracefuldemo;

import main.twophase.GracefulThread;

public class ServiceRequester {
    private static GracefulThread thread;

    public static void service() {
        if (isServiceRunning()) {
            System.out.println("service is balked.");
            return;
        }
        System.out.print("service.");
        thread = new ServiceThread();
        thread.start();
    }

    public static void cancel() {
        if (isServiceRunning()) {
            System.out.print("cancel.");
            thread.shutdownRequest();
        }
    }

    private static boolean isServiceRunning() {
        return thread != null && thread.isAlive();
    }
}