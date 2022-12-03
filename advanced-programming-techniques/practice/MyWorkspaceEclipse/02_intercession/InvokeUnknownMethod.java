// javac *.java
// java InvokeUnknownMethod Calculator add 7 25

import java.util.Arrays;
import java.util.regex.Pattern;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeUnknownMethod {
	public static void main(String[] args) {
		try {
			
			Class<?> cls = Class.forName(args[0]);
			
			Object[] o_args;
			if (Pattern.matches("[0-9]+.[0-9]+", args[2]))  {
				o_args = Arrays.stream(Arrays.copyOfRange(args, 2, args.length))
							.map(e -> Double.parseDouble(e))
							.toArray(Double[]::new);
			}
			else {
				o_args = Arrays.stream(Arrays.copyOfRange(args, 2, args.length))
							.map(e -> Integer.parseInt(e))
							.toArray(Integer[]::new);
			}
			
//			o_args = Arrays.stream(Arrays.copyOfRange(args, 2, args.length))
//					.map(e -> Pattern.matches("[0-9]+.[0-9]+", args[2]) ? Double.parseDouble(e) : Integer.parseInt(e))
//					.toArray(Pattern.matches("[0-9]++.[0-9]++", args[2]) ? Double[]::new : Integer[]::new);
			
			Class<?>[] c_args = Arrays.stream(Arrays.copyOfRange(args, 2, args.length))
					.map(e -> Pattern.matches("[0-9]++.[0-9]++", e) ? double.class : int.class)
					.toArray(Class<?>[]::new);
			
//			for (var i : o_args) { System.out.println(i); }
//			for (var i : c_args) { System.out.println(i); }
			Method m = cls.getDeclaredMethod(args[1], c_args);
			System.out.println(m.invoke(cls.getDeclaredConstructor().newInstance(), o_args));
		} catch (ClassNotFoundException e) { e.printStackTrace(); } 
		  catch (NoSuchMethodException e) { e.printStackTrace(); } 
		  catch (SecurityException e) { e.printStackTrace(); } 
		  catch (IllegalAccessException e) { e.printStackTrace();} 
		  catch (IllegalArgumentException e) { e.printStackTrace(); } 
		  catch (InvocationTargetException e) { e.printStackTrace(); } 
		  catch (InstantiationException e) { e.printStackTrace(); }
		
		
		
			
	}
}
