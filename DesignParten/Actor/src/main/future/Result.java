package main.future;

/**
 * @author: haoliu on 19/04/2018 13:21
 */
public interface Result<T> {

    /**
     * 获取数据
     *
     * @return T 数据类型
     */
    T get();
}
