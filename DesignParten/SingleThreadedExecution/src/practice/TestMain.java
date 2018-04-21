package practice;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(" Testing EaterThread ");
        Tool spoon = new Tool("Spoon");
        Tool fork = new Tool("Fork");
        new Eater("Alice", spoon, fork).start();
        new Eater("Bobby", fork, spoon).start();
    }
}
