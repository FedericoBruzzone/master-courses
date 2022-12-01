import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class MyTestClass {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        System.out.println("---------- Main MyStringBuilder ----------");
        //Class<?> cls = Class.forName("java.lang.StringBuilder");
        Class<?> cls = Class.forName("StringBuilder");
        System.out.println(cls.getClass().getName());
        StringBuilder sb = (StringBuilder)cls.getDeclaredConstructor().newInstance();
        System.out.println(Modifier.toString(sb.getClass().getModifiers()));

        
        //StringBuilder sb = new StringBuilder();
        sb.append(new char[]{'a','b'});
        System.out.println(sb);
        System.out.println("----------------------------------------");
    }
}
