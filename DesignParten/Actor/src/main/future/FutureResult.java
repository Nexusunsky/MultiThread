package main.future;

/**
 * 期货数据
 *
 * @author: haoliu on 19/04/2018 13:24
 */
public class FutureResult<T> implements Result<T> {
    private RealResult<T> realResult;
    private boolean isGet = false;

    public synchronized void setRealResult(Result<T> realResult) {
        if (resultAvailable()) {
            return;
        }
        this.realResult = (RealResult<T>) realResult;
        isGet = true;
        notifyAll();
    }

    @Override
    public synchronized T get() {
        while (!resultAvailable()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("FutureResult [ get ] failed." + e);
            }
        }
        return realResult.get();
    }

    private boolean resultAvailable() {
        return isGet && realResult != null;
    }
}
