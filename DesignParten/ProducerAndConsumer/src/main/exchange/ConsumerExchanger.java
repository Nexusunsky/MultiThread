package main.exchange;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ConsumerExchanger extends Thread {
    private final Exchanger<char[]> exchanger;
    private final Random random;
    private char[] buffer;
    private char index;

    public ConsumerExchanger(final Exchanger<char[]> exchanger, final char[] buffer) {
        super(ConsumerExchanger.class.getName());
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.index = 0;
        this.random = new Random(1000);
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " :BEGIN exchange Buffer is" + buffer.toString());
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " :After exchange Buffer is" + buffer.toString());

                for (final char aBuffer : buffer) {
                    System.out.println(Thread.currentThread().getName() + ": " + aBuffer + "-->");
                    Thread.sleep(random.nextInt(1000));
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
