import java.util.Arrays;
import java.util.stream.*;

public class test {
    public static int sum(int a, int b) {
        return a+b;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2};
        Integer[] b = new Integer[]{1,2};
        double[] c = new double[]{1.0f, 2.0f};
        Double[] d = new Double[]{1.0, 2.0};

        Arrays.stream(a).forEach(System.out::println); 
        IntStream.of(a).forEach(System.out::println);
        // Arrays.stream(c).forEach(System.out::println); FLOAT ????????????????????????????????????????????????
        DoubleStream.of(c).forEach(System.out::println);
        Stream.of(d).forEach(System.out::println);
    
        char myc = 'a';
        System.out.println((int)myc);
        char myc2 = 97;
        System.out.println(myc2);

        int myi = 97;
        System.out.println((char)myi);
    }
}