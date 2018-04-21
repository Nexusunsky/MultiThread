package practice;

import main.temple.IData;
import main.temple.Server;

/**
 * Practice 9-4
 *
 * @author: haoliu on 16/04/2018 15:42
 */
public class PracticeMain {
    public static void main(String[] args) {
        Server server = new Server();
        IData data = server.request(-1, 'N');
        System.out.println("data = " + data.getContent());
    }
}
