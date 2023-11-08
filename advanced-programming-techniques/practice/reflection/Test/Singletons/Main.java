import java.lang.reflect.InvocationTargetException;

public class Main {
	public static void main(String[] args) {
//		try {
//			MyClassLoader mcl1 = new MyClassLoader(";testclasses");
//			MyClassLoader mcl2 = new MyClassLoader(";testclasses");
//			Class<?> x = mcl1.loadClass("Singleton");
//			Class<?> y = mcl2.loadClass("Singleton");
//			Object xx = x.getDeclaredConstructor().newInstance();
//			Object yy = y.getDeclaredConstructor().newInstance();

//			MyClassLoader mcl1 = new MyClassLoader(";testclasses");
//			Class<?> x = mcl1.loadClass("Singleton");
//			Class<?> y = mcl1.loadClass("Singleton");
//			Object xx = x.getDeclaredConstructor().newInstance();
//			Object yy = y.getDeclaredConstructor().newInstance();

			Singleton s1 = new Singleton();
			Singleton s2 = new Singleton();
//		} catch (ClassNotFoundException e) { e.printStackTrace(); }
//		  catch (InstantiationException e) { e.printStackTrace(); }
//		  catch (IllegalAccessException e) { e.printStackTrace(); }
//		  catch (IllegalArgumentException e) { e.printStackTrace(); }
//		  catch (InvocationTargetException e) { e.printStackTrace(); }
//		  catch (NoSuchMethodException e) { e.printStackTrace(); }
//		  catch (SecurityException e) { e.printStackTrace(); }
	}
}
