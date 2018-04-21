package main;

public class TableClearer extends Thread {

    private final Table table;

    public TableClearer(final Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(100);
                table.clear();
                System.out.println("Clear Table is working.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
