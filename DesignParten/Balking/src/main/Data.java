package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {
    private final String fileName;
    private String content;
    private boolean changed;

    public Data(final String fileName, final String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        content = newContent;
        changed = true;
    }

    public /*synchronized*/ void save() throws IOException, InterruptedException {
        if (!changed) {
            System.out.println(Thread.currentThread().getName() + " calls doSave is Balked.");
            return;
        }
        Thread.sleep(500);
        doSave();
        changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        String fileName = this.fileName;
        Writer writer = new FileWriter(fileName);
        try {
            writer.write(content);
        } finally {
            writer.close();
        }
    }
}

