package practice;

/**
 * 实验是否加锁对于程序性能的影响
 *
 * @author haoliu
 */
public class TestPerformances {
    public static void main(String[] args) {
        trial("NotSync", CALL_COUNT, new NotSync());
        trial("Sync", CALL_COUNT, new Sync());
    }

    private static final long CALL_COUNT = 1000000000L;

    private static void trial(String message, long count, Object obj) {
        System.out.println(message + " :BEGIN ");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            obj.toString();
        }
        System.out.println(message + " :END");
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - startTime) + "msec.");
    }

    static class NotSync {
        private final String name = "NotSync";

        @Override
        public String toString() {
            return "[ " + name + " ]";
        }
    }

    static class Sync {
        private final String name = "Sync";

        @Override
        public synchronized String toString() {
            return "[ " + name + " ]";
        }
    }
}
