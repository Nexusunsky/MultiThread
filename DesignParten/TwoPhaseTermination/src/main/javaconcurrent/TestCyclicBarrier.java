package main.javaconcurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {

    private static final int THREADS = 5;

    public static void main(String[] args) {
        System.out.println("TestCyclicBarrier: BEGIN");

        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier Action.");
            }
        };

        CyclicBarrier phaseBarrier = new CyclicBarrier(THREADS, barrierAction);

        CountDownLatch latch = new CountDownLatch(THREADS);

        try {
            for (int i = 0; i < THREADS; i++) {
                service.execute(new BarrierTask(phaseBarrier, latch, i));
            }

            System.out.println("CountDownLatch:AWAIT");
            latch.await();
        } catch (InterruptedException e) {
        } finally {
            service.shutdown();
            System.out.println("TestCyclicBarrier: END");
        }
    }

    private static class BarrierTask implements Runnable {
        private static final Random random = new Random(314159);
        private static final int PHASE = 5;

        private final CyclicBarrier phaseBarrier;
        private final CountDownLatch latch;
        private final int content;


        public BarrierTask(final CyclicBarrier phaseBarrier, final CountDownLatch latch, final int i) {
            this.phaseBarrier = phaseBarrier;
            this.latch = latch;
            content = i;
        }

        @Override
        public void run() {
            for (int i = 0; i < PHASE; i++) {
                doPhase(i);
                try {
                    phaseBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }

        private void doPhase(final int i) {
            String name = Thread.currentThread().getName();
            System.out.println(name + ":BarrierTask:BEGIN: content = " + content + " ,Phase" + i);
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
            } finally {
                System.out.println(name + ":BarrierTask:END: content = " + content + " ,Phase" + i);
            }
        }
    }
}
