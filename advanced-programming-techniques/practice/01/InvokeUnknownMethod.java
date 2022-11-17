import java.util.Arrays;
import java.util.regex.*;
import java.lang.reflect.*;
import java.util.stream.*;

class Calculator {
  public int add(int a, int b) {
    return a + b;
  }

  public double add(double a, double b) {
    return a + b;
  }

  public int mul(int a, int b) {
    return a * b;
  }

  public double div(double a, double b) {
    return a / b;
  }

  public double neg(double a) {
    return -a;
  }
}

// ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
public class InvokeUnknownMethod {
  public static void main(String[] args) throws Exception {
    Class<?> cls = Class.forName(args[0]);

    Object[] list_args = Stream.of(Arrays.copyOfRange(args, 2, args.length))
        .map(e -> Pattern.matches("[0-9]+.[0-9]+", e) ? Double.parseDouble(e) : Integer.parseInt(e))
        .toArray(Object[]::new);
        
    Class<?>[] c = Stream.of(list_args)
        .map(l -> (l.getClass().getName() == "java.lang.Integer") ? int.class : double.class)
        .toArray(Class<?>[]::new);

    Method x = cls.getDeclaredMethod(args[1], c);
    System.out.println(x.invoke(cls.getDeclaredConstructor().newInstance(), list_args));
  }
}