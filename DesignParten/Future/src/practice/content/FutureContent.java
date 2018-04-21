package practice.content;

/**
 * 实际内容的Future
 *
 * @author: haoliu on 16/04/2018 15:11
 */
public class FutureContent implements Content {
    private Content asyncContent;
    private boolean isReady = false;

    public synchronized void setContent(Content content) {
        if (ready()) {
            //防止被重复设置值
            return;
        }
        asyncContent = content;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized byte[] getBytes() {
        try {
            while (!ready()) {
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Get Content failed by " + e.getMessage());
        }
        return asyncContent.getBytes();
    }

    private boolean ready() {
        return isReady && asyncContent != null;
    }
}
