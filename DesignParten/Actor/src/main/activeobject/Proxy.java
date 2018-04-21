package main.activeobject;

import main.Scheduler;
import main.future.FutureResult;
import main.future.Result;
import main.request.AddingRequest;
import main.request.DisplayRequest;
import main.request.MakingRequest;

/**
 * @author: haoliu on 19/04/2018 12:25
 */
public class Proxy implements ActiveObject {
    private final Scheduler scheduler;
    private final Servant servant;

    public Proxy() {
        this.scheduler = new Scheduler();
        this.servant = new Servant();
        scheduler.start();
    }

    public void cancel() {
        scheduler.cancel();
    }

    @Override
    public String makeContent(final String source) {
        FutureResult<String> result = new FutureResult<>();
        scheduler.invoke(new MakingRequest(source, servant, result));
        return result.get();
    }

    @Override
    public void displayContent(final String source) {
        scheduler.invoke(new DisplayRequest(source, servant));
    }

    @Override
    public Result<String> add(final String x, final String y) {
        FutureResult<String> result = new FutureResult<>();
        scheduler.invoke(new AddingRequest(x, y, servant, result));
        return result;
    }
}
