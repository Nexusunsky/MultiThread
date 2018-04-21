package practice;

import java.io.IOException;

import main.Log;
import main.TSLogger;

public class WatchedLogger implements Log {

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
                Watcher.watchThread(thread, log);
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
