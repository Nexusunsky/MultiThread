package main;

import java.io.IOException;

/**
 * 负责维护线程的集合
 *
 * @author: haoliu on 18/04/2018 10:23
 */
public class Logger implements Log {

    private static final ThreadLocal<Log> LOCALLOGS = new ThreadLocal<>();

    @Override
    public void printIn(String content) {
        getLog().printIn(content);
    }

    @Override
    public void close() {
        getLog().close();
        remove();
    }

    private static Log getLog() {
        Log log = LOCALLOGS.get();
        if (log == null) {
            try {
                final Thread thread = Thread.currentThread();
                log = new TSLogger(thread.getName());
                setLog(log);
            } catch (IOException e) {
                System.out.println("Logger : new TSLogger failed." + e);
            }
        }
        return log;
    }

    private static void setLog(Log log) {
        LOCALLOGS.set(log);
    }

    private static void remove() {
        LOCALLOGS.remove();
    }
}
