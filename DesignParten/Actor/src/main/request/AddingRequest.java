package main.request;

import main.activeobject.ActiveObject;
import main.future.FutureResult;
import main.future.Result;

/**
 * @author: haoliu on 19/04/2018 16:54
 */
public class AddingRequest implements Request {
    private final ActiveObject servant;
    private final String x;
    private final String y;
    private final FutureResult<String> futureResult;

    public AddingRequest(String x, String y, ActiveObject servant, FutureResult<String> result) {
        this.x = x;
        this.y = y;
        this.servant = servant;
        this.futureResult = result;
    }

    @Override
    public void execute() {
        final Result<String> result = servant.add(x, y);
        futureResult.setRealResult(result);
    }
}
