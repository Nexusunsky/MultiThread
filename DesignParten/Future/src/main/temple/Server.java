package main.temple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建真实数据的服务类
 *
 * @author: haoliu on 16/2018 10:52
 */
public class Server {
    private final ExecutorService service = Executors.newCachedThreadPool();

    public IData request(final int count, final char c) {
        System.out.println("   request(" + count + "," + c + ") BEGIN");
        final FutureData futureData = new FutureData();
        service.submit(() -> {
            //异步线程创建实际的数据
            RealData realData = null;
            try {
                realData = new RealData(count, c);
            } catch (Exception e) {
                System.out.println("New RealData getException " + e.getMessage());
            } finally {
                futureData.setRealData(realData);
            }
        });
        System.out.println("   request(" + count + "," + c + ") END");
        return futureData;
    }
}
