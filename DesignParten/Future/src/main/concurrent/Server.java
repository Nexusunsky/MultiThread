package main.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.temple.IData;
import main.temple.RealData;

public class Server {
    private final ExecutorService service = Executors.newCachedThreadPool();

    public IData request(final int count, final char c) {
        System.out.println("   request(" + count + "," + c + ") BEGIN");

        FutureData futureData = new FutureData(() -> new RealData(count, c));
        service.submit(futureData);

        System.out.println("   request(" + count + "," + c + ") END");

        return futureData;
    }
}
