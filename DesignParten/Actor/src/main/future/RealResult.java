package main.future;

/**
 * 真实数据
 *
 * @author: haoliu on 19/04/2018 13:25
 */
public class RealResult<T> implements Result<T> {
    private final T result;

    public RealResult(T result) {
        this.result = result;
    }

    @Override
    public T get() {
        return result;
    }
}
