package main.exchange;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ProducerExchanger extends Thread {
    private final Exchanger<char[]> exchanger;
    private final Random random;
    private char[] buffer;
    private char index;

    public ProducerExchanger(final Exchanger<char[]> exchanger, final char[] buffer) {
        super(ProducerExchanger.class.getName());
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
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = nextChar();
                    System.out.println(Thread.currentThread().getName() + ": " + buffer[i] + "-->");
                }
                System.out.println(Thread.currentThread().getName() + " :BEGIN exchange Buffer is" + buffer.toString());
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " :After exchange Buffer is" + buffer.toString());
            }
        } catch (InterruptedException e) {
        }
    }

    private char nextChar() throws InterruptedException {
        char c = (char) ('A' + index % 26);
        index++;
        Thread.sleep(random.nextInt(1000));
        return c;
    }
}
