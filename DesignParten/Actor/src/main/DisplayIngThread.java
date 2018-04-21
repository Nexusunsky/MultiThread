package main;

import main.activeobject.ActiveObject;

/**
 * @author: haoliu on 19/04/2018 14:25
 */
public class DisplayIngThread extends Thread {
    private ActiveObject proxy;

    public DisplayIngThread(final String name, final ActiveObject proxy) {
        super(name);
        this.proxy = proxy;
    }

    @Override
    public void run() {
        super.run();
        proxy.displayContent(getName());
    }
}
