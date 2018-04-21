package extension;

/**
 * 信号量的使用
 *
 * @author: haoliu on 26/03/2018 22:45
 */
public class TestMain {
    public static void main(String[] args) {
        BoundedResource resource = new BoundedResource(3);
        for (int i = 0; i < 10; i++) {
            //创建10个线程对象执行UserThread；
            new UserThread(resource).start();
        }
    }
}
