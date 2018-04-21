package main;

public class UserThread extends Thread {

    private final IGate gate;
    private final String pName;
    private final String pAddress;

    public UserThread(final IGate iGate, final String pName, final String pAddress) {
        gate = iGate;
        this.pName = pName;
        this.pAddress = pAddress;
    }

    @Override
    public void run() {
        System.out.println(pName + "BEGIN");
        while (true) {
            try {
                //减少程序的执行次数，便可以提前暴露出线程不安全的问题；
                Thread.sleep(3000);
                gate.pass(pName, pAddress);
            } catch (InterruptedException e) {
            }
        }
    }
}
