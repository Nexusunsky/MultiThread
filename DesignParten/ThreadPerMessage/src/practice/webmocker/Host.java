package practice.webmocker;

import java.io.IOException;
import java.net.Socket;

public class Host {
    public void request(Socket clientSocket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Service.service(clientSocket);
                } catch (IOException e) {
                }
            }
        }).start();
    }
}
