package main;

import java.util.Random;

public class Request {
    private final String name;
    private final int id;
    private final Random random = new Random();

    public Request(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " [execute] " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            System.out.println(name + "[ Request is stop ]");
        }
    }

    @Override
    public String toString() {
        return " [ Request from " + name + " No. " + id + " ]";
    }
}
