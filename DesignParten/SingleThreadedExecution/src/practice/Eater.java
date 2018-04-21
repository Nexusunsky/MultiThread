package practice;

public class Eater extends Thread {
    private String name;
    private final Tool leftHand;
    private final Tool rightHand;

    public Eater(final String name, final Tool leftHand, final Tool rightHand) {
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            eat();
        }
    }

    private void eat() {
        synchronized (leftHand) {
            System.out.println(name + " takes up " + leftHand + " (left). ");
            synchronized (rightHand) {
                System.out.println(name + " takes up " + rightHand + " (right). ");
                System.out.println(name + " is eating now ,yum ,yum! ");
                System.out.println(name + " puts down " + rightHand + " (right). ");
            }
            System.out.println(name + " puts down " + leftHand + " (left). ");
        }
    }

    /**
     * Handling DiedLock
     */
    private synchronized void safeEat() {
        System.out.println(name + " takes up " + leftHand + " (left). ");
        System.out.println(name + " takes up " + rightHand + " (right). ");
        System.out.println(name + " is eating now ,yum ,yum! ");
        System.out.println(name + " puts down " + rightHand + " (right). ");
        System.out.println(name + " puts down " + leftHand + " (left). ");
    }
}
