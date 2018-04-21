package main;

public class Request {
    private final String name;

    public Request(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request " + name + " ]";
    }
}
