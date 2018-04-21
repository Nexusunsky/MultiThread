package practice.webmocker;

import java.io.IOException;

public class TestMain {
    public static void main(String args[]) {
        try {
            new MiniServer(8888).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
