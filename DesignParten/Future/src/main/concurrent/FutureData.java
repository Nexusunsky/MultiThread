package main.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import main.temple.IData;
import main.temple.RealData;

/**
 * @author: haoliu on 16/04/2018 14:01
 */
public class FutureData extends FutureTask<RealData> implements IData<String> {

    public FutureData(final Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        try {
            return get().getContent();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("getContent failed. by: " + e);
        }
        return null;
    }
}
