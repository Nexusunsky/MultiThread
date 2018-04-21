package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 线程特有的Logger
 *
 * @author: haoliu on 18/04/2018 10:25
 */
public class TSLogger implements Log {

    private final FileWriter writer;
    private final String threadName;

    public TSLogger(final String name) throws IOException {
        File log = new File(name + "-Log.txt");
        if (!log.exists()) {
            if (!log.createNewFile()) {
                throw new NullPointerException("Create Log failed.");
            }
        }
        this.threadName = name;
        this.writer = new FileWriter(log);
    }

    @Override
    public void printIn(String content) {
        try {
            writer.append(threadName + " printIn: " + content);
        } catch (IOException e) {
            System.out.println("TSLogger.printIn : " + e);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("TSLogger.close : " + e);
        }
    }
}
