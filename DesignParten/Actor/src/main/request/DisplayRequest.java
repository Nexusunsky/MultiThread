package main.request;

import main.activeobject.ActiveObject;

/**
 * @author: haoliu on 19/04/2018 13:20
 */
public class DisplayRequest implements Request {
    private final String sources;
    private final ActiveObject servant;

    public DisplayRequest(final String source, final ActiveObject servant) {
        this.sources = source;
        this.servant = servant;
    }

    @Override
    public void execute() {
        servant.displayContent(sources);
    }
}
