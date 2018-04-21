package main.temple;

/**
 * VirtualData 接口
 * 统一数据类型
 *
 * @author haoliu
 */
public interface IData<T> {
    /**
     * 获取返回值类型
     *
     * @return T 需要的值类型
     */
    T getContent();
}
