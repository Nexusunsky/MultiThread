package practice;

/**
 * 猜测运行的结果
 */
public class PuzzleMain {
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //if (Thread.interrupted()) {
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException();
                        }
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        System.out.print("*");
                    }
                }
            }
        };

        // 启动线程
        thread.start();

        // 等待5秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        // 只interrupt线程一次
        thread.interrupt();
    }
}