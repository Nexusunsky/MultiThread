package main;

import main.activeobject.ActiveObject;
import main.future.Result;

/**
 * @author: haoliu on 19/04/2018 16:43
 */
class AddClientThread extends Thread {
    private ActiveObject proxy;
    private String x = "1";
    private String y = "1";

    public AddClientThread(final String name, final ActiveObject proxy) {
        super(name);
        this.proxy = proxy;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (; ; ) {
                // 有返回值的调用
                Result<String> result = proxy.add(x, y);
                Thread.sleep(100);
                String z = result.get();
                System.out.println(Thread.currentThread().getName() + ": " + x + " + " + y + " = " + z);
                x = y;
                y = z;
            }
        } catch (InterruptedException e) {
            System.out.println("AddClientThread [ add ] failed." + e);
        }
    }
}
