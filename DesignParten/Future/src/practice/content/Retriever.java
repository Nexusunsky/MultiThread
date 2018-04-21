package practice.content;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Future
 * Server类
 */
public class Retriever {
    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    public static Content retrieve(String urlStr) {
        Content futureContent = new FutureContent();
        SERVICE.submit(new Runnable() {
            @Override
            public void run() {
                ((FutureContent) futureContent).setContent(new SyncContentImpl(urlStr));
            }
        });
        return futureContent;
    }
}