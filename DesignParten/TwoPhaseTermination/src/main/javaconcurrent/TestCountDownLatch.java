package main.javaconcurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
    private static final int TASKS = 5;

    public static void main(String[] args) {
        System.out.println("TestCountDownLatch: BEGIN");
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(TASKS);

        try {
            for (int i = 0; i < TASKS; i++) {
                service.execute(new CountDownTask(latch, i));
            }

            System.out.println("CountDownLatch:AWAIT");
            latch.await();
        } catch (InterruptedException e) {
        } finally {
            service.shutdown();
            System.out.println("TestCountDownLatch: END");
        }
    }

    private static class CountDownTask implements Runnable {
        private final CountDownLatch latch;
        private final int content;
        private static final Random random = new Random(314159);

        public CountDownTask(final CountDownLatch latch, final int i) {
            this.latch = latch;
            this.content = i;
        }

        @Override
        public void run() {
            doTask();
            latch.countDown();
        }

        private void doTask() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ":CountDownTask:BEGIN: content = " + content);
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
            } finally {
                System.out.println(name + ":CountDownTask:END: content = " + content);
            }
        }
    }
}
