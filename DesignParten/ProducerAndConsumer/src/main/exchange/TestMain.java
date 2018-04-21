package main.exchange;

import java.util.concurrent.Exchanger;

/**
 * 使用Exchanger来进行跨线程的对象交换，
 * 当线程的一段开始请求交换时，会等待线程另一端调用，然后进行线程间的对象交换
 *
 * @author haoliu
 */
public class TestMain {
    public static void main(String[] args) {
        Exchanger<char[]> exchanger = new Exchanger<>();
        char[] buffer1 = new char[10];
        char[] buffer2 = new char[10];
        new ProducerExchanger(exchanger, buffer1).start();
        new ConsumerExchanger(exchanger, buffer2).start();
    }
}
