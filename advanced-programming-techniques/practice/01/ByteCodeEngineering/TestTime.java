import javassist.*;

public class TestTime {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("ciao");
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

    }
}