package practice;

public class TestMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Host.execute(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is Interrupted." + e);
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }

        System.out.println("Interrupted");
        thread.interrupt();
    }
}
