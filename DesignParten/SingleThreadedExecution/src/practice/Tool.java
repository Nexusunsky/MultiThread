package practice;

public class Tool {
    private final String name;

    public Tool(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + name + "] ";
    }
}
