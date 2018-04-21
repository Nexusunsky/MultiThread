package main.temple;

/**
 * 实际数据的提货单
 *
 * @author: haoliu on 16/04/2018 10:48
 */
public class FutureData implements IData<String> {
    private RealData realData = null;
    private boolean isSettled = false;

    public synchronized void setRealData(RealData realData) {
        if (this.isSettled && this.realData != null) {
            return;
        }
        this.realData = realData;
        this.isSettled = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        if (this.realData == null) {
            throw new NullPointerException("RealData is NULL.");
        }
        while (!isSettled) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "is Interrupted by: " + e.getMessage());
            }
        }
        return realData.getContent();
    }
}
