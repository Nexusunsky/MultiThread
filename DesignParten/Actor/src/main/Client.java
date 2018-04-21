package main;

import main.activeobject.Proxy;

/**
 * @author: haoliu on 19/04/2018 13:45
 */
public class Client {
    public static void main(String[] args) {
        Proxy activeProxy = new Proxy();
        new AddClientThread("Diana", activeProxy).start();
        new MakingThread("Alice", activeProxy).start();
        new DisplayIngThread("Bobboy", activeProxy).start();
        new MakingThread("Chris", activeProxy).start();
        new DisplayIngThread("Dogged", activeProxy).start();
        new MakingThread("Eeason", activeProxy).start();
        new MakingThread("Fotun", activeProxy).start();
        new DisplayIngThread("Goson", activeProxy).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        } finally {
            activeProxy.cancel();
        }
    }
}
