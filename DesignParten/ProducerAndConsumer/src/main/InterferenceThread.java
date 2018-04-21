package main;

public class InterferenceThread extends Thread {
    private final Table table;

    public InterferenceThread(final Table table) {
        super("InterferenceThread");
        this.table = table;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                synchronized (table) {
                    table.wait();
                }
                System.out.println(getName() + " is notified.");
            } catch (InterruptedException e) {
            }
        }
    }
}
