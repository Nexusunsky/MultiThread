package main;

public class SafeGate implements IGate {
    /**
     * 将Gate类中的成员私有化，可以防止在继承的时候发生继续反常的现象；
     * 子类可能会将父类中线程安全的SharedResources变成线程非安全的；
     */
    private int count = 0;
    private String name;
    private String address;

    /**
     * pass在多个线程调用时，会发生多个线程并行修改SharedResources的风险存在
     * 所以需要使用synchronized
     */
    @Override
    public synchronized void pass(final String name, final String address) {
        this.count++;
        this.name = name;
        this.address = address;
        checkout();
    }

    /**
     * toString可能会同时在pass修改SharedResources时，
     * 读取这些SharedResources，所有需要使用synchronized
     */
    @Override
    public synchronized String toString() {
        return "NO." + count + " : " + name + " , " + address;
    }

    /**
     * 该方法的调用端已经在SingleThreadedExecution中
     * 所以不需要使用synchronized
     */
    private void checkout() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("*****  BROKEN *****" + toString());
        }
    }
}
