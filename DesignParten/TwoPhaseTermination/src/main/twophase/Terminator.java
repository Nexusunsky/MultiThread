package main.twophase;

/**
 * 负责接收终止请求，并实际执行终止处理
 *
 * @author: haoliu on 17/04/2018 12:27
 */
public interface Terminator {
    /**
     * 提供shutdown的请求
     */
    void shutdownRequest();

    /**
     * 判断是否接收了shutdown的请求
     *
     * @return boolean true：表示请求过
     */
    boolean isShutdownRequested();
}
