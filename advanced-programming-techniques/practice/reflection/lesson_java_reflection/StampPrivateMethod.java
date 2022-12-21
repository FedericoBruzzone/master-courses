import java.lang.reflect.Field;
import java.util.stream.Stream;

class Ex {
    private String ex = "Ex private"; 

    public String myPublicMethodEx() {
        return "myPublicMethodEx";
    }

    private String myPrivateMethodEx() {
        return "myPrivateMethodEx";
    }
}

class MyClass extends Ex {
    private String m = "i'm a private field";
    public String f = "public";

    public String myPublicMethod() {
        return "myPublicMethod";
    }

    private String myPrivateMethod() {
        return "myPrivateMethod";
    }

    private String myPrivateMethod2() {
        return "myPrivateMethod2";
    }
}

public class StampPrivateMethod {
    public static void main(String[] args) throws Exception {
        String s_myClass = "MyClass";
        Class<?> myClass = Class.forName(s_myClass); 
        // Stream.of(i_myClass.getDeclaredMethods()).forEach(m -> System.out.println(m));
        
        // Stream.of(i_myClass.getDeclaredMethods())
        //     .filter(m -> (m.getModifiers() == Modifier.PRIVATE))
        //     .forEach(m -> System.out.println(m));
        
        Stream.of(myClass.getDeclaredFields()).forEach(System.out::println);
        
        Object i_myClass = myClass.getDeclaredConstructor().newInstance();
        Field private_field = myClass.getDeclaredField("m");
        System.out.println(private_field);
        private_field.setAccessible(true);
        System.out.println(private_field.get(i_myClass));
        private_field.set(i_myClass, "modified");
        System.out.println(private_field.get(i_myClass));
        
        // System.out.println("Dec");
        // Stream.of(i_myClass.getDeclaredFields()).forEach(System.out::println);
        // System.out.println();

        // System.out.println("No Dec");
        // Stream.of(i_myClass.getFields()).forEach(System.out::println);
        // System.out.println();

        // System.out.println("Dec");
        // Stream.of(i_myClass.getDeclaredMethods()).forEach(System.out::println);
        // System.out.println();

        // System.out.println("No Dec");
        // Stream.of(i_myClass.getMethods()).forEach(System.out::println);
        // System.out.println();

        // Class<?> x = i_myClass;
        // while (x != null) {
        //     Stream.of(x.getMethods()).forEach(System.out::println);
        //     x = x.getSuperclass();
        // }
    }   
}