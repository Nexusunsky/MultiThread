package main.concurrent;

import main.temple.IData;

/**
 * 数据请求类
 *
 * @author haoliu
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(" Client BEGIN");

        Server host = new Server();
        IData<String> data1 = host.request(10, 'A');
        IData<String> data2 = host.request(20, 'B');
        IData<String> data3 = host.request(30, 'C');

        otherBlockingJobs();

        System.out.println("data1 = " + data1.getContent());
        System.out.println("data2 = " + data2.getContent());
        System.out.println("data3 = " + data3.getContent());

        System.out.println(" Client END");
    }

    private static void otherBlockingJobs() {
        System.out.println(" otherJob BEGIN");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(" otherJob END");
    }
}
