package main.activeobject;

import main.future.Result;

/**
 * Active接口：
 * 接收异步消息并处理
 *
 * @author haoliu
 */
public interface ActiveObject {

    /**
     * 生成内容
     *
     * @param source
     * @return string
     */
    String makeContent(final String source);

    /**
     * 显示内容
     *
     * @param source
     */
    void displayContent(final String source);

    /**
     * 实现 x,y 的加法
     *
     * @param x 加法x
     * @param y 加法y
     * @return Result<String>
     */
    Result<String> add(String x, String y);
}
