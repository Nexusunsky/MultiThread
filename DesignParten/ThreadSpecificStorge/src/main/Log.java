package main;

/**
 * 打印接口
 *
 * @author: haoliu on 18/04/2018 10:26
 */
public interface Log {
    /**
     * 将日志打印到文件
     *
     * @param content 需要打印的内容
     */
    void printIn(String content);

    /**
     * 关闭流
     */
    void close();
}
