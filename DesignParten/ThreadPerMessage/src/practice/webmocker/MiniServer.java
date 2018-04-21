package practice.webmocker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {
    private final int portnumber;
    private final Host host = new Host();

    public MiniServer(int portnumber) {
        this.portnumber = portnumber;
    }

    /**
     * Socket 建立通信的通道只需要一次即可，
     * 真正需要的是在这条通道上执行的请求内容；
     * 因此问题的关键是将需要基于已有的socket链接而将操作放在多个线程中去完成，
     * 从而实现多个web浏览器的访问；
     */
    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portnumber);
        System.out.println("Listening on " + serverSocket);
        try {
            while (true) {
                System.out.println("Accepting...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket);
                host.request(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}