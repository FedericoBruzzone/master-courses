import java.util.regex.*;
import java.lang.reflect.*;

class Calculator {
  public int add(int a, int b) { return a + b; }
  public double add(double a, double b) { return a + b; }
  public int mul(int a, int b) { return a * b; }
  public double div(double a, double b) { return a / b; }
  public double neg(double a) { return -a; }
}

public class InvokeUnknownMethod {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(args[0]);
        
        String d = "[0-9]++.[0-9]++";
        Object[] list_args = new Object[args.length - 2];
        
        for (int i = 0; i < args.length - 2; i++) {
        	if (Pattern.matches(d, args[i + 2])) {
            	list_args[i] = Double.parseDouble(args[i + 2]);
            } else {
            	list_args[i] = Integer.parseInt(args[i + 2]);
            }
        }
        
        Class<?>[] types = new Class<?>[args.length - 2];
        int j = 0;
        for (var i : list_args) {
        	types[j++] = (i.getClass().getName() == "java.lang.Integer") ? int.class : double.class;
        }
        
        var x = cls.getDeclaredMethod(args[1], types);
        System.out.println(x.invoke(cls.getDeclaredConstructor().newInstance(), list_args));
        
    }
}
