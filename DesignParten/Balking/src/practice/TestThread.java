package practice;

/**
 * 对同一个Thread对象，只能调用它的start方法一次，
 * 当Thread对象调用了start方法后，会变成start终止状态
 * 当再次调用start方法时，，为防止线程再次启动，线程会进行balk。并抛出异常；
 * Thread的start使用Bulk模式；
 *
 * @author: haoliu on 31/03/2018 13:32
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("BEGIN");
        for (int i = 0; i < 50; i++) {
            System.out.println(".");
        }
        System.out.println("END");
    }

    public static void main(String[] args) {
        TestThread thread = new TestThread();
        while (true) {
            thread.start();
        }
    }
}
