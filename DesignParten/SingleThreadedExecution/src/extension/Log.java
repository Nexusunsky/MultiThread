package extension;

public class Log {
    public static void printLn(String s) {
        System.out.println(Thread.currentThread().getName() + " : " + s);
    }
}
