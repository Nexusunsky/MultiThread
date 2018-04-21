package main;

import main.activeobject.ActiveObject;

/**
 * @author: haoliu on 19/04/2018 14:24
 */
public class MakingThread extends Thread {
    private ActiveObject proxy;

    public MakingThread(final String name, final ActiveObject proxy) {
        super(name);
        this.proxy = proxy;
    }

    @Override
    public void run() {
        super.run();
        final String content = proxy.makeContent(getName());
        System.out.println(getName() + " is displaying " + content);
    }
}
