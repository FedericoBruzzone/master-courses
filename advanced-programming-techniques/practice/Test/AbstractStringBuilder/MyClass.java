//import java.lang.reflect.*;

public class MyClass {
    public static void main(String[] args) {
        Class<?> cls = StringBuilder.class.getSuperclass();
        System.out.println(cls.getName());
    }
}
