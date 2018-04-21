package main;

public class UnsafeGate implements IGate {
    private int count = 0;
    private String name;
    private String address;

    @Override
    public void pass(final String name, final String address) {
        this.count++;
        this.name = name;
        this.address = address;
        checkout();
    }

    @Override
    public String toString() {
        return "NO." + count + " : " + name + " , " + address;
    }

    private void checkout() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("*****  BROKEN *****" + toString());
        }
    }
}
