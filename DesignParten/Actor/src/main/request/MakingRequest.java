package main.request;

import main.activeobject.ActiveObject;
import main.future.FutureResult;
import main.future.RealResult;

/**
 * @author: haoliu on 19/04/2018 13:19
 */
public class MakingRequest implements Request {
    private final FutureResult future;
    private final String sources;
    private final ActiveObject servant;

    public MakingRequest(final String source, final ActiveObject servant, final FutureResult future) {
        this.future = future;
        this.sources = source;
        this.servant = servant;
    }

    @Override
    public void execute() {
        final String content = servant.makeContent(sources);
        future.setRealResult(new RealResult(content));
    }
}
