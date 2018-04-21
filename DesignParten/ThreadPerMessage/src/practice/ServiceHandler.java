package practice;

public class ServiceHandler {
    private final Service service = new Service();

    public void handle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               service.service();
            }
        }).start();
    }
}
