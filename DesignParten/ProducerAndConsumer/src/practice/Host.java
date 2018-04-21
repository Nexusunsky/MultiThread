package practice;

public class Host {
    public static void execute(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            if (Thread.interrupted()) {
                throw new InterruptedException("Host execute is Cancel.");
            }
            doHeavyJob();
        }
    }

    private static void doHeavyJob() {
        System.out.println("doHeavyJob BEGIN");
        long start = System.currentTimeMillis();
        while (start + 1000 > System.currentTimeMillis()) {
            //Busy Job
        }
        System.out.println("doHeavyJob END");
    }
}
